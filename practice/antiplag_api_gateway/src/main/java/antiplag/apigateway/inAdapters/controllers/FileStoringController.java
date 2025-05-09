package antiplag.apigateway.inAdapters.controllers;

import antiplag.apigateway.use_cases.dtos.requests.FileLoadRequest;
import antiplag.apigateway.use_cases.dtos.requests.FileStoreRequest;
import antiplag.apigateway.use_cases.dtos.responses.FileBodyResponse;
import antiplag.apigateway.use_cases.dtos.responses.FileMetaResponse;
import antiplag.apigateway.use_cases.services.StoringService;
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
@RequestMapping("/antiplag/store")
@RequiredArgsConstructor
@Tag(name = "Проверка на антиплагиат : работа с файлами", description = "Проверка на антиплагиат : работа с файлами")
public class FileStoringController {
    private final StoringService storingService;

    @PostMapping
    @Operation(summary = "загрузить файл")
    public ResponseEntity<FileMetaResponse> storeFile(@Valid @RequestBody FileStoreRequest request,
                                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    bindingResult.getAllErrors().getFirst().getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(storingService.storeFile(request));
    }

    @GetMapping("/{id")
    @Operation(summary = "выгрузить файл")
    public ResponseEntity<FileBodyResponse> loadFile(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(storingService.loadFile(new FileLoadRequest(id)));
    }
}