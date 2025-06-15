package payment.paymentService.useCases.iShared;

import payment.paymentService.core.entities.OrderId;
import payment.paymentService.core.entities.OrderStatus;
import payment.paymentService.core.entities.UserId;
import payment.paymentService.shared.WrappedError;

public interface IorderService {
    public void applyOrder(OrderId orderId, UserId userId, int cost, OrderStatus status, WrappedError error);
}
