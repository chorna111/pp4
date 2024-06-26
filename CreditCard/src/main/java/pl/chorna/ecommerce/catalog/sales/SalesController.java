package pl.chorna.ecommerce.catalog.sales;


import org.springframework.web.bind.annotation.*;
import pl.chorna.ecommerce.catalog.sales.offering.Offer;
import pl.chorna.ecommerce.catalog.sales.reservation.AcceptOfferRequest;
import pl.chorna.ecommerce.catalog.sales.reservation.ReservationDetails;

@RestController
public class SalesController {

    SalesFacade sales;
    public SalesController(SalesFacade sales){
        this.sales = sales;
    }

    @GetMapping("/api/current-offer")
    Offer getCurrentOffer(){
        var customerId = getCurrentCustomerId();
        return sales.getCurrentOffer(customerId);
    }

    @PostMapping("/api/add-to-cart/{productId}")
    void addToCart(@PathVariable(name="productId") String productId){
        var customerId=getCurrentCustomerId();
        sales.addProduct(customerId,productId);
    }
    @PostMapping("/api/accept-offer")
    ReservationDetails acceptOffer(@RequestBody AcceptOfferRequest acceptOfferRequest){
        var customerId = getCurrentCustomerId();
        ReservationDetails reservationDetails=sales.acceptOffer(customerId,acceptOfferRequest);
        return reservationDetails;
    }

    private String getCurrentCustomerId(){
        return "Anastejsza";
    }


}