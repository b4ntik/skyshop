package org.skypro.skyshop.service;

import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BasketService {

    private final StorageService storageService;
    private List<BasketItem> items = new ArrayList<>();

    public BasketService(StorageService storageService) {
        this.storageService = storageService;
    }

    private final Map<UUID, Integer> basket = new HashMap<>();

    public void addProductByIdToBasket(UUID id) {

        int newCount = basket.getOrDefault(id, 0) + 1;
        if (storageService.getSearchable().stream().anyMatch(product -> product.getId().equals(id))) {
            basket.put(id, newCount);
        } else

            throw new IllegalArgumentException("id Не найден");

    }

    public Map<UUID, UserBasket> getUserBasket() {
        Map<UUID, UserBasket> result = new HashMap<>();

        for (Map.Entry<UUID, Integer> entry : basket.entrySet()) {
            UUID productId = entry.getKey();
            int count = entry.getValue();
            // Получение продукта по id
            Product product = storageService.getProductMap().stream()
                    .filter(p -> p.getId().equals(productId))
                    .findFirst()
                    .orElse(null);

            if (product != null) {
                if (result.containsKey(productId)) {
                    UserBasket existingBasket = result.get(productId);
                    existingBasket.setCount(existingBasket.getCount() + count);
                } else {
                    List<BasketItem> items = new ArrayList<>();
                    items.add(new BasketItem(product, count));
                    UserBasket newBasket = new UserBasket(items, count);
                    result.put(productId, newBasket);
                }
            }
        }

        return result;
    }
}
