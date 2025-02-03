package hse.kpo.interfaces;

import hse.kpo.domains.Car;
import hse.kpo.domains.Customer;

/**
 * Интерфейс, который должны предоставлять классы содержащие имеющиеся машины.
 */
public interface IcarProvider {
    /**
     * Метод пытается подобрать для покупателя машину.
     *
     * @return Car, если машина нашлась , null иначе
     */
    Car takeCar(Customer customer); // Метод возвращает optional на Car, что означает, что метод может ничего не вернуть
}
