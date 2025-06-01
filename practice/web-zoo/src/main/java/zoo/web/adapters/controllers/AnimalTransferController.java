package zoo.web.adapters.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import zoo.web.core.application_services.Facade;
import zoo.web.core.application_services.dtos.request.AnimalRequest;
import zoo.web.core.application_services.dtos.response.AnimalResponse;

@RestController
@RequestMapping("/api/animals")
@RequiredArgsConstructor
@Tag(name = "Животные", description = "Управление животными")
public class AnimalTransferController {
    private final Facade facade;

    @PostMapping("/{enclosureId}")
    @Operation(summary = "Создать животного")
    public ResponseEntity<AnimalResponse> createAnimal(
            @Valid @RequestBody AnimalRequest request,
            @PathVariable String enclosureId,
            BindingResult bindingResult) throws Throwable {

        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    bindingResult.getAllErrors().getFirst().getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(facade.addAnimal(request, enclosureId));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить животное")
    public ResponseEntity<Void> deleteAnimal(@PathVariable String id) {
        facade.deleteAnimal(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{animalId}/{enclosureId}")
    @Operation(summary = "Переместить животное")
    public ResponseEntity<Void> moveAnimal(@PathVariable String animalId, @PathVariable String enclosureId) throws Throwable {
        facade.moveAnimal(animalId, enclosureId);
        return ResponseEntity.ok().build();
    }
}