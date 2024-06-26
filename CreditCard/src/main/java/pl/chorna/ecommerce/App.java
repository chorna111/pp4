package pl.chorna.ecommerce;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import pl.chorna.ecommerce.PayU.PayUCredentials;
import pl.chorna.ecommerce.catalog.HasMapProductStorage;
import pl.chorna.ecommerce.catalog.ProductCatalog;
import pl.chorna.ecommerce.catalog.sales.cart.CartStorage;
import pl.chorna.ecommerce.catalog.sales.reservation.ReservationRepository;
import pl.chorna.ecommerce.PayU.PayU;
import pl.chorna.ecommerce.catalog.sales.offering.OfferCalculator;
import pl.chorna.ecommerce.catalog.sales.SalesFacade;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        System.out.println("here we go!!!!");
        SpringApplication.run(App.class, args);
    }

    @Bean
    ProductCatalog createMyProductCatalog() {


        var catalog = new ProductCatalog(new HasMapProductStorage());
        catalog.addProduct("Lego set 8083", "Nice one");
        catalog.addProduct("Cobi blocks", "Nice one");
        catalog.addProduct("Cobi blocks", "Nice one");

        return catalog;
    }
    @Bean
    SalesFacade createSales(){
        return new SalesFacade(new CartStorage(), new OfferCalculator(), new PayU(new RestTemplate(),
                PayUCredentials.sandbox(
                        "300746",
                        "2ee86a66e5d97e3fadc400c9f19b065d"
                )), new ReservationRepository());
    }
}
