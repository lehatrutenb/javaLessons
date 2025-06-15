package payment.paymentService.adapters.ports.In.grpc;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import payment.paymentService.adapters.ports.mappers.RequestProtoMapper;
import payment.paymentService.adapters.ports.mappers.ResponseProtoMapper;
import payment.paymentService.adapters.ports.mappers.SharedProtoMapper;
import payment.paymentService.grpc.*;
import payment.paymentService.useCases.iShared.Ifacade;
import payment.paymentService.iShared.IwrappedErrorFactory;
import payment.paymentService.shared.WrappedError;
import payment.paymentService.useCases.dtos.responses.CreateWalletResponse;
import payment.paymentService.useCases.dtos.responses.GetWalletBalanceResponse;
import payment.paymentService.useCases.dtos.responses.TopUpWalletResponse;

import java.util.Optional;

@RequiredArgsConstructor
@GrpcService
public class PaymentsGrpcInController extends PaymentServiceGrpc.PaymentServiceImplBase {
    private final Ifacade facade;
    private final SharedProtoMapper sharedMapped;
    private final RequestProtoMapper requestMapper;
    private final ResponseProtoMapper responseMapper;
    private final IwrappedErrorFactory wrappedErrorFactory;

    @Override
    public void createWallet(CreateWalletRequestProto request, StreamObserver<WrappedCreateWalletResponseProto> responseObserver) {
        WrappedError error = wrappedErrorFactory.create();
        Optional<CreateWalletResponse> response;
        try {
            response = facade.createWallet(requestMapper.map(request), error);
        } catch (RuntimeException e) {
            error.setCode(500);
            error.setMessage(e.getMessage());
            response = Optional.empty();
        }
        WrappedCreateWalletResponseProto responseProto;
        if (response.isEmpty()) {
            responseProto = WrappedCreateWalletResponseProto.newBuilder().setError(sharedMapped.map(error)).build();
        } else {
            responseProto = WrappedCreateWalletResponseProto.newBuilder()
                    .setCreateWalletResponse(responseMapper.map(response.get()))
                    .build();
        }

        responseObserver.onNext(responseProto);
        responseObserver.onCompleted();
    }

    @Override
    public void getWalletBalance(GetWalletBalanceRequestProto request, StreamObserver<WrappedGetWalletBalanceResponseProto> responseObserver) {
        WrappedError error = wrappedErrorFactory.create();
        Optional<GetWalletBalanceResponse> response;
        try {
            response = facade.getWalletBalance(requestMapper.map(request), error);
        } catch (RuntimeException e) {
            error.setCode(500);
            error.setMessage(e.getMessage());
            response = Optional.empty();
        }
        WrappedGetWalletBalanceResponseProto responseProto;
        if (response.isEmpty()) {
            responseProto = WrappedGetWalletBalanceResponseProto.newBuilder().setError(sharedMapped.map(error)).build();
        } else {
            responseProto = WrappedGetWalletBalanceResponseProto.newBuilder()
                    .setGetWalletBalanceResponse(responseMapper.map(response.get()))
                    .build();
        }

        responseObserver.onNext(responseProto);
        responseObserver.onCompleted();
    }

    @Override
    public void topUpWallet(TopUpWalletRequestProto request, StreamObserver<WrappedTopUpWalletResponseProto> responseObserver) {
        WrappedError error = wrappedErrorFactory.create();
        Optional<TopUpWalletResponse> response;
        try {
            response = facade.topUpWallet(requestMapper.map(request), error);
        } catch (RuntimeException e) {
            error.setCode(500);
            error.setMessage(e.getMessage());
            response = Optional.empty();
        }
        WrappedTopUpWalletResponseProto responseProto;
        if (response.isEmpty()) {
            responseProto = WrappedTopUpWalletResponseProto.newBuilder().setError(sharedMapped.map(error)).build();
        } else {
            responseProto = WrappedTopUpWalletResponseProto.newBuilder()
                    .setTopUpWalletResponse(responseMapper.map(response.get()))
                    .build();
        }

        responseObserver.onNext(responseProto);
        responseObserver.onCompleted();
    }
}
