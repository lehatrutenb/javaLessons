package hse.kpo.interfaces;

import java.io.Writer;

public interface ExporterVisitorI extends BankVisitorI {
    public void setWriter(Writer writer);
}
