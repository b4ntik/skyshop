package org.skypro.skyshop.service;

import org.skypro.skyshop.model.product.Product;

import java.util.List;

public class UserBasket {
    private final List<BasketItem> items;
    private int count;

    public UserBasket(List<BasketItem> items, int count) {
        this.items = items;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int totalBasketPrice(List<BasketItem> items) {
        return items.stream()
                .mapToInt(item -> {
                    Product product = item.getProduct();
                    if (product == null) {
                        return 0;
                    }

                    return product.getProductPrice() * item.getCount();
                })
                .sum();
    }

    public List<BasketItem> getBasketItem() {
        return items;
    }
}

