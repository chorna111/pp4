package pl.chorna.ecommerce.catalog;

import org.junit.jupiter.api.Test;
import pl.chorna.ecommerce.catalog.Product;
import pl.chorna.ecommerce.catalog.ProductStorage;
import pl.chorna.ecommerce.catalog.HasMapProductStorage;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
public class HasMapProductStorageTest {

    private static final String TEST_PRODUCT_NAME = "test product";

    @Test
    void itStoreNewProduct(){
        ProductStorage storage=thereIsProductStorage();
        Product product=thereIsExampleProduct();

        storage.add(product);
        List<Product> products=storage.allProducts();
        assertThat(products)
                .hasSize(1)
                .extracting(Product::getName)
                .contains(TEST_PRODUCT_NAME);
    }

    private Product thereIsExampleProduct() {
        return new Product(UUID.randomUUID(),TEST_PRODUCT_NAME,"fajna rzecz");
    }

    private ProductStorage thereIsProductStorage(){
        return new HasMapProductStorage();
    }
    @Test
    void itAllowsAllProducts(){

    }
    @Test
    void itLoadsAllProducts(){

    }
}
