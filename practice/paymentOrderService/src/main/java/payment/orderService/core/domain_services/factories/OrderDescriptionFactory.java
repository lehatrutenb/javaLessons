package payment.orderService.core.domain_services.factories;

import org.springframework.stereotype.Component;
import payment.orderService.core.domain_services.ifactories.IorderDescriptionFactory;
import payment.orderService.core.entities.OrderDescription;

@Component
public class OrderDescriptionFactory implements IorderDescriptionFactory {
    @Override
    public OrderDescription create(String description) {
        return new OrderDescription(description);
    }
}
