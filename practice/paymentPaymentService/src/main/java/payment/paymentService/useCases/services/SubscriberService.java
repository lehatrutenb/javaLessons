package payment.paymentService.useCases.services;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import payment.paymentService.useCases.iShared.IorderAppliementEventHandler;

// not using event java subscribers cause of lack of support of transactions for them
@Service
public class SubscriberService implements InitializingBean {
    @Autowired
    private OrderService orderService;

    @Autowired
    private IorderAppliementEventHandler orderAppliementEventHandler;

    @Override
    public void afterPropertiesSet() throws Exception {
        orderService.subscribeOnOrderCreation(orderAppliementEventHandler);
    }
}
