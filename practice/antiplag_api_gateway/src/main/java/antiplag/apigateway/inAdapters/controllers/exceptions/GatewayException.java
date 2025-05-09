package antiplag.apigateway.inAdapters.controllers.exceptions;

import lombok.Getter;

@Getter
public class GatewayException extends RuntimeException {
    private final int code;
    public GatewayException(int code, String message) {
        super(message);
        this.code = code;
    }
}