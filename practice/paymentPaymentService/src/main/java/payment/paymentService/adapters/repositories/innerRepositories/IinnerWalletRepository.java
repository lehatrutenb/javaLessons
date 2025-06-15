package payment.paymentService.adapters.repositories.innerRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import payment.paymentService.core.entities.Wallet;

public interface IinnerWalletRepository extends JpaRepository<Wallet, Integer> {
}
