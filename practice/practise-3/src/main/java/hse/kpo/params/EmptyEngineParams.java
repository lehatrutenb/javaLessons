package hse.kpo.params;

import org.springframework.stereotype.Component;

public record EmptyEngineParams() {
    public static final EmptyEngineParams DEFAULT = new EmptyEngineParams();
}
