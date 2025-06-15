package payment.paymentService.core.entities;

import java.util.Arrays;
import java.util.Optional;

public enum OrderStatus {
    NEW ("NEW"),
    FINISHED ("FINISHED"),
    CANCELLED ("CANCELLED");

    private final String name;

    OrderStatus(String name) {
        this.name = name;
    }

    public static Optional<OrderStatus> get(String name) {
        return Arrays.stream(values()).filter(curStatus -> curStatus.name.equals(name)).findFirst();
    }
}

