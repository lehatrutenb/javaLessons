package hse.kpo.controllers;

import hse.kpo.facade.Facade;
import hse.kpo.service.TrainingCenterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/training_center/")
@RequiredArgsConstructor
@Tag(name = "Тренировочный зал", description = "Управление тренировками позвоталетей")
public class TrainingController {
    private final Facade facade;

    @PostMapping("/train")
    @Operation(summary = "Потренировать пользователя")
    public ResponseEntity<Void> trainCustomer(
            @Valid @RequestBody TrainRequest request,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    bindingResult.getAllErrors().getFirst().getDefaultMessage());
        }

        facade.trainCustomer(request);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/")
    @Operation(summary = "Получить список пользователей")
    public ResponseEntity<List<CustomerResponse>> getCustomers() {
        return ResponseEntity.status(HttpStatus.CREATED).body(facade.getCustomers());
    }
}
