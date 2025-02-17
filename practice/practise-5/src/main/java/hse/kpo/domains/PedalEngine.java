package hse.kpo.domains;

import hse.kpo.interfaces.Iengine;
import lombok.Getter;
import lombok.ToString;

/**
 * Класс двигателя работающего на силе ног пользователя.
 */
@ToString
@Getter
public class PedalEngine implements Iengine {
    private final int size;

    /**
     * Проверка совместимости с пользователем.
     *
     * @return true если сила ног пользователя > 5, false иначе
     */
    @Override
    public boolean isCompatible(Customer customer) {
        return customer.getLegPower() > 5;
    }

    public PedalEngine(int size) {
        this.size = size;
    }
}
