package payment.apigateway.adapters.outPorts.grpc.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import payment.apigateway.grpc.*;
import payment.apigateway.useCases.dtos.ifactories.IcreateWalletResponseFactory;
import payment.apigateway.useCases.dtos.ifactories.IgetWalletBalanceResponseFactory;
import payment.apigateway.useCases.dtos.ifactories.ItopUpWalletResponseFactory;
import payment.apigateway.useCases.dtos.requests.CreateWalletRequest;
import payment.apigateway.useCases.dtos.requests.GetWalletBalanceRequest;
import payment.apigateway.useCases.dtos.requests.TopUpWalletRequest;
import payment.apigateway.useCases.dtos.responses.CreateWalletResponse;
import payment.apigateway.useCases.dtos.responses.GetWalletBalanceResponse;
import payment.apigateway.useCases.dtos.responses.TopUpWalletResponse;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PaymentServiceMapper {
    private final SharedMapper mapper;
    private final IgetWalletBalanceResponseFactory getWalletBalanceResponseFactory;
    private final IcreateWalletResponseFactory createWalletResponseFactory;
    private final ItopUpWalletResponseFactory topUpWalletResponseFactory;

    public CreateWalletRequestProto map(CreateWalletRequest request) {
        return CreateWalletRequestProto.newBuilder()
                .setUserId(mapper.mapUserId(request.userId()))
                .build();
    }

    public Optional<CreateWalletResponse> map(WrappedCreateWalletResponseProto response) {
        if (response.hasError()) {
            return Optional.empty();
        }
        return Optional.of(
            createWalletResponseFactory.create(mapper.mapWalletId(response.getCreateWalletResponse().getWalletId()))
        );
    }

    public GetWalletBalanceRequestProto map(GetWalletBalanceRequest request) {
        return GetWalletBalanceRequestProto.newBuilder()
                .setWalletId(mapper.mapWalletId(request.walletId()))
                .build();
    }

    public Optional<GetWalletBalanceResponse> map(WrappedGetWalletBalanceResponseProto response) {
        if (response.hasError()) {
            return Optional.empty();
        }
        return Optional.of(
          getWalletBalanceResponseFactory.create(response.getGetWalletBalanceResponse().getAmount())
        );
    }

    public TopUpWalletRequestProto map(TopUpWalletRequest request) {
        return TopUpWalletRequestProto.newBuilder()
                .setWalletId(mapper.mapWalletId(request.walletId()))
                .setAmount(request.amount())
                .build();
    }

    public Optional<TopUpWalletResponse> map(WrappedTopUpWalletResponseProto response) {
        if (response.hasError()) {
            return Optional.empty();
        }
        return Optional.of(
          topUpWalletResponseFactory.create()
        );
    }
}
