package zoo.services;

import org.springframework.stereotype.Component;
import zoo.domains.animals.Animal;
import zoo.interfaces.IclinicService;

import java.util.Random;

@Component
public class ClinicService implements IclinicService {
    public boolean CheckAnimal(Animal animal) {
        return Math.random() < 0.8;
    }
}
