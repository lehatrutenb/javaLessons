package payment.orderService.adapters.ports;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import payment.orderService.adapters.ports.In.mapperProto.InRequestProtoMapper;
import payment.orderService.adapters.ports.In.mapperProto.InResponseProtoMapper;
import payment.orderService.grpc.*;
import payment.orderService.ishared.IwrappedErrorFactory;
import payment.orderService.shared.WrappedError;
import payment.orderService.useCases.ishared.Ifacade;

@RequiredArgsConstructor
@GrpcService
public class OrderServiceGrpcController extends OrderServiceGrpc.OrderServiceImplBase {
    private final Ifacade facade;
    private final IwrappedErrorFactory wrappedErrorFactory;
    private final InRequestProtoMapper requestMapper;
    private final InResponseProtoMapper responseMapper;

    @Override
    public void createOrder(CreateOrderRequestProto request, StreamObserver<WrappedCreateOrderResponseProto> streamObserver) {
        WrappedError error = wrappedErrorFactory.create();
        var response = facade.createOrder(requestMapper.map(request), error);
        streamObserver.onNext(responseMapper.mapCreateOrder(response, error));
        streamObserver.onCompleted();
    }

    @Override
    public void getOrderList(GetOrderListRequestProto request, StreamObserver<WrappedGetOrderListResponseProto> streamObserver) {
        WrappedError error = wrappedErrorFactory.create();
        var response = facade.getOrderListByUser(requestMapper.map(request), error);
        streamObserver.onNext(responseMapper.mapGetOrderList(response, error));
        streamObserver.onCompleted();
    }

    @Override
    public void getOrderMeta(GetOrderMetaRequestProto request, StreamObserver<WrappedGetOrderMetaResponseProto> streamObserver) {
        WrappedError error = wrappedErrorFactory.create();
        var response = facade.getOrderMeta(requestMapper.map(request), error);
        streamObserver.onNext(responseMapper.mapGetOrderMeta(response, error));
        streamObserver.onCompleted();
    }
}








































