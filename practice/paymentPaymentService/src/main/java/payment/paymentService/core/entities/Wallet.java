package payment.paymentService.core.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="wallets")
public class Wallet {
    @Id
    private int id;

    public WalletId getId() {
        return new WalletId(id);
    }

    public void setId(WalletId walletId) {
        id = walletId.getId();
    }

    public UserId getUserId() {
        return new UserId(id);
    }
    // no set user id not to have diff wallet&user ids
    @Getter
    @Setter
    @Column(nullable = false, name = "moneyAmount")
    private int moneyAmount;

    public Wallet(WalletId walletId, UserId userId, int moneyAmount) throws RuntimeException {
        if (walletId.getId() != userId.getId()) {
            throw new RuntimeException("expected walletId equal to userId");
        }
        id = walletId.getId();
        this.moneyAmount = moneyAmount;
    }

    public Wallet(UserId userId, int moneyAmount) throws RuntimeException {
        id = userId.getId();
        this.moneyAmount = moneyAmount;
    }
}
