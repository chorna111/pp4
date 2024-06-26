package pl.chorna.ecommerce.catalog.sales.cart;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Spliterator;

public class CartStorage {
    Map<String,Cart> carts;
    public CartStorage(){
        this.carts=new HashMap<>();

    }



    public Optional<Cart> findByCustomer(String customerId) {

        return Optional.ofNullable(carts.get(customerId));
    }

    public void save(String customerId, Cart cart) {
        carts.put(customerId,cart);
    }

}
