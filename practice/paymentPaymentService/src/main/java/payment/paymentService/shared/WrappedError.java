package payment.paymentService.shared;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class WrappedError {
    private int code;
    private String message;
    public WrappedError(int code, String message) {
        this.code = code;
        this.message = message;
    }
    public WrappedError() {
        code = 0;
        message = "";
    }
    public boolean hasError() {
        return code != 0;
    }
}
