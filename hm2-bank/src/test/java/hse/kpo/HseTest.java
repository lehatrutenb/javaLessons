package hse.kpo;

import hse.kpo.domains.*;
import hse.kpo.enums.OperationType;
import hse.kpo.facade.HseBankFacade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;


@SpringBootTest
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
class HseTest {
    @Autowired
    HseBankFacade hseBankFacade;
    @Test
    @DisplayName("тест что дата добавляется в сервис")
    void addData() {
        hseBankFacade.addBankAccount("1", "2", 3);
        hseBankFacade.addCategory(OperationType.EARNING, "4", "earning");
        hseBankFacade.addCategory(OperationType.SPENDING, "5", "spending");
        hseBankFacade.addOperation("6", "1", 1, "4");
        hseBankFacade.addOperation("7", "1", 1, "5");

        assertAll(
                () -> assertEquals("1", hseBankFacade.getBankAccounts().getFirst().getId(),
                        "банковский аккаунт должен был добавиться"),
                () -> assertEquals("4", hseBankFacade.getCategories().getFirst().getId(),
                        "категория должна была добавиться"),
                () -> assertEquals("5", hseBankFacade.getCategories().getLast().getId(),
                        "2ая категория должна была добавиться"),
                () -> assertEquals("6", hseBankFacade.getOperationsByAccountId("1").getFirst().id,
                        "операция должна была добавиться"),
                () -> assertEquals("7", hseBankFacade.getOperationsByAccountId("1").getLast().id,
                        "2ая операция должна была добавиться")
        );
    }
}