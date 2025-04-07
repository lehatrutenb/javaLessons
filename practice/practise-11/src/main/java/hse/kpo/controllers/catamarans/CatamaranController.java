package hse.kpo.controllers.catamarans;

import hse.kpo.domains.Catamaran;
import hse.kpo.domains.cars.Car;
import hse.kpo.dto.CarRequest;
import hse.kpo.enums.EngineTypes;
import hse.kpo.facade.Hse;
import hse.kpo.requests.CatamaranRequest;
import hse.kpo.services.cars.HseCarService;
import hse.kpo.services.catamarans.HseCatamaranService;
import hse.kpo.storages.CarStorage;
import hse.kpo.storages.CatamaranStorage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/catamarans")
@RequiredArgsConstructor
@Tag(name = "Катамараны", description = "Управление транспортными водными средствами")
public class CatamaranController {
    private final CatamaranStorage catamaranStorage;
    private final HseCatamaranService catamaranService;
    private final Hse hseFacade;

    @GetMapping("/{vin}")
    @Operation(summary = "Получить катамаран по VIN")
    public ResponseEntity<Catamaran> getCatamaranByVin(@PathVariable int vin) {
        return catamaranStorage.getCatamarans().stream()
                .filter(catamaran -> catamaran.getVin() == vin)
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Создать катамаран",
            description = "Для PEDAL требуется pedalSize (1-15)")
    public ResponseEntity<Catamaran> createCatamaran(
            @Valid @RequestBody CatamaranRequest request,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        var engineType = EngineTypes.find(request.getEngineType());
        if (engineType.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "No this type");
        }

        var catamaran = switch (engineType.get()) {
            case EngineTypes.PEDAL -> hseFacade.addPedalCatamaran(request.getPedalSize());
            case EngineTypes.HAND -> hseFacade.addHandCatamaran();
            case EngineTypes.LEVITATION -> hseFacade.addLevitationCatamaran();
            default -> throw new RuntimeException();
        };

        return ResponseEntity.status(HttpStatus.CREATED).body(catamaran);
    }

    @PostMapping("/sell")
    @Operation(summary = "Продать все доступные катамараны")
    public ResponseEntity<Void> sellAllCars() {
        catamaranService.sellCatamarans();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/sell/{vin}")
    @Operation(summary = "Продать катамаран по VIN")
    public ResponseEntity<Void> sellCatamaran(@PathVariable int vin) {
        var catamaranOptional = catamaranStorage.getCatamarans().stream()
                .filter(c -> c.getVin() == vin)
                .findFirst();

        if (catamaranOptional.isPresent()) {
            var catamaran = catamaranOptional.get();
            catamaranStorage.getCatamarans().remove(catamaran);
            // Логика продажи (упрощенно)
            hseFacade.sell();
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{vin}")
    @Operation(summary = "Обновитьrf")
    public ResponseEntity<Car> updateCatamaran(
            @PathVariable int vin,
            @Valid @RequestBody CarRequest request) {

        return carStorage.getCars().stream()
                .filter(car -> car.getVin() == vin)
                .findFirst()
                .map(existingCar -> {
                    var updatedCar = createCarFromRequest(request, vin);
                    carStorage.getCars().remove(existingCar);
                    carStorage.addExistingCar(updatedCar);
                    return ResponseEntity.ok(updatedCar);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{vin}")
    @Operation(summary = "Удалить автомобиль")
    public ResponseEntity<Void> deleteCar(@PathVariable int vin) {
        boolean removed = carStorage.getCars().removeIf(car -> car.getVin() == vin);
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
