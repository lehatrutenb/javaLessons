package hse.kpo.factories;

import hse.kpo.enums.TransportFormat;
import hse.kpo.export.transport.CsvTransportExporter;
import hse.kpo.export.transport.XmlTransportExporter;
import hse.kpo.export.transport.XmlTransportImporter;
import hse.kpo.interfaces.TransportImporter;
import org.springframework.stereotype.Component;

@Component
public class TransportImportererFactory {
    public TransportImporter create(TransportFormat format) {
        return switch (format) {
            //case CSV -> new CsvTransport();
            case XML -> new XmlTransportImporter();
            default -> throw new IllegalArgumentException("Unsupported format: " + format);
        };
    }
}
