package payment.paymentService.core.domainServices.factories;

import org.springframework.stereotype.Component;
import payment.paymentService.core.domainServices.iFactories.IwalletFactory;
import payment.paymentService.core.entities.UserId;
import payment.paymentService.core.entities.Wallet;

@Component
public class WalletFactory implements IwalletFactory {
    @Override
    public Wallet create(UserId userId, int moneyAmount) {
        return new Wallet(userId, moneyAmount);
    }
}
