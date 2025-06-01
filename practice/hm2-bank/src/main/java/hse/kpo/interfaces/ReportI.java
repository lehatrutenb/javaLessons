package hse.kpo.interfaces;
import java.util.List;

public interface ReportI <T> {
    public void addReportElement(T elem);
    public List<T> getReport();
}
