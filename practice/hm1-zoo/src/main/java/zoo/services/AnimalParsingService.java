package zoo.services;

import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zoo.domains.animals.Animal;
import zoo.domains.animals.AnimalAttributes;
import zoo.domains.animals.Herbo;
import zoo.domains.animals.HerboAttributes;
import zoo.domains.nonLThings.ThingAttributes;
import zoo.factories.MonkeyFactory;
import zoo.factories.RabbitFactory;
import zoo.factories.WolfFactory;

import io.vavr.control.Either;
import zoo.types.MonkeyType;
import zoo.types.RabbitType;
import zoo.types.WolfType;

import java.util.Optional;
import java.util.Scanner;

@Component
public class AnimalParsingService {
    @Autowired
    private MonkeyFactory monkeyFactory;
    @Autowired
    private RabbitFactory rabbitFactory;
    @Autowired
    private WolfFactory wolfFactory;

    public Either<String, Animal> Parse(String line, int number) {
        Scanner scanner = new Scanner(line);
        if (!scanner.hasNext()) {
            return Either.left("expected animal type as first word");
        }
        var type = scanner.next();
        if (!type.equals(MonkeyType.Name) && !type.equals(RabbitType.Name) && !type.equals(WolfType.Name)) {
            return Either.left("got unknown type of animal");
        }
        var thingAttrs = new ThingAttributes(number, type);
        if (!scanner.hasNextInt()) {
            return Either.left("expected animal food need after its type");
        }
        var food = scanner.nextInt();
        if (Herbo.IsAnimalHerbo(type)) {
            if (!scanner.hasNextInt()) {
                return Either.left("expected animal kindness amount after its type");
            }
            var kindness = scanner.nextInt();
            var herboAttrs = new HerboAttributes(food, kindness);
            return switch (type) {
                case RabbitType.Name -> Either.right(rabbitFactory.CreateAnimal(herboAttrs, thingAttrs));
                case MonkeyType.Name -> Either.right(monkeyFactory.CreateAnimal(herboAttrs, thingAttrs));
                default -> Either.left("unexpected error - can't define animal herbo type");
            };
        }
        return Either.right(wolfFactory.CreateAnimal(new AnimalAttributes(food), thingAttrs));
    }
}
