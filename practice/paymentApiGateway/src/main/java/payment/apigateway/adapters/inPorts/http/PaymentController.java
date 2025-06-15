package payment.apigateway.adapters.inPorts.http;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import payment.apigateway.Ishared.IwrappedErrorFactory;
import payment.apigateway.adapters.inPorts.http.exceptions.PaymentException;
import payment.apigateway.shared.WrappedError;
import payment.apigateway.useCases.dtos.ifactories.IgetWalletBalanceRequestFactory;
import payment.apigateway.useCases.dtos.requests.CreateWalletRequest;
import payment.apigateway.useCases.dtos.requests.TopUpWalletRequest;
import payment.apigateway.useCases.dtos.responses.CreateWalletResponse;
import payment.apigateway.useCases.dtos.responses.GetWalletBalanceResponse;
import payment.apigateway.useCases.dtos.responses.TopUpWalletResponse;
import payment.apigateway.useCases.iShared.IcreateWallet;
import payment.apigateway.useCases.iShared.IgetWalletBalance;
import payment.apigateway.useCases.iShared.ItopUpWallet;

@Slf4j
@RestController
@RequestMapping("/wallet")
@RequiredArgsConstructor
@Tag(name = "Контроллер для работы с кошельками")
public class PaymentController {
    private final IwrappedErrorFactory wrappedErrorFactory;
    private final IgetWalletBalanceRequestFactory getWalletBalanceRequestFactory;
    private final IcreateWallet createWallet;
    private final ItopUpWallet topUpWallet;
    private final IgetWalletBalance getWalletBalance;

    @PostMapping
    @Operation(summary = "создать кошелёк")
    public ResponseEntity<CreateWalletResponse> createWallet(
            @Valid @RequestBody CreateWalletRequest request,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    bindingResult.getAllErrors().getFirst().getDefaultMessage());
        }

        WrappedError error = wrappedErrorFactory.create();
        var response = createWallet.endHandle(request, error);
        if (response.isEmpty() || error.hasError()) {
            log.warn(String.format("failed with code: %d, message: %s", error.getCode(), error.getMessage()));
            throw new PaymentException(error.getCode(), error.getMessage());
            // return ResponseEntity.status(error.getCode()).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(response.get());
    }

    @PatchMapping
    @Operation(summary = "пополнить кошелёк")
    public ResponseEntity<TopUpWalletResponse> topUpWallet(
            @Valid @RequestBody TopUpWalletRequest request,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    bindingResult.getAllErrors().getFirst().getDefaultMessage());
        }

        WrappedError error = wrappedErrorFactory.create();
        var response = topUpWallet.endHandle(request, error);
        if (response.isEmpty() || error.hasError()) {
            log.warn(String.format("failed with code: %d, message: %s", error.getCode(), error.getMessage()));
            throw new PaymentException(error.getCode(), error.getMessage());
            // return ResponseEntity.status(error.getCode()).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(response.get());
    }

    @GetMapping("/{id}")
    @Operation(summary = "узнать баланс кошелька")
    public ResponseEntity<GetWalletBalanceResponse> getWalletBalance(
            @PathVariable Integer id
    ) {
        WrappedError error = wrappedErrorFactory.create();
        var response = getWalletBalance.endHandle(getWalletBalanceRequestFactory.create(id), error);
        if (response.isEmpty() || error.hasError()) {
            log.warn(String.format("failed with code: %d, message: %s", error.getCode(), error.getMessage()));
            throw new PaymentException(error.getCode(), error.getMessage());
            // return ResponseEntity.status(error.getCode()).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(response.get());
    }
}