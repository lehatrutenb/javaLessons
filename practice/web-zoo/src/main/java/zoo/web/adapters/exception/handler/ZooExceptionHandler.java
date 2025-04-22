package zoo.web.adapters.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import zoo.web.adapters.exception.ZooException;

@RestControllerAdvice(basePackages = "zoo.web")
public class ZooExceptionHandler {
    @ExceptionHandler(ZooException.class)
    public ResponseEntity<ZooException> handleKpoException(ZooException ex) {
        return ResponseEntity.status(HttpStatus.valueOf(ex.getCode())).body(ex);
    }

    @ExceptionHandler(Error.class)
    public ResponseEntity<ZooException> handleError(Error error) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ZooException(HttpStatus.INTERNAL_SERVER_ERROR.value(), error.getMessage()));
    }
}
