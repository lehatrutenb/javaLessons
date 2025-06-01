package antiplag.apigateway.inAdapters.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "antiplag.apigateway")
public class GatewayExceptionHandler {
    @ExceptionHandler(GatewayException.class)
    public ResponseEntity<GatewayException> handleException(GatewayException ex) {
        return ResponseEntity.status(HttpStatus.valueOf(ex.getCode())).body(ex);
    }
    @ExceptionHandler(Error.class)
    public ResponseEntity<GatewayException> handleError(Error error) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new GatewayException(HttpStatus.INTERNAL_SERVER_ERROR.value(), error.getMessage()));
    }
}
