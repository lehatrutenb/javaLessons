package payment.orderService.core.domain_services.ifactories;

import payment.orderService.core.entities.OrderId;

public interface IorderIdFactory {
    public OrderId create(int id);
}
