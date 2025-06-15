package payment.paymentService.core.domainServices.iFactories;

import payment.paymentService.core.entities.UserId;
import payment.paymentService.core.entities.Wallet;

public interface IwalletFactory {
    public Wallet create(UserId userId, int moneyAmount);
}
