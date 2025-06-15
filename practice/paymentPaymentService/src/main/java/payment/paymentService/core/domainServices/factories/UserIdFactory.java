package payment.paymentService.core.domainServices.factories;

import org.springframework.stereotype.Component;
import payment.paymentService.core.domainServices.holders.IuserIdHolder;
import payment.paymentService.core.domainServices.iFactories.IuserIdFactory;
import payment.paymentService.core.entities.UserId;

@Component
public class UserIdFactory implements IuserIdFactory {
    @Override
    public UserId create(int userId) {
        return new UserId(userId);
    }

    @Override
    public UserId create(IuserIdHolder userIdHolder) {
        return new UserId(userIdHolder.userId());
    }
}
