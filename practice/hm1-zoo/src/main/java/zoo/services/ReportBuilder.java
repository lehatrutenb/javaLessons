package zoo.services;

import org.springframework.stereotype.Component;
import zoo.domains.Report;

import java.util.List;

@Component
public class ReportBuilder {
    private Report report = null;
    public void Clear() {
        report = new Report();
    }
    public void AddTaggedElement(String tag, String data) {
        report = report.AddData(tag + " : " + data);
    }
    public <T> void AddTaggedList(String tag, List<T> arr) {
        report = report.AddData(tag + " : " + arr);
    }
    public Report GetReport() {
        return report;
    }
}
