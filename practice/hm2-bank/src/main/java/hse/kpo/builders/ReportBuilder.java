package hse.kpo.builders;

import hse.kpo.interfaces.ExporterI;
import hse.kpo.interfaces.ReportI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Writer;
import java.util.List;

//@Component
@RequiredArgsConstructor
public class ReportBuilder<T> {
    private final ReportI<T> report;
    public void addReportElement(T elem) {
        report.addReportElement(elem);
    }

    public void createReport(ExporterI<ReportI<T>> exporter, Writer writer) throws Throwable {
        exporter.export(report, writer);
    }
}
