package payment.orderService.core.repositoryEntities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;
import payment.orderService.core.entities.*;
import payment.orderService.core.entities.Order;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="ordersOutbox")
@Entity
public class OrderOutbox {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public OrderId getId() {
        return new OrderId(id); // don't want to use factory, cause not looks good to have links from entities to domain_services
    }

    public void setId(OrderId orderId) {
        id = orderId.getId();
        description.setId(orderId);
    }

    @Column(nullable = false, name="userId")
    private int userId;

    public UserId getUserId() {
        return new UserId(userId);
    }

    public void setUserId(UserId userId) {
        this.userId = userId.getId();
    }

    @Getter
    @Setter
    @Column(nullable = false, name="cost")
    private int cost;

    @Getter
    @Setter

    @Column(nullable = false, name="description")
    @Embedded
    private OrderDescription description;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name="status")
    private OrderStatus status;

    public OrderOutbox(UserId userId, int cost, OrderDescription orderDescription, OrderStatus status) {
        this.description = orderDescription;
        this.setUserId(userId);
        this.cost = cost;
        this.status = status;
    }
}
