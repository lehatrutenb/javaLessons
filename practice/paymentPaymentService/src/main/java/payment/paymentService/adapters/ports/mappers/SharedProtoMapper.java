package payment.paymentService.adapters.ports.mappers;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import payment.paymentService.core.entities.WalletId;
import payment.paymentService.grpc.*;
import payment.paymentService.shared.WrappedError;

@Component
@NoArgsConstructor
public class SharedProtoMapper {
    public int map(UserIdProto userIdProto) {
        return userIdProto.getId();
    }
    public UserIdProto mapUserId(int userId) {
        return UserIdProto.newBuilder().setId(userId).build();
    }
    public int map(WalletIdProto walletId) {
        return walletId.getId();
    }
    public WalletIdProto mapWalletId(int walletId) {
        return WalletIdProto.newBuilder().setId(walletId).build();
    }
    public int map(OrderIdProto orderId) {
        return orderId.getId();
    }
    public OrderIdProto mapOrderId(int orderId) {
        return OrderIdProto.newBuilder().setId(orderId).build();
    }
    public ErrorProto map(WrappedError error) {
        return ErrorProto.newBuilder()
                .setCode(error.getCode())
                .setMessage(error.getMessage())
                .build();
    }
    public OrderStatusProto map(String status) {
        return OrderStatusProto.valueOf(status);
    }
    public String map(OrderStatusProto status) {
        return status.name();
    }
}
