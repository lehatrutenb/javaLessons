package payment.paymentService.core.domainServices.iFactories;

import payment.paymentService.core.domainServices.holders.IwalletIdHolder;
import payment.paymentService.core.entities.UserId;
import payment.paymentService.core.entities.WalletId;

public interface IwalletIdFactory {
    public WalletId create(int walletId);
    public WalletId create(IwalletIdHolder walletIdHolder);
    public WalletId create(UserId userId);
}
