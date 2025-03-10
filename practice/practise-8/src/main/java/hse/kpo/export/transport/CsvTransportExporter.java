package hse.kpo.export.transport;

import hse.kpo.interfaces.Transport;
import hse.kpo.interfaces.TransportExporter;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class CsvTransportExporter implements TransportExporter {
    private String getStringRepr(Transport transport) {
        return String.format("%d,%s,%s\n",
                transport.getVin(),
                transport.getTransportType(),
                transport.getEngineType());
    }


    public void export(List<Transport> transports, Writer writer) throws IOException {
        transports.stream().map(this::getStringRepr).forEach(toWrite -> {
            try {
                writer.write(toWrite);
                writer.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        try {
            writer.write("\n");
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
