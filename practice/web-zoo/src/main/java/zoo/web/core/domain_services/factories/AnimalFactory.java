package zoo.web.core.domain_services.factories;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import zoo.web.core.entities.AllAnimalInfo;
import zoo.web.core.entities.animals.Animal;
import zoo.web.core.entities.animals.AnimalEatType;
import zoo.web.core.entities.animals.AnimalMale;
import zoo.web.core.entities.animals.AnimalType;
import zoo.web.core.entities.feeding.AnimalFeeding;
import zoo.web.core.entities.feeding.Food;
import zoo.web.core.entities.healing.AnimalHealing;
import zoo.web.ishared.IanimalFactory;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Component
public class AnimalFactory implements IanimalFactory {
    private AnimalFeeding createFeeding(AnimalEatType animalEatType,
                                       AnimalType animalType,
                                       AnimalMale animalMale,
                                       Food favoriteFood,
                                        UUID id) {
        return new AnimalFeeding(animalEatType, animalType, animalMale, favoriteFood, id);
    }
    private Animal createAnimal(LocalDate birthday,
                                AnimalMale animalMale,
                                UUID id) {
        return new Animal(birthday, animalMale, id);
    }
    private AnimalHealing createHealing( UUID id,
                                         boolean isHealthy,
                                         LocalDate birthday,
                                         AnimalMale animalMale) {
        return new AnimalHealing(id, isHealthy, birthday, animalMale);
    }

    public AllAnimalInfo create(AnimalEatType animalEatType,
                                AnimalType animalType,
                                AnimalMale animalMale,
                                Food favoriteFood,
                                LocalDate birthday,
                                boolean isHealthy) {
        UUID id = UUID.randomUUID();
        return new AllAnimalInfo(createAnimal(birthday, animalMale, id),
                createFeeding(animalEatType, animalType, animalMale, favoriteFood, id),
                createHealing(id, isHealthy, birthday, animalMale));
    }

    @Override
    public AllAnimalInfo create(Animal animal, AnimalFeeding animalFeeding, AnimalHealing animalHealing) {
        return new AllAnimalInfo(animal, animalFeeding, animalHealing);
    }
}
