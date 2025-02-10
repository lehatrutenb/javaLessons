package hse.kpo;

import hse.kpo.domains.Customer;
import hse.kpo.domains.HandEngine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HandEngineTest {
    @DisplayName("проверка что подходящий клиент подходит")
    @Test
    public void isCompatibleTest() {
        Customer customer = new Customer("Bob", 1, 6);
        Assertions.assertTrue(new HandEngine().isCompatible(customer));
    }
    @DisplayName("проверка что не подходящий клиент не подходит")
    @Test
    public void isNotCompatibleTest() {
        Customer customer = new Customer("Bob", 1, 5);
        Assertions.assertFalse(new HandEngine().isCompatible(customer));
    }
}
