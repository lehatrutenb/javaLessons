package payment.paymentService.adapters.ports.In.kafka;

import com.google.protobuf.InvalidProtocolBufferException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import payment.paymentService.adapters.ports.mappers.RequestProtoMapper;
import payment.paymentService.config.KafkaConfig;
import payment.paymentService.useCases.iShared.Ifacade;
import payment.paymentService.iShared.IwrappedErrorFactory;
import payment.paymentService.shared.WrappedError;
import payments.paymentService.grpc.OrderCreationEventRequestProto;
import payments.paymentService.grpc.OrderCreationEventResponseProto;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderAppliementRequestsKafkaInController {
    private final Ifacade facade;
    private final KafkaConfig kafkaConfig;
    private final IwrappedErrorFactory wrappedErrorFactory;
    private final RequestProtoMapper requestProtoMapper;

    @KafkaListener(
            topics = "#{@kafkaConfig.orderAppliement.topicRequests}",
            groupId = "#{@kafkaConfig.orderAppliement.groupId}",
            containerFactory = "reliableKafkaListenerContainerFactory"
    )
    public void handleOrderCreationEvent(ConsumerRecord<String, byte[]> record, Acknowledgment acknowledgment) {
        log.info("got new order creation event");

        byte[] event = record.value();
        OrderCreationEventRequestProto eventProto;
        WrappedError error = wrappedErrorFactory.create();
        try {
            eventProto = OrderCreationEventRequestProto.parseFrom(event);
            facade.processOrderAppliementRequest(requestProtoMapper.map(eventProto), error);
        } catch (InvalidProtocolBufferException e) {
            log.warn(String.format("failed to consume message from kafka: %s", e.getMessage()));
            throw new RuntimeException(e);
        } catch (RuntimeException e) {
            log.warn(String.format("failed to process event", e.getMessage()));
            throw new RuntimeException(e);
        }
        if (!error.hasError() || error.getCode() / 100 != 5) { // not 5xx
            acknowledgment.acknowledge();
        }
    }
}

