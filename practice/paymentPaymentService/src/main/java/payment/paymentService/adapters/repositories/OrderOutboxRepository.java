package payment.paymentService.adapters.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import payment.paymentService.adapters.repositories.innerRepositories.IinnerOrderOutboxRepository;
import payment.paymentService.core.entities.OrderId;
import payment.paymentService.core.entities.OrderStatus;
import payment.paymentService.core.repositoryEntities.OrderOutbox;
import payment.paymentService.useCases.iShared.IorderOutboxRepository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OrderOutboxRepository implements IorderOutboxRepository {
    private final IinnerOrderOutboxRepository innerOrderOutboxRepository;

    @Override
    public OrderOutbox save(OrderOutbox order) {
        return innerOrderOutboxRepository.save(order);
    }

    @Override
    public Optional<OrderOutbox> findFirstByStatus(OrderStatus orderStatus) {
        return innerOrderOutboxRepository.findFirstByStatus(orderStatus);
    }

    @Override
    public void deleteById(OrderId orderId) {
        innerOrderOutboxRepository.deleteById(orderId.getId());
    }
}
