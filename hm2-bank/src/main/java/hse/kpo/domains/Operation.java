package hse.kpo.domains;

import hse.kpo.enums.OperationType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class Operation {
    @Getter
    private final String id;

    @Getter
    private final BankAccount bankAccount;

    @Getter
    private final int sum;

    public OperationType getOperationType() {
        if (sum < 0) {
            return OperationType.SPENDING;
        } else {
            return OperationType.EARNING;
        }
    }

    @Getter
    private final LocalDateTime timestamp;

    @Getter
    @Setter
    private String description;

    @Getter
    private final Category category;

    public Operation(String id, BankAccount bankAccount, int sum, Category category) {
        this.id = id;
        this.bankAccount = bankAccount;
        this.sum = sum;
        this.category = category;
        timestamp = LocalDateTime.now();
    }
}
