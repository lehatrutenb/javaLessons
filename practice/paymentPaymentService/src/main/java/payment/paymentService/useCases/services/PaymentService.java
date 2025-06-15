package payment.paymentService.useCases.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import payment.paymentService.core.domainServices.holders.IwalletIdHolder;
import payment.paymentService.core.domainServices.iFactories.IwalletFactory;
import payment.paymentService.core.domainServices.iFactories.IwalletIdFactory;
import payment.paymentService.core.entities.UserId;
import payment.paymentService.core.entities.Wallet;
import payment.paymentService.core.entities.WalletId;
import payment.paymentService.shared.WrappedError;
import payment.paymentService.useCases.iShared.IpaymentService;
import payment.paymentService.useCases.iShared.IwalletRepository;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService implements IpaymentService {
    private final IwalletRepository walletRepository;
    private final IwalletFactory walletFactory;
    private final IwalletIdFactory walletIdFactory;

    private void noSuchWallet(WrappedError error) {
        log.info("not found wallet");
        error.setCode(404);
        error.setMessage("No such wallet found");
    }
    private void notEnoughMoney(WrappedError error) {
        log.info("not enough money on wallet");
        error.setCode(400);
        error.setMessage("Not enough money on wallet");
    }


    @Override
    public Optional<WalletId> createWallet(UserId userId, WrappedError error) {
        if (walletRepository.findById(walletFactory.create(userId, 0).getId()).isPresent()) {
            error.setCode(400);
            error.setMessage("wallet for that userId already exists");
            return Optional.empty();
        }
        return Optional.of(
                walletRepository.save(walletFactory.create(userId, 0)).getId()
        );
    }

    @Override
    public Optional<Integer> getWalletBalance(WalletId walletId, WrappedError error) {
        var response = walletRepository.findById(walletId).map(Wallet::getMoneyAmount);
        if (response.isEmpty()) {
            noSuchWallet(error);
        }
        return response;
    }

    @Override
    @Transactional(value = Transactional.TxType.SUPPORTS)
    public void topUpWallet(WalletId walletId, int amount, WrappedError error) {
        Optional<Wallet> wallet = walletRepository.findById(walletId);
        if (wallet.isEmpty()) {
            noSuchWallet(error);
            return;
        }
        wallet.get().setMoneyAmount(wallet.get().getMoneyAmount() + amount);
        walletRepository.save(wallet.get());
    }

    @Override
    @Transactional(value = Transactional.TxType.SUPPORTS)
    public void deductFromWallet(WalletId walletId, int amount, WrappedError error) {
        Optional<Wallet> wallet = walletRepository.findById(walletId);
        if (wallet.isEmpty()) {
            noSuchWallet(error);
            return;
        }
        if (wallet.get().getMoneyAmount() < amount) {
            notEnoughMoney(error);
            return;
        }
        wallet.get().setMoneyAmount(wallet.get().getMoneyAmount() - amount);
        walletRepository.save(wallet.get());
    }
}
