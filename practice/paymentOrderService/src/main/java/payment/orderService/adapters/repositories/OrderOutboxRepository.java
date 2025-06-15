package payment.orderService.adapters.repositories;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import payment.orderService.core.entities.Order;
import payment.orderService.core.entities.OrderId;
import payment.orderService.core.entities.OrderStatus;
import payment.orderService.core.repositoryEntities.OrderOutbox;
import payment.orderService.useCases.ishared.IorderOutboxRepository;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderOutboxRepository implements IorderOutboxRepository {
    private final IinnerOrderOutboxRepository outboxRepository;

    @Override
    public OrderOutbox save(OrderOutbox order) {
        return outboxRepository.save((OrderOutbox) order);
    }

    @Override
    public Optional<OrderOutbox> findFirstByStatus(OrderStatus orderStatus) {
        try {
            return outboxRepository.findFirstByStatus(orderStatus);
        } catch (RuntimeException e) {
            log.warn(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public void deleteById(OrderId orderId) {
        outboxRepository.deleteById(orderId.getId());
    }
}
