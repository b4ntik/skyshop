package org.skypro.skyshop.service;

import org.skypro.skyshop.model.error.NoSuchProductException;
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

        if (!storageService.getSearchable().stream().anyMatch(product -> product.getId().equals(id))) {
            throw new NoSuchProductException();
        }
        Optional<BasketItem> itemOpt = items.stream()
                .filter(item -> item.getProduct() != null && item.getProduct().getId().equals(id))
                .findFirst();

        if (itemOpt.isPresent()) {
            // Если есть, увеличиваем количество
            itemOpt.get().incrementCount();
            basket.put(id, basket.getOrDefault(id, 0) + 1);
        } else {
            // Если нет, добавляем новый товар
            Product product = storageService.getProductById(id);
            if(product == null){ throw new NoSuchProductException();}
            BasketItem item = new BasketItem(product, 1); // предполагается, что есть конструктор
            items.add(item);
            basket.put(id, 1);
        }
        boolean exists = storageService.getSearchable().stream().anyMatch(product -> product.getId().equals(id));
        System.out.println("Product with id " + id + " exists in searchable: " + exists);

        Product product = storageService.getProductById(id);
        System.out.println("Product fetched by id: " + product);
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
                    .orElseThrow(() -> new IllegalArgumentException("Такого Id нет"));

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
