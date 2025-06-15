package payment.orderService.core.domain_services.factories;

import org.springframework.stereotype.Component;
import payment.orderService.core.domain_services.ifactories.IuserIdFactory;
import payment.orderService.core.entities.UserId;

@Component
public class userIdFactory implements IuserIdFactory {
    public UserId create(int id) {
        return new UserId(id);
    }
}
