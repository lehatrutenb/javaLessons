package payment.paymentService.core.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class WalletId {
    private final int id;

    public WalletId(UserId userId) {
        id = userId.getId();
    }
}
