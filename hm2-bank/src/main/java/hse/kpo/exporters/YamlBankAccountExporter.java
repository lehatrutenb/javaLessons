package hse.kpo.exporters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import hse.kpo.domains.BankAccount;
import hse.kpo.domains.DurationReportElement;
import hse.kpo.interfaces.ExporterI;
import hse.kpo.interfaces.ReportI;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;

@Component
public class YamlBankAccountExporter { /*implements ExporterI<ReportI<BankAccount>> {
    private final ObjectMapper mapper = new ObjectMapper(new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER));
    @Override
    public void export(ReportI<BankAccount> data, Writer writer) throws IOException {
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.writeValue(writer, data.getReport());
    }*/
}
