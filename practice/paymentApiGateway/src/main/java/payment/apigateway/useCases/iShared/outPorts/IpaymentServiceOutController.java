package payment.apigateway.useCases.iShared.outPorts;

import payment.apigateway.useCases.iShared.IgetWalletBalance;

public interface IpaymentServiceOutController extends IgetWalletBalanceOutPort, IcreateWalletOutPort, ItopUpWalletOutPort {
}
