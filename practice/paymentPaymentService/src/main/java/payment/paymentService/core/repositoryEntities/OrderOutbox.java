package payment.paymentService.core.repositoryEntities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import payment.paymentService.core.entities.Order;
import payment.paymentService.core.entities.OrderId;
import payment.paymentService.core.entities.OrderStatus;
import payment.paymentService.core.entities.UserId;

@Table(name="ordersOutbox")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class OrderOutbox {
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

    public OrderOutbox(OrderId orderId, UserId userId, int cost, OrderStatus status) {
        this.setId(orderId);
        this.setUserId(userId);
        this.cost = cost;
        this.status = status;
    }
}

