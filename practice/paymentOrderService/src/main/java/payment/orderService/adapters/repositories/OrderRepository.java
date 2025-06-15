package payment.orderService.adapters.repositories;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import payment.orderService.core.entities.Order;
import payment.orderService.core.entities.OrderId;
import payment.orderService.core.entities.UserId;
import payment.orderService.useCases.ishared.IorderRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderRepository implements IorderRepository {
    private final IinnerOrderRepository innerOrderRepository;

    @Override
    public Order save(Order order) {
        return innerOrderRepository.save(order);
    }

    @Override
    public Optional<Order> findById(OrderId orderId) {
        try {
            return innerOrderRepository.findById(orderId.getId());
        } catch (Exception e) {
            log.warn(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<Order> findByUserId(UserId userId) {
        try {
            return innerOrderRepository.findByUserId(userId.getId());
        } catch (Exception e) {
            log.warn(e.getMessage());
            return List.of();
        }
    }
}
