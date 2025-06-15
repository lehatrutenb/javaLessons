package payment.orderService.adapters.ports.Out.kafka;

import com.google.protobuf.ByteString;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.micrometer.KafkaTemplateObservationConvention;
import org.springframework.stereotype.Component;
import payment.orderService.adapters.ports.Out.mapperProto.OutRequestProtoMapper;
import payment.orderService.adapters.ports.Out.mapperProto.OutResponseProtoMapper;
import payment.orderService.config.KafkaConfig;
import payment.orderService.config.KafkaProducerConfig;
import payment.orderService.useCases.dtos.requests.CreateOrderRequest;
import payment.orderService.useCases.events.OrderCreationEvent;
import payment.orderService.useCases.ishared.IorderCreationOutController;
import payments.orderService.grpc.OrderCreationEventRequestProto;

import java.util.Properties;

@Component
@RequiredArgsConstructor
public class CreateOrderKafkaController implements IorderCreationOutController, InitializingBean {
    private final KafkaTemplate<String, byte[]> kafkaTemplate;
    private final OutRequestProtoMapper outRequestProtoMapper;
    private final KafkaConfig kafkaConfig;
    private final KafkaProducerConfig<String, byte[]> kafkaProducerConfig;

    @Override
    public void handle(OrderCreationEvent request) {
        kafkaTemplate.send(kafkaConfig.getOrderAppliement().getTopicResponses(), String.valueOf(request.orderId().getId()),
                outRequestProtoMapper.map(request).toByteArray());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // kafkaTemplate = kafkaProducerConfig.reliableKafkaTemplate(); // acking will be enabled
    }
}
