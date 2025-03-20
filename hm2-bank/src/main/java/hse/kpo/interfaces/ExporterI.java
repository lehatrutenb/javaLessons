package hse.kpo.interfaces;

import java.io.IOException;
import java.io.Writer;

public interface ExporterI<T> {
    public void export(T data, Writer writer) throws IOException;
}
