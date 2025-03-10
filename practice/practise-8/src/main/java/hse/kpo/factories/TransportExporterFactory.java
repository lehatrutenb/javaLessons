package hse.kpo.factories;

import hse.kpo.enums.ReportFormat;
import hse.kpo.enums.TransportFormat;
import hse.kpo.export.reports.ReportExporter;
import hse.kpo.export.reports.impl.JsonReportExporter;
import hse.kpo.export.reports.impl.MarkdownReportExporter;
import hse.kpo.export.transport.CsvTransportExporter;
import hse.kpo.export.transport.XmlTransportExporter;
import hse.kpo.interfaces.Transport;
import hse.kpo.interfaces.TransportExporter;
import org.springframework.stereotype.Component;

@Component
public class TransportExporterFactory {
    public TransportExporter create(TransportFormat format) {
        return switch (format) {
            case CSV -> new CsvTransportExporter();
            case XML -> new XmlTransportExporter();
            default -> throw new IllegalArgumentException("Unsupported format: " + format);
        };
    }
}