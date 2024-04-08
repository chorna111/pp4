package pl.chorna.ecommerce.catalog;

import pl.chorna.ecommerce.catalog.Product;
import pl.chorna.ecommerce.catalog.ProductStorage;

import java.util.HashMap;
import java.util.List;

public class HashMapProductStorage implements ProductStorage {
    HashMap<String,Product> products;
    public HashMapProductStorage(){
        products=new HashMap<>();
    }

    @Override
    public List<Product> allProducts() {
        return products.values().stream().toList();
    }

    @Override
    public void add(Product product) {
        products.put(product.getId(),product);

    }

    @Override
    public Product getProductBy(String id) {
        return products.get(id);
    }
}
