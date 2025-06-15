package payment.paymentService.useCases.iShared;

import payment.paymentService.useCases.events.OrderAppliementEvent;

public interface IorderAppliementEventHandler {
    public void handle(OrderAppliementEvent event);
}
