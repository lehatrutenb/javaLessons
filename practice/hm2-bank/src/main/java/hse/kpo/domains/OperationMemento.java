package hse.kpo.domains;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import hse.kpo.enums.OperationType;
import hse.kpo.interfaces.CsvExportable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    public final LocalDateTime timestamp;
    public String description = "";
    public final String categoryId;

    public OperationMemento(Operation operation) {
        id = operation.getId();
        bankAccountId = operation.getBankAccount().getId();
        sum = operation.getSum();
        timestamp = operation.getTimestamp();
        description = operation.getDescription();
        categoryId = operation.getCategory().getId();
    }

    public OperationMemento(String id, String bankAccountId, int sum, LocalDateTime timestamp, String description, String categoryId) {
        this.id = id;
        this.bankAccountId = bankAccountId;
        this.sum = sum;
        this.timestamp = timestamp;
        this.description = description;
        this.categoryId = categoryId;
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
