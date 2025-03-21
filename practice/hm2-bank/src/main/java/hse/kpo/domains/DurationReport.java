package hse.kpo.domains;

import hse.kpo.interfaces.ReportI;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DurationReport implements ReportI<DurationReportElement> {
    private List<DurationReportElement> report = new ArrayList<>();

    @Override
    public void addReportElement(DurationReportElement elem) {
        report.add(elem);
    }

    @Override
    public List<DurationReportElement> getReport() {
        return report;
    }
}
