package payment.orderService.useCases.services;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import payment.orderService.useCases.ishared.IorderCreationHandler;
import payment.orderService.useCases.ishared.IorderService;

import javax.annotation.PostConstruct;

// not using event java subscribers cause of lack of support of transactions for them
@Service
public class SubscriberService implements InitializingBean {
    @Autowired
    private OrderService orderService;

    @Autowired
    private IorderCreationHandler orderCreationHandler;

    @Override
    public void afterPropertiesSet() throws Exception {
        orderService.subscribeOnOrderCreation(orderCreationHandler);
    }
}
