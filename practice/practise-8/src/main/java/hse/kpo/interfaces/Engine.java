package hse.kpo.interfaces;


import hse.kpo.domains.customers.Customer;
import hse.kpo.enums.ProductionTypes;

import javax.xml.bind.annotation.XmlTransient;

@XmlTransient
public interface Engine {

    /**
     * Метод для проверки совместимости двигателя с покупателем.
     *
     * @param customer - покупатель, с которым мы сравниваем двигатель
     * @param type - тип объекта
     * @return true, если двигатель подходит покупателю
     */
    boolean isCompatible(Customer customer, ProductionTypes type);
}
