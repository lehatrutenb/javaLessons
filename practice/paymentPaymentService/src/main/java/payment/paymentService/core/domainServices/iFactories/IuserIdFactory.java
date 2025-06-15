package payment.paymentService.core.domainServices.iFactories;

import payment.paymentService.core.domainServices.holders.IuserIdHolder;
import payment.paymentService.core.entities.UserId;

public interface IuserIdFactory {
    public UserId create(int userId);
    public UserId create(IuserIdHolder userIdHolder);
}
