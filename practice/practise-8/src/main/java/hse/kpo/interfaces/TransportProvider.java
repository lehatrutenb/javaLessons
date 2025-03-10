package hse.kpo.interfaces;

import java.util.List;

public interface TransportProvider {
    List<Transport> getTransports();
    void setTransports(List<Transport> transports);
}
