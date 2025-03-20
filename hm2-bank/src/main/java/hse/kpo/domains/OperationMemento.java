package hse.kpo.domains;

import hse.kpo.enums.OperationType;
import hse.kpo.interfaces.CsvExportable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class OperationMemento implements CsvExportable {
    public final String id;
    public final String bankAccountId;
    public final int sum;

    public OperationType getOperationType() {
        if (sum < 0) {
            return OperationType.SPENDING;
        } else {
            return OperationType.EARNING;
        }
    }

    public final LocalDateTime timestamp;
    public final String description;
    public final String categoryId;

    public OperationMemento(Operation operation) {
        id = operation.getId();
        bankAccountId = operation.getBankAccount().getId();
        sum = operation.getSum();
        timestamp = operation.getTimestamp();
        description = operation.getDescription();
        categoryId = operation.getCategory().getId();
    }

    public String csvHeader() {
        return "id,bankAccountId,sum,timestamp,description,categoryId";
    }

    public String toCsvString() {
        return String.format("%s,%s,%d,%s,%s,%s", id, bankAccountId,
                sum, timestamp.toString(), description, categoryId);
    }

    /*public void parse(String s) {
        String[] attrs = s.split(",");
        id = attrs[0];
        bankAccountId = attrs[1];
        sum = Integer.parseInt(attrs[2]);
        timestamp = LocalDateTime.parse(attrs[3]);
        description = attrs[4];
        categoryId = attrs[5];
    }*/
}
