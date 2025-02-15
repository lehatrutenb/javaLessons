package zoo.services;

import io.vavr.control.Either;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zoo.domains.animals.Animal;
import zoo.domains.nonLThings.Thing;
import zoo.domains.nonLThings.ThingAttributes;
import zoo.factories.*;
import zoo.types.*;

import java.util.Scanner;

@Component
public class ThingParsingService {
    @Autowired
    private TableFactory tableFactory;
    @Autowired
    private ComputerFactory computerFactory;

    public Either<String, Thing> Parse(String line, int number) {
        Scanner scanner = new Scanner(line);
        if (!scanner.hasNext()) {
            return Either.left("expected thing type as first word");
        }
        var type = scanner.next();
        if (!type.equals(TableType.Name) && !type.equals(ComputerType.Name)) {
            return Either.left("got unknown type of thing");
        }
        var thingAttrs = new ThingAttributes(number, type);
        return switch (type) {
            case TableType.Name -> Either.right(tableFactory.CreateThing(thingAttrs));
            case ComputerType.Name -> Either.right(computerFactory.CreateThing(thingAttrs));
            default -> Either.left("unexpected error - can't define thing type");
        };
    }
}
