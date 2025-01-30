package hse.kpo.tests;

import hse.kpo.factories.HandCarFactory;
import hse.kpo.factories.PedalCarFactory;
import hse.kpo.factories.ReviveCarFactory;
import hse.kpo.services.CarService;
import hse.kpo.services.CustomerStorage;
import hse.kpo.services.HseCarService;

public class TestStr {
    public CarService carService;
    public CustomerStorage customerStorage;
    public HseCarService hseCarService;
    public HandCarFactory handCarFactory;
    public PedalCarFactory pedalCarFactory;
    public ReviveCarFactory reviveCarFactory;

    public void setup() {
        carService = new CarService();
        customerStorage = new CustomerStorage();
        hseCarService = new HseCarService(carService, customerStorage);
        handCarFactory = new HandCarFactory();
        pedalCarFactory = new PedalCarFactory();
        reviveCarFactory = new ReviveCarFactory();
    }

    public void teardown() {
    }
}
