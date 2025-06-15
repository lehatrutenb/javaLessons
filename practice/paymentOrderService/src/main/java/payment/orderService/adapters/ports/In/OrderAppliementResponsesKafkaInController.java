package payment.orderService.adapters.ports.In;

import com.google.protobuf.InvalidProtocolBufferException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.internals.Acknowledgements;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import payment.orderService.adapters.ports.In.mapperProto.InRequestProtoMapper;
import payment.orderService.config.KafkaConfig;
import payment.orderService.ishared.IwrappedErrorFactory;
import payment.orderService.shared.WrappedError;
import payment.orderService.useCases.ishared.Ifacade;
import payments.orderService.grpc.OrderCreationEventResponseProto;
import payments.orderService.grpc.WrappedOrderCreationEventResponseProto;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderAppliementResponsesKafkaInController {
    private final Ifacade facade;
    private final KafkaConfig kafkaConfig;
    private final IwrappedErrorFactory wrappedErrorFactory;
    private final InRequestProtoMapper requestProtoMapper;

    @KafkaListener(
            topics = "#{@kafkaConfig.orderAppliement.topicRequests}",
            groupId = "#{@kafkaConfig.orderAppliement.groupId}",
            containerFactory = "reliableKafkaListenerContainerFactory"
    )
    public void handleOrderCreationEvent(ConsumerRecord<String, byte[]> record, Acknowledgment acknowledgement) {
        byte[] event = record.value();
        WrappedError error = wrappedErrorFactory.create();
        try {
            facade.processOrderAppliementEvent(requestProtoMapper.map(OrderCreationEventResponseProto.parseFrom(event)), error);
            if (!error.hasError() || error.getCode() / 100 != 5) { // not 5xx
                acknowledgement.acknowledge();
            }
        } catch (RuntimeException | InvalidProtocolBufferException e) {
            log.warn(String.format("got excetion: %s", e.getMessage()));
        }
    }
}
