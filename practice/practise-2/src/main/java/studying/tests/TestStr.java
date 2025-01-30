package studying.tests;

import studying.ReviveCarFactory;
import studying.factories.HandCarFactory;
import studying.factories.PedalCarFactory;
import studying.services.CarService;
import studying.services.CustomerStorage;
import studying.services.HseCarService;

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
