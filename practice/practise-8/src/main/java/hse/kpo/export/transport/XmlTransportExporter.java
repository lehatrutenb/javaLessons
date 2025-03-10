package hse.kpo.export.transport;

import hse.kpo.interfaces.Transport;
import hse.kpo.interfaces.TransportExporter;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class XmlTransportExporter implements TransportExporter {
    private String getStringRepr(Transport transport) {
        return String.format("""
                            <Vehicle>
                                <VIN>%d</VIN>
                                <Type>%s</Type>
                                <Engine>
                                    <Type>%s</Type>
                                </Engine>
                            </Vehicle>
                            """,
                            transport.getVin(),
                            transport.getTransportType(),
                            transport.getEngineType()
        );
    }


    public void export(List<Transport> transports, Writer writer) throws IOException {
        try {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<TransportList>\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        transports.stream().map(this::getStringRepr).forEach(toWrite -> {
            try {
                writer.write(toWrite + "\n");
                writer.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        try {
            writer.write("</TransportList>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
