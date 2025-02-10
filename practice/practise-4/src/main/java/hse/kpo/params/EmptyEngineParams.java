package hse.kpo.params;

import org.springframework.stereotype.Component;

/**
 * Класс пустых параметров двигателя для двигаталей без параметров.
 */
public record EmptyEngineParams() {
    public static final EmptyEngineParams DEFAULT = new EmptyEngineParams();
}
