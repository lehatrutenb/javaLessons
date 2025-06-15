package payment.orderService.useCases.commands;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import payment.orderService.core.entities.Order;
import payment.orderService.useCases.dtos.ifactories.IorderRequestFactory;
import payment.orderService.useCases.dtos.requests.CreateOrderRequest;
import payment.orderService.useCases.events.OrderCreationEvent;
import payment.orderService.useCases.events.factories.OrderCreationEventFactory;
import payment.orderService.useCases.ishared.IorderCreationHandler;
import payment.orderService.useCases.ishared.IorderCreationOutController;

import static org.springframework.transaction.event.TransactionPhase.AFTER_COMMIT;
import static org.springframework.transaction.event.TransactionPhase.BEFORE_COMMIT;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderCreationPaymentServiceNotifyHandler implements IorderCreationHandler {
    private final IorderCreationOutController producer;

    @Override
    public void handle(OrderCreationEvent order) {
        producer.handle(order);
    }
}
