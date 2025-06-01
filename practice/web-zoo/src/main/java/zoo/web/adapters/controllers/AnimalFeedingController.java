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
import zoo.web.core.application_services.dtos.request.FeedingTimeRequest;
import zoo.web.core.application_services.dtos.response.AnimalResponse;
import zoo.web.core.application_services.dtos.response.FeedingTimeResponse;

@RestController
@RequestMapping("/api/animals/feed")
@RequiredArgsConstructor
@Tag(name = "Кормление животных", description = "Кормление животных")
public class AnimalFeedingController {
    private final Facade facade;

    @PostMapping
    @Operation(summary = "создать кормление по расписанию")
    public ResponseEntity<FeedingTimeResponse> createFeedingTime(
            @Valid @RequestBody FeedingTimeRequest request,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    bindingResult.getAllErrors().getFirst().getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(facade.addFeedingSchedule(request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить кормление по расписанию")
    public ResponseEntity<Void> deleteFeedingTime(@PathVariable String id) {
        facade.deleteFeedingSchedule(id);
        return ResponseEntity.ok().build();
    }
}