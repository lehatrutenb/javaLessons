package payment.paymentService.adapters.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import payment.paymentService.adapters.repositories.innerRepositories.IinnerWalletRepository;
import payment.paymentService.core.entities.Wallet;
import payment.paymentService.core.entities.WalletId;
import payment.paymentService.useCases.iShared.IwalletRepository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class WalletRepository implements IwalletRepository {
    private final IinnerWalletRepository innerWalletRepository;
    @Override
    public Wallet save(Wallet wallet) {
        return innerWalletRepository.save(wallet);
    }

    @Override
    public Optional<Wallet> findById(WalletId walletId) {
        return innerWalletRepository.findById(walletId.getId());
    }
}
