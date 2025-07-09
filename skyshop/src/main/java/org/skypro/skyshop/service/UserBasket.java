package org.skypro.skyshop.service;

import java.util.List;

public class UserBasket {
    private final List<BasketItem> items;
    private int total = 0;
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

    public int totalBasketPrice(List<BasketItem> items){
         total = items.stream()
                .mapToInt(item -> item.getProduct().getProductPrice() * item.getCount())
                .sum();
         return total;
    }
    public List<BasketItem> getBasketItem(){
        return items;
    }
}

