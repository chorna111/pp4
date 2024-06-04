package pl.chorna.ecommerce.PayU;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.web.client.RestTemplate;
import pl.chorna.ecommerce.PayU.PayU;
import java.util.Arrays;
import java.util.UUID;

public class PayUTest {
    @Test
    void itAllowsToRegisterPayment(){
        PayU payu=thereIsPayU();
        OrderCreateRequest request=thereIsExamplePaymentRegisterRequest();
        OrderCreateResponse response=payu.handle(request);
        assertNotNull(response.getRedirectUri());
        assertNotNull(response.getOrderId());
        assertNotNull(response.getExtOrderId());

    }

    private OrderCreateRequest thereIsExamplePaymentRegisterRequest() {
        var request=new OrderCreateRequest();
        request
                .setNotifyUrl("https://your.eshop.com/notify")
                .setCustomerIp("127.0.0.1")
                .setMerchantPosId("300746")
                .setDescription("My ebook store")
                .setCurrencyCode("PLN")
                .setTotalAmount(21000)
                .setExtOrderId(UUID.randomUUID().toString())
                .setBuyer(new Buyer()
                        .setEmail("johndoe@example.com")
                        .setPhone("654111654")
                        .setFirstName("John")
                        .setLastName("Doe")
                        .setLanguage("pl")

                )
                .setProducts(Arrays.asList(
                        new Product()
                                .setName("Ebook x")
                                .setQuantity(1)
                                .setUnitPrice(21000)
                ));
        return request;
    }

    private PayU thereIsPayU() {
        return new PayU(new RestTemplate(),
                PayUCredentials.sandbox("300746",
                        "2ee86a66e5d97e3fadc400c9f19b065d"));
    }

}
