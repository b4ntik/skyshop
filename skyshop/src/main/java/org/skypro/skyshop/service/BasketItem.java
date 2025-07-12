package org.skypro.skyshop.service;

import org.skypro.skyshop.model.product.Product;

public class BasketItem {
    private final Product product;
    private int count;

    public BasketItem(Product product, int count) {
        this.product = product;
        this.count = 1;
    }

    public Product getProduct() {
        return product;
    }

    public int getCount() {
        return count;
    }

    public void incrementCount() {
        count++;
    }

}
