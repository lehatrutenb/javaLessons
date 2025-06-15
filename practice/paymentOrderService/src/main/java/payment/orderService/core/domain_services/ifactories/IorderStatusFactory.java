package payment.orderService.core.domain_services.ifactories;

import payment.orderService.core.entities.OrderStatus;

import java.util.Optional;

public interface IorderStatusFactory {
    public Optional<OrderStatus> create(String status);
}
