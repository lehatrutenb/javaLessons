package payment.apigateway.core.entities;

import java.util.Arrays;
import java.util.Optional;

public enum OrderStatus {
    NEW ("NEW"),
    FINISHED ("FINISHED"),
    CANCELED ("CANCELED");

    private final String type;
    OrderStatus(String type) {
        this.type = type;
    }
    public static Optional<OrderStatus> get(String type) {
        return Arrays.stream(values()).filter(curType -> curType.type.equals(type)).findFirst();
    }
}
