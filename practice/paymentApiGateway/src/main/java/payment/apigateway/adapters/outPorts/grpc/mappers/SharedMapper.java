package payment.apigateway.adapters.outPorts.grpc.mappers;

import org.springframework.stereotype.Component;
import payment.apigateway.grpc.OrderDescriptionProto;
import payment.apigateway.grpc.OrderIdProto;
import payment.apigateway.grpc.UserIdProto;
import payment.apigateway.grpc.WalletIdProto;

@Component
public class SharedMapper {
    public int mapUserId(UserIdProto userIdProto) {
        return userIdProto.getId();
    }

    public UserIdProto mapUserId(int userId) {
        return UserIdProto.newBuilder().setId(userId).build();
    }

    public String map(OrderDescriptionProto orderDescriptionProto) {
        return orderDescriptionProto.getText();
    }

    public OrderDescriptionProto map(String orderDescription) {
        return OrderDescriptionProto.newBuilder().setText(orderDescription).build();
    }

    public int mapOrderId(OrderIdProto orderIdProto) {
        return orderIdProto.getId();
    }

    public OrderIdProto mapOrderId(int orderId) {
        return OrderIdProto.newBuilder().setId(orderId).build();
    }

    public int mapWalletId(WalletIdProto walletIdProto) {
        return walletIdProto.getId();
    }

    public WalletIdProto mapWalletId(int walletId) {
        return WalletIdProto.newBuilder().setId(walletId).build();
    }
}
