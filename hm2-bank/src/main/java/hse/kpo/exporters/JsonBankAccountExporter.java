package hse.kpo.exporters;

import com.fasterxml.jackson.databind.ObjectMapper;
import hse.kpo.domains.BankAccount;
import hse.kpo.domains.BankAccountReport;
import hse.kpo.domains.DurationReport;
import hse.kpo.domains.DurationReportElement;
import hse.kpo.interfaces.ExporterI;
import hse.kpo.interfaces.ReportI;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;

@Component
public class JsonBankAccountExporter implements ExporterI<ReportI<BankAccount>> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void export(ReportI<BankAccount> data, Writer writer) throws IOException {
        objectMapper.writeValue(writer, data.getReport());
    }
}
