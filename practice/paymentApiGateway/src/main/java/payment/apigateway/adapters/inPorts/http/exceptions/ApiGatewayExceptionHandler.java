package payment.apigateway.adapters.inPorts.http.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "payment.apigateway")
public class ApiGatewayExceptionHandler {
    @ExceptionHandler(PaymentException.class)
    public ResponseEntity<PaymentException> handleKpoException(PaymentException ex) {
        return ResponseEntity.status(HttpStatus.valueOf(ex.getCode())).body(ex);
    }
    @ExceptionHandler(Error.class)
    public ResponseEntity<PaymentException> handleError(Error error) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new PaymentException(HttpStatus.INTERNAL_SERVER_ERROR.value(), error.getMessage()));
    }
}