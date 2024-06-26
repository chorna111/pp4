package pl.chorna.ecommerce.catalog.sales.offering;

import java.math.BigDecimal;

public class Offer {
    private final BigDecimal total;
    private  final int itemsCount;

    public Offer(BigDecimal total, int itemsCount){
        this.total=total;
        this.itemsCount=itemsCount;

    }
    public int getItemsCount() {
        return itemsCount;
    }

    public BigDecimal getTotal() {
        return total;
    }
}