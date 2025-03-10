package hse.kpo.storages;

import hse.kpo.domains.PureTransport;
import hse.kpo.interfaces.Transport;
import hse.kpo.interfaces.TransportProvider;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="TransportList")
public class TransportStorage {
    @Setter
    List<PureTransport> transports;
    @XmlElement(name = "Vehicle")
    public List<PureTransport> getTransports() {
        return transports;
    }
}
