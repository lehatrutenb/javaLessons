package payment.apigateway.adapters.inPorts.http.exceptions;

import lombok.Getter;

@Getter
public class PaymentException extends RuntimeException {
    private final int code;
    public PaymentException(int code, String message) {
        super(message);
        this.code = code;
    }
}