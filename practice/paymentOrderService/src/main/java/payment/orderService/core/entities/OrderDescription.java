package payment.orderService.core.entities;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
//@Entity
//@Table(name="OrderDescriptions")
public class OrderDescription {
    /*@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @PrimaryKeyJoinColumn
    @Id
    private int id;

    public OrderId getId() {
        return new OrderId(id); // don't want to use factory, cause not looks good to have links from entities to domain_services
    }*/

    public void setId(OrderId orderId) {
        // id = orderId.getId();
    }

    @Getter
    @Setter
    //@Column(nullable = false, name="text")
    private String text;

    public OrderDescription(OrderId orderId, String text) {
        //this.setId(orderId);
        this.text = text;
    }

    public OrderDescription(String text) {
        this.text = text;
    }
}
