package hse.kpo.domains;

import hse.kpo.interfaces.Iengine;
import lombok.ToString;

/**
 * Класс двигателя, работающего на iq пользователя.
 */
@ToString
public class ReviveEngine implements Iengine {
    /**
     * Проверка совместимости с пользователем.
     *
     * @return true если iq пользователя > 300, false иначе
     */
    @Override
    public boolean isCompatible(Customer customer) {
        return customer.iq > 300;
    }
}
