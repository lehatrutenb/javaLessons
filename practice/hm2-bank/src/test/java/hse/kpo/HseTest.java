package hse.kpo;

import hse.kpo.domains.*;
import hse.kpo.dtos.OperationsGrouppedByCategories;
import hse.kpo.enums.DataType;
import hse.kpo.enums.OperationType;
import hse.kpo.facade.HseBankFacade;
import kotlin.Pair;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;


@SpringBootTest
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
class HseTest {
    @Autowired
    HseBankFacade hseBankFacade;
    @Test
    @DisplayName("тест что данные добавляются в сервис")
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

    @Test
    @DisplayName("тест что можно корректно посчитать суммерное измение от операций за период")
    void testCalcOperationSumChangeInPeriod() {
        hseBankFacade.addBankAccount("1", "2", 3);
        hseBankFacade.addCategory(OperationType.EARNING, "4", "earning");
        hseBankFacade.addCategory(OperationType.SPENDING, "5", "spending");
        hseBankFacade.addOperation("6", "1", 1, "4");
        hseBankFacade.addOperation("7", "1", -2, "5");

        assertAll(
                () -> assertEquals(-1, hseBankFacade.calcOperationSumChangeInPeriod("1", LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(1)),
                        "1 + -2 = -1 в результате операций")
        );
    }

    @Test
    @DisplayName("тест что пересчёт баланса через операции работает корректно")
    void testRecalcBalanceBasedOnOperations() {
        hseBankFacade.addBankAccount("1", "2", 3);
        hseBankFacade.addCategory(OperationType.EARNING, "4", "earning");
        hseBankFacade.addOperation("6", "1", 1, "4");
        hseBankFacade.addOperation("7", "1", -2, "4");
        hseBankFacade.addOperation("8", "1", -5, "4");
        hseBankFacade.addOperation("9", "1", 10, "4");

        hseBankFacade.recalcBalanceBasedOnOperations("1");
        assertAll(
                () -> assertEquals(4, hseBankFacade.getBankAccounts().getFirst().getBalance(),
                        "1 + -2 + -5 + 10 = 4 в результате операций")
        );
    }

    @Test
    @DisplayName("тест что группировка происходит верно")
    void testGetOperationsGrouppedByCategories() {
        hseBankFacade.addBankAccount("1", "2", 3);
        hseBankFacade.addCategory(OperationType.EARNING, "4", "earning");
        hseBankFacade.addCategory(OperationType.SPENDING, "5", "spending");
        hseBankFacade.addOperation("6", "1", 1, "4");
        hseBankFacade.addOperation("7", "1", -2, "5");
        hseBankFacade.addOperation("8", "1", -2, "5");

        OperationsGrouppedByCategories res =  hseBankFacade.getOperationsGrouppedByCategories("1");
        Pair<List<Operation>, Category> cat1 = res.get().get();
        Pair<List<Operation>, Category> cat2 = res.get().get();
        System.out.println(cat1.getSecond().getId());
        System.out.println(cat2.getSecond().getId());
        assertAll(
                () -> assertEquals("4", cat1.getSecond().getId(),
                        "первая категория"),
                () -> assertEquals("5", cat2.getSecond().getId(),
                "вторая категория"),
                () -> assertEquals(Optional.empty(), res.get(),
                        "больше категорий нет")
        );

        assertAll(
                () -> assertEquals("6", cat1.getFirst().getFirst().getId(),
                        "первая операция"),
                () -> assertEquals("7", cat2.getFirst().getFirst().getId(),
                        "вторая операция"),
                () -> assertEquals("8", cat2.getFirst().getLast().getId(),
                        "третья операция")
        );
    }

    @Test
    @DisplayName("тест что экспорт данных работает без ошибок")
    void testExport() throws Throwable {
        hseBankFacade.addBankAccount("1", "2", 3);
        hseBankFacade.addCategory(OperationType.EARNING, "4", "earning");
        hseBankFacade.addCategory(OperationType.SPENDING, "5", "spending");
        hseBankFacade.addOperation("6", "1", 1, "4");
        hseBankFacade.addOperation("7", "1", -2, "5");
        hseBankFacade.addOperation("8", "1", -2, "5");
        hseBankFacade.exportAll(DataType.CSV);
        hseBankFacade.exportAll(DataType.JSON);
        hseBankFacade.exportAll(DataType.YAML);
    }

    @Test
    @DisplayName("тест что экспорт и импорт данных json работает без ошибок")
    void testExportImortJson() throws Throwable {
        hseBankFacade.addBankAccount("1", "2", 3);
        hseBankFacade.addCategory(OperationType.EARNING, "4", "earning");
        hseBankFacade.addCategory(OperationType.SPENDING, "5", "spending");
        hseBankFacade.addOperation("6", "1", 1, "4");
        hseBankFacade.addOperation("7", "1", -2, "5");
        hseBankFacade.addOperation("8", "1", -2, "5");
        hseBankFacade.exportAll(DataType.JSON);
        hseBankFacade.importAll(DataType.JSON);
    }

