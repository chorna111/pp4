package pl.chorna.ecommerce.catalog;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private String url;

    Product() {
    }

    public Product(UUID id, String name, String description,BigDecimal price, String url) {
        this.id = id.toString();
        this.name = name;
        this.description = description;
        this.price=price;
        this.url=url;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    public void changePrice(BigDecimal newPrice) {

        this.price = newPrice;
    }
}