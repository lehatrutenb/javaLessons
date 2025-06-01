package hse.kpo.storages;

import hse.kpo.domains.Operation;
import hse.kpo.domains.OperationReport;
import hse.kpo.interfaces.OperationStorageI;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OperationStorage implements OperationStorageI {
    private final OperationReport operationReport = new OperationReport();
    @Override
    public void add(Operation operation) {
        operationReport.addReportElement(operation);
    }

    @Override
    public void addReport(OperationReport operationReport) {
        operationReport.getReport().forEach(this::add);
    }

    @Override
    public OperationReport getReport() {
        return operationReport;
    }
}
