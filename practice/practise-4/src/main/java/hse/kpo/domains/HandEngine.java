package hse.kpo.domains;

import hse.kpo.interfaces.Iengine;
import lombok.ToString;

/**
 * Класс двигателя зависящего от силы рук.
 */
@ToString
public class HandEngine implements Iengine {
    /**
     * Проверка совместимости с пользователем.
     *
     * @return true если сила рук пользователя > 5, false иначе
     */
    @Override
    public boolean isCompatible(Customer customer) {
        return customer.getHandPower() > 5;
    }
}
