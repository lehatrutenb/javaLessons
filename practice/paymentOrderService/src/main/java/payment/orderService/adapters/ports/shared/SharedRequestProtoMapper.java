package payment.orderService.adapters.ports.shared;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import payment.orderService.grpc.*;
import payment.orderService.ishared.IwrappedErrorFactory;
import payment.orderService.shared.WrappedError;

@Component
@RequiredArgsConstructor
public class SharedRequestProtoMapper {
    private final IwrappedErrorFactory wrappedErrorFactory;

    public int map(UserIdProto userIdProto) { // safe to use only to pass to next funcs
        return userIdProto.getId();
    }

    public int map(OrderIdProto orderIdProto) {  // safe to use only to pass to next funcs
        return orderIdProto.getId();
    }

    public String map(OrderDescriptionProto descriptionProto) {  // safe to use only to pass to next funcs
        return descriptionProto.getText();
    }

    public String map(OrderStatusProto orderStatusProto) {
        return orderStatusProto.name();
    }

    public void map(ErrorProto error, WrappedError resError) {
        resError.setCode(error.getCode());
        resError.setMessage(error.getMessage());
    }
}
