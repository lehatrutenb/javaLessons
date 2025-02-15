package zoo.services;

import io.vavr.control.Either;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zoo.domains.Report;
import zoo.domains.animals.Animal;
import zoo.domains.animals.AnimalAttributes;
import zoo.domains.animals.Herbo;
import zoo.domains.animals.Wolf;
import zoo.domains.nonLThings.Thing;
import zoo.domains.nonLThings.ThingAttributes;
import zoo.interfaces.IanimalStorage;
import zoo.interfaces.IclinicService;

import java.util.List;
import java.util.Optional;

@Component
public class ZooService {
    private final int KindnessContactMin = 5;
    @Autowired
    private AnimalStorage animalStorage;

    @Autowired
    private ThingStorage thingStorage;

    @Autowired
    private ReportBuilder reportBuilder;

    @Autowired
    private AnimalParsingService animalParsingService;

    @Autowired
    private ThingParsingService thingParsingService;

    private int number=0;

    private IclinicService clinicService;

    public void SetClinicService(IclinicService clinicService) {
        this.clinicService = clinicService;
    }

    public IclinicService GetClinicService() {
        return clinicService;
    }

    public ZooService() {
    }

    public Optional<String> AddAnimal(String line) {
        var parsed = animalParsingService.Parse(line, number);
        if (parsed.isLeft()) {
            return Optional.of(parsed.getLeft());
        }
        if (clinicService != null && !clinicService.CheckAnimal(parsed.get())) {
            return Optional.of("clinic service refused animal");
        }
        animalStorage.AddAnimal(parsed.get());
        number++;
        return Optional.empty();
    }

    public Optional<String> AddThing(String line) {
        var parsed = thingParsingService.Parse(line, number);
        if (parsed.isLeft()) {
            return Optional.of(parsed.getLeft());
        }
        thingStorage.AddThing(parsed.get());
        number++;
        return Optional.empty();
    }

    public int getNeedFoodAmt() {
        return animalStorage.GetAnimals().stream().mapToInt(Animal::GetFood).sum();
    }

    public Report GetNeedFoodAmt() {
        reportBuilder.Clear();
        reportBuilder.AddTaggedElement("Required food amount", Integer.toString(getNeedFoodAmt()));
        return reportBuilder.GetReport();
    }

    private List<Animal> getContactFineAnimals() {
        return animalStorage.GetAnimals().stream().filter
                (animal -> animal instanceof Herbo &&((Herbo) animal).GetKindness() > KindnessContactMin).toList();
    }

    public Report GetContactFineAnimals() {
        reportBuilder.Clear();
        reportBuilder.AddTaggedList("Animals for contact zoo", getContactFineAnimals());
        return reportBuilder.GetReport();
    }

    public Report GetAnimals() {
        reportBuilder.Clear();
        reportBuilder.AddTaggedList("Zoo's animals", animalStorage.GetAnimals());
        return reportBuilder.GetReport();
    }

    public Report GetInventory() {
        reportBuilder.Clear();
        var liveInventory = animalStorage.GetAnimals().stream().map(animal -> (Thing) animal).toList();
        var notLiveInventory =  thingStorage.GetThings();
        notLiveInventory.addAll(liveInventory);
        reportBuilder.AddTaggedList("Zoo's inventory", notLiveInventory);
        return reportBuilder.GetReport();
    }
}
