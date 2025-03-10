package hse.kpo.interfaces;

import hse.kpo.domains.PureTransport;
import hse.kpo.storages.TransportStorage;

import java.io.Reader;
import java.util.List;

public interface TransportImporter {
    List<PureTransport> importer(Reader reader);
}
