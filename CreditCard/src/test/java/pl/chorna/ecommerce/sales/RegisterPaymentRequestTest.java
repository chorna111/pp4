package pl.chorna.ecommerce.sales;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import pl.chorna.ecommerce.catalog.sales.payment.RegisterPaymentRequest;
import pl.chorna.ecommerce.catalog.sales.reservation.AcceptOfferRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
public class RegisterPaymentRequestTest {
    @Test
    void  testRegisterPaymentRequestInitialization(){
        String resId="123";
        AcceptOfferRequest acceptOfferRequest=new AcceptOfferRequest()
                .setFirstName("John")
                .setLastName("Doe")
                .setEmail("john.doe@example.com");

        BigDecimal totalAmount = new BigDecimal("100.00");
        RegisterPaymentRequest request = RegisterPaymentRequest.of(resId, acceptOfferRequest, totalAmount);
        assertNotNull(request);
        assertEquals(resId, request.getReservationId());
        assertEquals(acceptOfferRequest, request.getAcceptOfferRequest());
        assertEquals(totalAmount, request.getTotal());
        assertEquals("John", request.getFirstname());
        assertEquals("Doe", request.getLastname());
        assertEquals("john.doe@example.com", request.getEmail());
        assertEquals(10000, request.getTotalAsPennies());
    }



}
