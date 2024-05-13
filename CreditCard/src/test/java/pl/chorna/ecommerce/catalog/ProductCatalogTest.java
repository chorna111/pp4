package pl.chorna.ecommerce.catalog;

import org.junit.jupiter.api.Test;
import pl.chorna.ecommerce.catalog.ArrayListProductStorage;
import pl.chorna.ecommerce.catalog.Product;
import pl.chorna.ecommerce.catalog.ProductCatalog;

import java.math.BigDecimal;
import java.util.List;
import static org.assertj.core.api.Assertions.*;


public class ProductCatalogTest {
    @Test
    void itListAvailableProducts(){
        ProductCatalog catalog = getProductCatalog();
        List<Product> products=catalog.allProducts();
        assert products.isEmpty();



    }
    @Test
    void itAllowsToAddProduct(){
        ProductCatalog catalog = getProductCatalog();
        catalog.addProduct("lego","jakiś tam  opis");
        List<Product> products=catalog.allProducts();
        assertThat(products)
                .hasSize(1);



    }

    private static ProductCatalog getProductCatalog() {
        ProductCatalog catalog=new ProductCatalog(new ArrayListProductStorage());
        return catalog;
    }

    @Test
    void itLoadsSingleProductById(){
        ProductCatalog catalog = getProductCatalog();
        String id=catalog.addProduct("Lego","jakiś tam opis");
        Product loaded=catalog.getProductBy(id);
        assertThat(id).isEqualTo(loaded.getId());
    }
    @Test
    void itAllowsToChangePrice(){
        ProductCatalog catalog = getProductCatalog();
        String id=catalog.addProduct("Lego","jakiś tam opis");
        catalog.changePrice(id, BigDecimal.valueOf(10.10));
        Product loaded=catalog.getProductBy(id);
        assertThat(BigDecimal.valueOf(10.10)).isEqualTo(loaded.getPrice());


    }
}
