package payment.paymentService.core.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name="status")
    private OrderStatus status;

    public void apply() {
        status = OrderStatus.FINISHED;
    }

    public void cancel() {
        status = OrderStatus.CANCELLED;
    }

    public Order(OrderId orderId, UserId userId, int cost, OrderStatus status) {
        this.setId(orderId);
        this.setUserId(userId);
        this.cost = cost;
        this.status = status;
    }
}
