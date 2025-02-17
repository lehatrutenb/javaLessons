package zoo.domains;

import org.springframework.stereotype.Component;

@Component
public class Report {
    private final String data;
    public Report(String data) {
        this.data = data;
    }
    public Report() {
        this.data = "";
    }
    public Report AddData(String data) {
        return new Report(this.data + data + "\n");
    }
    public String GetData() {
        return "{\n" + data + "\n}";
    }

    @Override
    public String toString() {
        return GetData();
    }
}
