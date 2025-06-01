package hse.kpo.domains;

import hse.kpo.interfaces.BankVisitorI;
import hse.kpo.interfaces.ReportI;
import hse.kpo.interfaces.Visitable;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CategoryReport implements ReportI<Category>, Visitable {
    private List<Category> categories = new ArrayList<>();
    @Override
    public void addReportElement(Category elem) {
        categories.add(elem);
    }

    @Override
    public List<Category> getReport() {
        return categories;
    }

    public CategoryReport() {
        categories = new ArrayList<>();
    }

    public CategoryReport(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public void visit(BankVisitorI bankVisitorI) throws IOException {
        bankVisitorI.runOnCategory(this);
    }
}
