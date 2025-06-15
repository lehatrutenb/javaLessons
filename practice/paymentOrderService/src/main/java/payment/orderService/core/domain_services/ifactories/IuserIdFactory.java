package payment.orderService.core.domain_services.ifactories;

import payment.orderService.core.entities.UserId;

public interface IuserIdFactory {
    public UserId create(int id);
}