    private void testDataSame() {
        assertAll(
                () -> assertEquals(2, hseBankFacade.getBankAccounts().size(),
                        "кол-во банковских аккаунтов"),
                () -> assertEquals(2, hseBankFacade.getCategories().size(),
                        "кол-во категорий"),
                () -> assertEquals(2, hseBankFacade.getOperationsByAccountId("1").size(),
                        "кол-во операций")
        );

        assertAll(
                () -> assertEquals("1", hseBankFacade.getBankAccounts().getFirst().getId(),
                        "оригинальный банковский аккаунт"),
                () -> assertEquals("1", hseBankFacade.getBankAccounts().getLast().getId(),
                        "скопированный банковский аккаунт"),
                () -> assertEquals("4", hseBankFacade.getCategories().getFirst().getId(),
                        "оригинальная категория"),
                () -> assertEquals("4", hseBankFacade.getCategories().getLast().getId(),
                        "скопированная категория"),
                () -> assertEquals("6", hseBankFacade.getOperationsByAccountId("1").getFirst().id,
                        "оригинальная операция"),
                () -> assertEquals("6", hseBankFacade.getOperationsByAccountId("1").getLast().id,
                        "скопированная операция")
        );

        assertAll(
                () -> assertEquals("2", hseBankFacade.getBankAccounts().getFirst().getName(),
                        "оригинальный банковский аккаунт"),
                () -> assertEquals("2", hseBankFacade.getBankAccounts().getLast().getName(),
                        "скопированный банковский аккаунт"),
                () -> assertEquals(OperationType.EARNING, hseBankFacade.getCategories().getFirst().getOperationType(),
                        "оригинальная категория"),
                () -> assertEquals(OperationType.EARNING, hseBankFacade.getCategories().getLast().getOperationType(),
                        "скопированная категория"),
                () -> assertEquals(1, hseBankFacade.getOperationsByAccountId("1").getFirst().sum,
                        "оригинальная операция"),
                () -> assertEquals(1, hseBankFacade.getOperationsByAccountId("1").getLast().sum,
                        "скопированная операция")
        );
    }

    @Test
    @DisplayName("тест что экспорт и импорт данных json не портит данные")
    void testExportImortJsonCheckSameObjects() throws Throwable {
        hseBankFacade.addBankAccount("1", "2", 3);
        hseBankFacade.addCategory(OperationType.EARNING, "4", "earning");
        hseBankFacade.addOperation("6", "1", 1, "4");
        hseBankFacade.exportAll(DataType.JSON);
        hseBankFacade.importAll(DataType.JSON);

        testDataSame();
    }

    @Test
    @DisplayName("тест что экспорт и импорт данных yaml работает без ошибок")
    void testExportImortYaml() throws Throwable {
        hseBankFacade.addBankAccount("1", "2", 3);
        hseBankFacade.addCategory(OperationType.EARNING, "4", "earning");
        hseBankFacade.addCategory(OperationType.SPENDING, "5", "spending");
        hseBankFacade.addOperation("6", "1", 1, "4");
        hseBankFacade.addOperation("7", "1", -2, "5");
        hseBankFacade.addOperation("8", "1", -2, "5");
        hseBankFacade.exportAll(DataType.YAML);
        hseBankFacade.importAll(DataType.YAML);
    }

    @Test
    @DisplayName("тест что экспорт и импорт данных yaml не портит данные")
    void testExportImortYamlCheckSameObjects() throws Throwable {
        hseBankFacade.addBankAccount("1", "2", 3);
        hseBankFacade.addCategory(OperationType.EARNING, "4", "earning");
        hseBankFacade.addOperation("6", "1", 1, "4");
        hseBankFacade.exportAll(DataType.YAML);
        hseBankFacade.importAll(DataType.YAML);

        testDataSame();
    }


    @Test
    @DisplayName("тест что экспорт и импорт данных csv работает без ошибок")
    void testExportImortCsv() throws Throwable {
        hseBankFacade.addBankAccount("1", "2", 3);
        hseBankFacade.addCategory(OperationType.EARNING, "4", "earning");
        hseBankFacade.addCategory(OperationType.SPENDING, "5", "spending");
        hseBankFacade.addOperation("6", "1", 1, "4");
        hseBankFacade.addOperation("7", "1", -2, "5");
        hseBankFacade.addOperation("8", "1", -2, "5");
        hseBankFacade.exportAll(DataType.CSV);
        hseBankFacade.importAll(DataType.CSV);
    }

    @Test
    @DisplayName("тест что экспорт и импорт данных csv не портит данные")
    void testExportImortCsvCheckSameObjects() throws Throwable {
        hseBankFacade.addBankAccount("1", "2", 3);
        hseBankFacade.addCategory(OperationType.EARNING, "4", "earning");
        hseBankFacade.addOperation("6", "1", 1, "4");
        hseBankFacade.exportAll(DataType.CSV);
        hseBankFacade.importAll(DataType.CSV);

        testDataSame();
    }

    @Test
    @DisplayName("тест отслеживания времени (exportAll имеет @MeasureTime)")
    void testMeasureTime() throws Throwable {
        hseBankFacade.addBankAccount("1", "2", 3);
        hseBankFacade.addCategory(OperationType.EARNING, "4", "earning");
        hseBankFacade.addCategory(OperationType.SPENDING, "5", "spending");
        hseBankFacade.addOperation("6", "1", 1, "4");
        hseBankFacade.addOperation("7", "1", -2, "5");
        hseBankFacade.addOperation("8", "1", -2, "5");

        hseBankFacade.exportAll(DataType.CSV);

        StringWriter writerCsv = new StringWriter();
        hseBankFacade.buildDurationData(DataType.CSV, writerCsv);
        System.out.println(writerCsv.toString());

        StringWriter writerJson = new StringWriter();
        hseBankFacade.buildDurationData(DataType.JSON, writerJson);
        System.out.println(writerJson.toString());

        StringWriter writerYaml = new StringWriter();
        hseBankFacade.buildDurationData(DataType.YAML, writerYaml);
        System.out.println(writerYaml.toString());
    }
}