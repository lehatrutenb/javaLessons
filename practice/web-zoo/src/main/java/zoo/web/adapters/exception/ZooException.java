package zoo.web.adapters.exception;

import lombok.Getter;

@Getter
public class ZooException extends RuntimeException {
    private final int code;

    public ZooException(int code, String message) {
        super(message);
        this.code = code;
    }
}