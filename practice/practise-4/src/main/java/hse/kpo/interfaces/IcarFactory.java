package hse.kpo.interfaces;

import hse.kpo.domains.Car;

/**
 * Интерфейс который должны реализовывать фабрики машин.
 */
public interface IcarFactory<CparamsT> {
    Car createCar(CparamsT carParams, int carNumber);
}
