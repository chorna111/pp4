package pl.chorna.ecommerce.sales.cart;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import pl.chorna.ecommerce.catalog.sales.cart.Cart;
import pl.chorna.ecommerce.catalog.sales.cart.CartItem;

import java.util.List;

public class CartTest {
    @Test
    void itIsEmptyWhenCreated(){
        Cart cart=Cart.empty();
        assertThat(cart.isEmpty())
                .isTrue();

    }
    @Test

    void itIsNotEmptyWhenProductWasAdded(){
        Cart cart=Cart.empty();
        String productId=thereIsProduct(("x"));
        cart.addProduct(productId);
        assertThat(cart.isEmpty())
                .isFalse();

    }
    @Test
    void itExposeUniqueProductCountS1(){
        Cart cart=Cart.empty();
        String productX=thereIsProduct("X");
        cart.addProduct(productX);
        assertThat(cart.getLinesCount())
                .isEqualTo(1);

    }
    @Test
    void itExposeUniqueProductCountS2(){
        Cart cart=Cart.empty();
        String productX=thereIsProduct("X");
        cart.addProduct(productX);
        cart.addProduct(productX);

        assertThat(cart.getLinesCount())
                .isEqualTo(1);


    }
    @Test
    void itExposeUniqueProductCountS3(){
        Cart cart=Cart.empty();
        String productX=thereIsProduct("X");
        String productY=thereIsProduct("Y");

        cart.addProduct(productX);
        cart.addProduct(productX);
        cart.addProduct(productY);


        assertThat(cart.getLinesCount())
                .isEqualTo(2);



    }
    @Test
    void itExposeCartLineQuantityS1(){
        Cart cart=Cart.empty();
        String productX=thereIsProduct("X");
        cart.addProduct(productX);

        List<CartItem> lines=cart.getItems();
        assertCartContainsXAmountOfProduct(lines,productX,1);


    }
    @Test
    void itExposeCartLineQuantityS2(){
        Cart cart=Cart.empty();
        String productX=thereIsProduct("X");
        cart.addProduct(productX);
        cart.addProduct(productX);


        List<CartItem> lines=cart.getItems();
        assertCartContainsXAmountOfProduct(lines,productX,1);


    }
    @Test

    void itExposeCartLineQuantityS3(){
        Cart cart=Cart.empty();
        String productX=thereIsProduct("X");
        String productY=thereIsProduct("Y");

        cart.addProduct(productX);
        cart.addProduct(productX);

        cart.addProduct(productX);

        cart.addProduct(productY);


        List<CartItem> lines=cart.getItems();
        assertCartContainsXAmountOfProduct(lines,productX,3);

        assertCartContainsXAmountOfProduct(lines,productY,1);


    }

    private void assertCartContainsXAmountOfProduct(List<CartItem> lines, String productId, int expectedQuantity) {
        assertThat(lines)
                .filteredOn(cartLine->cartLine.getProductId().equals(productId))
                .extracting(cartLine->cartLine.getQty())
                .first()
                .isEqualTo(expectedQuantity);


    }


    private String thereIsProduct(String id){
        return id;
    }

}
