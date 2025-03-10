package hse.kpo.interfaces.cars;

import hse.kpo.domains.cars.Car;

/**
 * Интерфейс для определения методов фабрик.
 *
 * @param <TParams> параметры для фабрик
 */
public interface CarFactory<TParams> {
    /**
     * Метод создания машин.
     *
     * @param carParams параметры для создания
     * @param carNumber номер
     * @return {@link Car}
     */
    Car create(TParams carParams, int carNumber);
}
