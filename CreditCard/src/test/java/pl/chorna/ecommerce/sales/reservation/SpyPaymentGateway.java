package pl.chorna.ecommerce.sales.reservation;

import pl.chorna.ecommerce.catalog.sales.payment.PaymentDetails;
import pl.chorna.ecommerce.catalog.sales.payment.PaymentGateway;
import pl.chorna.ecommerce.catalog.sales.payment.RegisterPaymentRequest;

public class SpyPaymentGateway implements PaymentGateway {
    Integer requestCount=0;
    public RegisterPaymentRequest lastRequest;

    public Integer getRequestCount() {
        return requestCount;
    }
    @Override
    public PaymentDetails registerPayment(RegisterPaymentRequest registerPaymentRequest){
        this.requestCount++;
        lastRequest=registerPaymentRequest;
        return new PaymentDetails("http://spy-gateway","1");
    }
}
