package payment.orderService.shared;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class WrappedError {
    @Getter
    @Setter
    private int code;

    @Getter
    @Setter
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
