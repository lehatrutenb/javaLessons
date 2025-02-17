package hse.kpo;

import hse.kpo.domains.Customer;
import hse.kpo.domains.ReviveEngine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ReviveEngineTest {
    @DisplayName("проверка что подходящий клиент подходит")
    @Test
    public void isCompatibleTest() {
        Customer customer = new Customer("Bob", 6, 6, 301);
        Assertions.assertTrue(new ReviveEngine().isCompatible(customer));
    }
    @DisplayName("проверка что не подходящий клиент не подходит")
    @Test
    public void isNotCompatibleTest() {
        Customer customer = new Customer("Bob", 5, 10, 300);
        Assertions.assertFalse(new ReviveEngine().isCompatible(customer));
    }
}
