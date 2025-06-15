package payment.paymentService.core.domainServices.factories;

import org.springframework.stereotype.Component;
import payment.paymentService.core.domainServices.holders.IwalletIdHolder;
import payment.paymentService.core.domainServices.iFactories.IwalletIdFactory;
import payment.paymentService.core.entities.UserId;
import payment.paymentService.core.entities.WalletId;

@Component
public class WalletIdFactory implements IwalletIdFactory {
    @Override
    public WalletId create(int walletId) {
        return new WalletId(walletId);
    }

    @Override
    public WalletId create(IwalletIdHolder walletIdHolder) {
        return new WalletId(walletIdHolder.walletId());
    }

    @Override
    public WalletId create(UserId userId) {
        return new WalletId(userId);
    }
}
