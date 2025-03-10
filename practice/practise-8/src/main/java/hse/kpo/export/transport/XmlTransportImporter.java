package hse.kpo.export.transport;

import hse.kpo.domains.PureTransport;
import hse.kpo.interfaces.Transport;
import hse.kpo.interfaces.TransportImporter;
import hse.kpo.interfaces.TransportProvider;
import hse.kpo.storages.TransportStorage;

import java.io.Reader;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

public class XmlTransportImporter implements TransportImporter {
    public List<PureTransport> importer(Reader reader) throws RuntimeException {
        Unmarshaller unmarshaller;
        List<PureTransport> pureTransports;
        try {
            JAXBContext context = JAXBContext.newInstance(TransportStorage.class);
            unmarshaller = context.createUnmarshaller();
            pureTransports = ((TransportStorage) unmarshaller.unmarshal(reader)).getTransports();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return pureTransports;
    }
}

