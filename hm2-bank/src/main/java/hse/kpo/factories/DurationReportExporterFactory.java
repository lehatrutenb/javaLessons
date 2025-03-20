package hse.kpo.factories;

import hse.kpo.domains.DurationReport;
import hse.kpo.enums.DataType;
import hse.kpo.exporters.CsvDurationReportExporter;
import hse.kpo.exporters.JsonDurationReportExporter;
import hse.kpo.exporters.YamlDurationReportExporter;
import hse.kpo.interfaces.ExporterI;
import hse.kpo.interfaces.ReportExporterFactoryI;
import org.springframework.stereotype.Component;

/*@Component
public class DurationReportExporterFactory implements ReportExporterFactoryI<DurationReport> {
    @Override
    public ExporterI<DurationReport> create(DataType dataType) {
        switch (dataType) {
            case CSV -> {return new CsvDurationReportExporter();}
            case YAML -> {return new YamlDurationReportExporter();}
            case JSON -> {return new JsonDurationReportExporter();}
            default -> throw new IllegalArgumentException("Unsupported format: " + dataType);
        }
    }
}
*/