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
import zoo.web.core.application_services.dtos.request.EnclosureRequest;
import zoo.web.core.application_services.dtos.request.FeedingTimeRequest;
import zoo.web.core.application_services.dtos.response.AnimalResponse;
import zoo.web.core.application_services.dtos.response.EnclosureResponse;
import zoo.web.core.application_services.dtos.response.FeedingTimeResponse;

import java.util.List;

@RestController
@RequestMapping("/api/enclosures")
@RequiredArgsConstructor
@Tag(name = "Вольеры", description = "Управление вольерами")
public class EnclosuresController {
    private final Facade facade;

    @PostMapping
    @Operation(summary = "создать вольер")
    public ResponseEntity<EnclosureResponse> createEnclosure(
            @Valid @RequestBody EnclosureRequest request,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    bindingResult.getAllErrors().getFirst().getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(facade.addEnclosure(request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить вольер")
    public ResponseEntity<Void> deleteEnclosure(@PathVariable String id) {
        facade.deleteEnclosure(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @Operation(summary = "Получить все вольеры")
    public ResponseEntity<List<EnclosureResponse>> getEnclosures() {
        return ResponseEntity.status(HttpStatus.OK).body(facade.getEnclosures());
    }
}