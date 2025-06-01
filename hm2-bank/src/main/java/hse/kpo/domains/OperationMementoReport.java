package hse.kpo.domains;

import hse.kpo.interfaces.BankVisitorI;
import hse.kpo.interfaces.ReportI;
import hse.kpo.interfaces.Visitable;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OperationMementoReport implements ReportI<OperationMemento>, Visitable {
    private List<OperationMemento> operationMementos = new ArrayList<>();
    @Override
    public void addReportElement(OperationMemento elem) {
        operationMementos.add(elem);
    }

    @Override
    public List<OperationMemento> getReport() {
        return operationMementos;
    }

    public OperationMementoReport(OperationReport operationReport) {
        operationMementos = operationReport.getReport().stream().map(OperationMemento::new).toList();
    }

    public OperationMementoReport(List<OperationMemento> operationMementos) {
        this.operationMementos = operationMementos;
    }

    @Override
    public void visit(BankVisitorI bankVisitor) throws IOException {
        bankVisitor.runOnOperationMemento(this);
    }
}
