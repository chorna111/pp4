package pl.chorna.ecommerce.catalog.sales.offering;

import pl.chorna.ecommerce.catalog.sales.cart.CartItem;

import java.math.BigDecimal;
import java.util.List;

public class OfferCalculator {
    public static Offer calculate(List<CartItem> items) {
        //co x produkt gratis
        //>100pln -10zł
        return new Offer(
                BigDecimal.valueOf(10).multiply(new BigDecimal(items.size())),
                items.size());

    }
}
