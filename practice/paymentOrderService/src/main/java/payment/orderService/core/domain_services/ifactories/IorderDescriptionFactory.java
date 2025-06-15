package payment.orderService.core.domain_services.ifactories;

import payment.orderService.core.entities.OrderDescription;

public interface IorderDescriptionFactory {
    public OrderDescription create(String description);
}
