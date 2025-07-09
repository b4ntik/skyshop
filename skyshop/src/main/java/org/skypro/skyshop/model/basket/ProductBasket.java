package org.skypro.skyshop.model.basket;

import org.springframework.web.context.annotation.SessionScope;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@SessionScope
public class ProductBasket {

    private Map<UUID, Integer> productBasket = new HashMap<>();
    private List<String> removedProducts;

    public ProductBasket() {
        productBasket = new HashMap<>();
    }

    //метод добавления в корзину - добавляем в лист
    public void addUserBasket(UUID id) {
        productBasket.merge(id, 1, Integer::sum);

    }

    //получение содержимого корзины
    public Map<UUID, Integer> getBasketComposition() {
        return productBasket;
    }

    //очистка корзины
    public void cleanBasket() {
        productBasket.clear();
    }
}

