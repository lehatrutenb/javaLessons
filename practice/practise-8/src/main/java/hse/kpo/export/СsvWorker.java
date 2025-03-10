package hse.kpo.export;

import hse.kpo.domains.PureTransport;
import hse.kpo.interfaces.Transport;

import java.util.Arrays;
import java.util.List;

public class СsvWorker {
    private String transportToCsv(Transport transport) {
        return String.format("%d,%s,%s\n",
                transport.getVin(),
                transport.getTransportType(),
                transport.getEngineType());
    }

    private String TransportsToCsv(List<Transport> transports) {
        return transports.stream().map(transport -> transportToCsv(transport)).reduce((s1, s2) -> s1 + s2).get();
    }

    private List<Transport> CsvToTransport(String data) {
        Arrays.stream(data.split("\n")).map(s -> {

            }
        );
    }
}
