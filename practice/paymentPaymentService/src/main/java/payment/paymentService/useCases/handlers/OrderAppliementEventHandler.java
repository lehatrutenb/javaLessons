package payment.paymentService.useCases.handlers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import payment.paymentService.useCases.dtos.ifactories.IorderAppliementResponseFactory;
import payment.paymentService.useCases.events.OrderAppliementEvent;
import payment.paymentService.useCases.iShared.IorderAppliementEventHandler;
import payment.paymentService.useCases.iShared.IorderAppliementOutController;

@Service
@RequiredArgsConstructor
public class OrderAppliementEventHandler implements IorderAppliementEventHandler {
    private final IorderAppliementOutController orderAppliementOutController;
    private final IorderAppliementResponseFactory responseFactory;

    @Override
    public void handle(OrderAppliementEvent event) {
        orderAppliementOutController.orderAppliementSend(responseFactory.create(event.orderId().getId(), event.status().name()));
    }
}