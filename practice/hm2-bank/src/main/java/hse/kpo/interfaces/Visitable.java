package hse.kpo.interfaces;

import java.io.IOException;

public interface Visitable {
    public void visit(BankVisitorI bankVisitorI)  throws Throwable;
}
