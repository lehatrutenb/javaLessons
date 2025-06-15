package payment.paymentService.useCases.iShared;

import payment.paymentService.core.entities.UserId;
import payment.paymentService.core.entities.WalletId;
import payment.paymentService.shared.WrappedError;

import java.util.Optional;

public interface IpaymentService {
    public Optional<WalletId> createWallet(UserId userId, WrappedError error);
    public Optional<Integer> getWalletBalance(WalletId walletId, WrappedError error);
    public void topUpWallet(WalletId walletId, int amount, WrappedError error);
    public void deductFromWallet(WalletId walletId, int amount, WrappedError error);
}
