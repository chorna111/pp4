package pl.chorna.ecommerce.sales;
import org.junit.jupiter.api.Test;
import pl.chorna.ecommerce.catalog.sales.cart.CartStorage;
import pl.chorna.ecommerce.catalog.sales.offering.Offer;
import pl.chorna.ecommerce.catalog.sales.offering.OfferCalculator;
import pl.chorna.ecommerce.catalog.sales.reservation.ReservationRepository;
import pl.chorna.ecommerce.catalog.sales.SalesFacade;
import pl.chorna.ecommerce.sales.reservation.SpyPaymentGateway;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

public class SalesTest {

    @Test
    void itShowsCurrentOffer() {
        String customerId = thereIsCustomer("Kuba");
        SalesFacade sales = thereIsSales();

        Offer offer = sales.getCurrentOffer(customerId);

        assertThat(offer.getTotal()).isEqualTo(BigDecimal.ZERO);
        assertThat(offer.getItemsCount()).isEqualTo(0);
    }

    @Test
    void itAddsProductToCart() {
        String productId = thereIsExampleProduct("X", BigDecimal.valueOf(10));
        String customerId = thereIsCustomer("Kuba");
        SalesFacade sales = thereIsSales();

        sales.addProduct(customerId, productId);

        Offer offer = sales.getCurrentOffer(customerId);

        assertThat(offer.getTotal()).isEqualTo(BigDecimal.valueOf(10));
        assertThat(offer.getItemsCount()).isEqualTo(1);
    }

    private String thereIsExampleProduct(String name, BigDecimal price) {
        return name;
    }

    @Test
    void itAcceptCustomersCurrentOffer() {

    }

    @Test
    void itConfirmPayment() {

    }

    private SalesFacade thereIsSales() {

        return new SalesFacade(new CartStorage(),new OfferCalculator(),new SpyPaymentGateway(),new ReservationRepository());
    }

    private String thereIsCustomer(String name) {
        return name;
    }
}







