package payment.orderService.useCases.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.internals.TransactionManager;
import org.checkerframework.checker.formatter.qual.ReturnsFormat;
import org.hibernate.internal.TransactionManagement;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.reactive.TransactionContextManager;
import org.springframework.transaction.reactive.TransactionalEventPublisher;
import payment.orderService.core.domain_services.factories.OrderFactory;
import payment.orderService.core.domain_services.ifactories.IorderDescriptionFactory;
import payment.orderService.core.domain_services.ifactories.IorderFactory;
import payment.orderService.core.domain_services.ifactories.IorderOutboxFactory;
import payment.orderService.core.domain_services.ifactories.IorderStatusFactory;
import payment.orderService.core.entities.Order;
import payment.orderService.core.entities.OrderId;
import payment.orderService.core.entities.OrderStatus;
import payment.orderService.core.entities.UserId;
import payment.orderService.shared.WrappedError;
import payment.orderService.useCases.dtos.ifactories.IorderRequestFactory;
import payment.orderService.useCases.events.factories.OrderCreationEventFactory;
import payment.orderService.useCases.ishared.IorderCreationHandler;
import payment.orderService.useCases.ishared.IorderOutboxRepository;
import payment.orderService.useCases.ishared.IorderRepository;
import payment.orderService.useCases.ishared.IorderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService implements IorderService {
    private final IorderOutboxRepository outboxRepository;
    private final IorderRepository repository;
    private final IorderFactory orderFactory;
    private final IorderOutboxFactory orderOutboxFactory;
    private final OrderCreationEventFactory orderCreationEventFactory;
    private final IorderDescriptionFactory orderDescriptionFactory;
    private List<IorderCreationHandler> orderCreationSubs = new ArrayList<>();
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void subscribeOnOrderCreation(IorderCreationHandler handler) {
        orderCreationSubs.add(handler);
    }

    @Scheduled(cron = "*/5 * * * * ?")
    @Transactional
    protected void createOrderInner() {
        Optional<Order> wrappedOrder;
        try {
            wrappedOrder = outboxRepository.findFirstByStatus(OrderStatus.NEW).map(orderFactory::create);
        } catch (RuntimeException e) {
            log.warn(e.getMessage());
            return;
        }

        if (wrappedOrder.isEmpty()) {
            return;
        }
        var order = wrappedOrder.get();

        if (repository.findById(order.getId()).isPresent()) {
            outboxRepository.deleteById(order.getId());
            return;
        }
        repository.save(order);
        outboxRepository.deleteById(order.getId());
        var event = orderCreationEventFactory.create(order);
        try {
            orderCreationSubs.forEach(handler -> handler.handle(event));
        } catch (RuntimeException e) {
            log.warn(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return;
        }
        log.debug(String.format("successfully done transactdion for %d", order.getId().getId()));
    }

    @Override
    public Optional<OrderId> createOrder(UserId userId, int cost, String description, WrappedError error) {
        var response = outboxRepository.save(
                orderOutboxFactory.create(userId, cost, orderDescriptionFactory.create(description), OrderStatus.NEW)
        );
        return Optional.of(response.getId());
    }

    @Override
    public Optional<List<OrderId>> getOrderListByUser(UserId userId, WrappedError error) {
        return Optional.of(
                repository.findByUserId(userId).stream().map(Order::getId).toList()
        );
    }

    @Override
    public Optional<OrderStatus> getOrderMeta(OrderId orderId, WrappedError error) {
        var response = repository.findById(orderId);
        if (response.isEmpty()) {
            error.setCode(404);
            error.setMessage("Order with such id not found");
        }
        return response.map(Order::getStatus);
    }

    @Override
    public void processOrderAppliementEvent(OrderId orderId, OrderStatus newOrderStatus, WrappedError error) {
        Optional<Order> order = repository.findById(orderId);
        if (order.isEmpty()) {
            log.error(String.format("Expected to find order with id %d in order repo", orderId.getId()));
            error.setCode(404);
            error.setMessage("Can't fins such order in repo");
            return;
        }
        order.get().setStatus(newOrderStatus);
        repository.save(order.get());
    }
}
