package payment.paymentService.adapters.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import payment.paymentService.adapters.repositories.innerRepositories.IinnerOrderRepository;
import payment.paymentService.core.entities.Order;
import payment.paymentService.core.entities.OrderId;
import payment.paymentService.useCases.iShared.IorderRepository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OrderRepository implements IorderRepository {
    private final IinnerOrderRepository innerOrderRepository;

    @Override
    public Order save(Order order) {
        return innerOrderRepository.save(order);
    }

    @Override
    public Optional<Order> findById(OrderId orderId) {
        return innerOrderRepository.findById(orderId.getId());
    }
}
