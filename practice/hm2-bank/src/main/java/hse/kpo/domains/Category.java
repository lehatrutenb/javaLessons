package hse.kpo.domains;

import hse.kpo.enums.OperationType;
import hse.kpo.interfaces.CsvExportable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class Category implements CsvExportable {
    @Getter
    private OperationType operationType;

    @Getter
    private String id;

    @Getter
    private String name;

    public Category(OperationType operationType, String id, String name) {
        this.operationType = operationType;
        this.id = id;
        this.name = name;
    }

    @Override
    public String csvHeader() {
        return "operationType,id,name";
    }

    @Override
    public String toCsvString() {
        return String.format("%s,%s,%s", operationType.name(), id, name);
    }

   /* @Override
    public void parse(String s) {
        String[] attrs = s.split(",");
        operationType = OperationType.valueOf(attrs[0]);
        id = attrs[1];
        name = attrs[2];
    }*/
}
