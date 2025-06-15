package payment.orderService.adapters.ports.shared;

import org.apache.catalina.User;
import org.springframework.stereotype.Component;
import payment.orderService.core.entities.OrderDescription;
import payment.orderService.core.entities.OrderId;
import payment.orderService.core.entities.UserId;
import payment.orderService.grpc.*;
import payment.orderService.shared.WrappedError;

@Component
public class SharedResponseProtoMapper {
    public ErrorProto map(WrappedError error) {
        return ErrorProto.newBuilder()
                .setCode(error.getCode())
                .setMessage(error.getMessage())
                .build();
    }

    public OrderIdProto mapOrderId(int orderId) {
        return OrderIdProto.newBuilder().setId(orderId).build();
    }

    public UserIdProto mapUserId(int userId) {
        return UserIdProto.newBuilder().setId(userId).build();
    }

    public OrderIdProto map(OrderId orderId) {
        return mapOrderId(orderId.getId());
    }

    public UserIdProto map(UserId userId) {
        return mapUserId(userId.getId());
    }

    public OrderStatusProto map(String status) {
        return OrderStatusProto.valueOf(status);
    }

    public OrderDescriptionProto map(OrderDescription orderDescription) {
        return OrderDescriptionProto.newBuilder().setText(orderDescription.getText()).build();
    }
}
