package payment.orderService.core.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="orders")
@Entity
public class Order {
    @Id
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
    //@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    //@JoinColumn(name = "id")
    @Column(nullable = false, name="description")
    @Embedded
    private OrderDescription description;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name="status")
    private OrderStatus status;

    public Order(OrderId orderId, UserId userId, int cost, OrderDescription orderDescription, OrderStatus status) {
        this.description = orderDescription;
        this.setId(orderId);
        this.setUserId(userId);
        this.cost = cost;
        this.status = status;
    }
}
