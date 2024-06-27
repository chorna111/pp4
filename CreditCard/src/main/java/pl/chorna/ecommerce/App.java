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

import java.math.BigDecimal;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        System.out.println("here we go!!!!");
        SpringApplication.run(App.class, args);
    }

    @Bean
    ProductCatalog createMyProductCatalog() {


        var catalog = new ProductCatalog(new HasMapProductStorage());
        catalog.addProduct("Cup 'Cuba core'", "Nice one", BigDecimal.valueOf(10),"https://fastly.picsum.photos/id/30/1280/901.jpg?hmac=A_hpFyEavMBB7Dsmmp53kPXKmatwM05MUDatlWSgATE");
        catalog.addProduct("Macbook Pro", "Nice one",BigDecimal.valueOf(3000),"https://fastly.picsum.photos/id/48/5000/3333.jpg?hmac=y3_1VDNbhii0vM_FN6wxMlvK27vFefflbUSH06z98so");
        catalog.addProduct("Vinyl record player", "Nice one",BigDecimal.valueOf((1000)),"https://images.unsplash.com/5/unsplash-kitsune-4.jpg?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");

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
