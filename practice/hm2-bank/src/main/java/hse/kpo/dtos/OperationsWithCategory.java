package hse.kpo.dtos;

import hse.kpo.domains.Category;
import hse.kpo.domains.Operation;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class OperationsWithCategory {
    @Getter
    @Setter
    private List<Operation> operations;

    @Getter
    @Setter
    private Category category;
}
