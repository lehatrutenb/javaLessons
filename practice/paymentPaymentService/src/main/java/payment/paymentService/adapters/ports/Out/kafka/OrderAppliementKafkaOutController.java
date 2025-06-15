package payment.paymentService.adapters.ports.Out.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import payment.paymentService.adapters.ports.mappers.ResponseProtoMapper;
import payment.paymentService.config.KafkaConfig;
import payment.paymentService.config.KafkaProducerConfig;
import payment.paymentService.useCases.dtos.responses.OrderAppliementResponse;
import payment.paymentService.useCases.iShared.IorderAppliementOutController;
import payments.paymentService.grpc.WrappedOrderCreationEventResponseProto;

@Component
@RequiredArgsConstructor
public class OrderAppliementKafkaOutController implements IorderAppliementOutController, InitializingBean {
    private KafkaTemplate<String, byte[]> kafkaTemplate;
    private final ResponseProtoMapper mapper;
    private final KafkaConfig kafkaConfig;
    private final KafkaProducerConfig<String, byte[]> kafkaProducerConfig;

    @Override
    public void orderAppliementSend(OrderAppliementResponse orderAppliementResponse) {
        kafkaTemplate.send(kafkaConfig.getOrderAppliement().getTopicResponses(), String.valueOf(orderAppliementResponse.orderId()),
                mapper.map(orderAppliementResponse).toByteArray());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        kafkaTemplate = kafkaProducerConfig.reliableKafkaTemplate(); // acking will be enabled
    }
}
