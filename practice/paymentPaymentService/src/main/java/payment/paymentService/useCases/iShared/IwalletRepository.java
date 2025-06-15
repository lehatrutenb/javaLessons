package payment.paymentService.useCases.iShared;

import payment.paymentService.adapters.repositories.innerRepositories.IinnerWalletRepository;
import payment.paymentService.core.entities.Wallet;
import payment.paymentService.core.entities.WalletId;

import java.util.Optional;

public interface IwalletRepository {
    public Wallet save(Wallet wallet);
    public Optional<Wallet> findById(WalletId walletId);
}
