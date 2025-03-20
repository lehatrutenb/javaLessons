package hse.kpo.domains;

import hse.kpo.interfaces.ReportI;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public class OperationReport implements ReportI<Operation> {
    private List<Operation> operations = new ArrayList<>();
    @Override
    public void addReportElement(Operation elem) {
        operations.add(elem);
    }

    public OperationReport() {
        operations = new ArrayList<>();
    }

    public OperationReport(List<Operation> operations) {
        this.operations = operations;
    }

    @Override
    public List<Operation> getReport() {
        return operations;
    }
}
