package payment.apigateway.shared;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

public class WrappedError {
    @Getter
    @Setter
    private int code;

    @Getter
    @Setter
    private String message;

    public boolean hasError() {
        return code != 0;
    }

    public WrappedError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public WrappedError() {
        code = 0;
        message = "";
    }
}
