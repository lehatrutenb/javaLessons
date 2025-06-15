package payment.paymentService.useCases.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.TransactionTemplate;
import payment.paymentService.core.domainServices.holders.IorderOutboxFactory;
import payment.paymentService.core.domainServices.iFactories.IorderFactory;
import payment.paymentService.core.domainServices.iFactories.IwalletFactory;
import payment.paymentService.core.domainServices.iFactories.IwalletIdFactory;
import payment.paymentService.core.entities.Order;
import payment.paymentService.core.entities.OrderId;
import payment.paymentService.core.entities.OrderStatus;
import payment.paymentService.core.entities.UserId;
import payment.paymentService.iShared.IwrappedErrorFactory;
import payment.paymentService.shared.WrappedError;
import payment.paymentService.useCases.events.factories.OrderAppliementEventFactory;
import payment.paymentService.useCases.iShared.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService implements IorderService {
    private List<IorderAppliementEventHandler> orderCreationEventHandlerList = new ArrayList<>();
    private final IorderFactory orderFactory;
    private final IorderOutboxRepository orderOutboxRepository;
    private final IorderRepository orderRepository;
    private final IorderOutboxFactory orderOutboxFactory;
    private final OrderAppliementEventFactory orderCreationEventFactory;
    private final IpaymentService paymentService;
    private final IwrappedErrorFactory wrappedErrorFactory;
    private final IwalletIdFactory walletIdFactory;
    private TransactionTemplate transactionTemplate;

    public void subscribeOnOrderCreation(IorderAppliementEventHandler handler) {
        orderCreationEventHandlerList.add(handler);
    }

    @Transactional
    @Scheduled(cron = "*/5 * * * * ?")
    protected void applyOrderInner() {
        Optional<Order> wrappedOrder;
        try {
            wrappedOrder = orderOutboxRepository.findFirstByStatus(OrderStatus.NEW).map(orderFactory::create);
        } catch (RuntimeException e) {
            log.warn(e.getMessage());
            return;
        }

        if (wrappedOrder.isEmpty()) {
            return;
        }
        var order = wrappedOrder.get();
        orderOutboxRepository.deleteById(order.getId());
        if (orderRepository.findById(order.getId()).isPresent()) {
            return;
        }
        WrappedError error = wrappedErrorFactory.create();
        paymentService.deductFromWallet(walletIdFactory.create(order.getUserId()), order.getCost(), error);
        if (error.hasError()) {
            log.debug("failed to deduct from wallet");
            order.cancel();
        } else {
            order.apply();
        }
        orderRepository.save(order);

        var event = orderCreationEventFactory.create(order);

        try {
            orderCreationEventHandlerList.forEach(handler -> handler.handle(event));
        } catch (RuntimeException e) {
            log.warn(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return;
        }
        log.debug(String.format("successfully done transactdion for %d", order.getId().getId()));
    }

    @Transactional
    public void applyOrder(OrderId orderId, UserId userId, int cost, OrderStatus status, WrappedError error) {
        orderOutboxRepository.save(orderOutboxFactory.create(orderId, userId, cost, status));
    }
}
