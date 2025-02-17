package hse.kpo.params;

/**
 * Класс пустых параметров двигателя для двигаталей без параметров.
 */
public record EmptyEngineParams() {
    public static final EmptyEngineParams DEFAULT = new EmptyEngineParams();
}
