package org.skypro.skyshop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skypro.skyshop.model.error.NoSuchProductException;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BasketServiceTest {
    private StorageService storageService;
    private BasketService basketService;

    private UUID existingProductId;
    private SimpleProduct existingProduct;

    @BeforeEach
    void setUp() {
        storageService = mock(StorageService.class);
        basketService = new BasketService(storageService);

        existingProductId = UUID.randomUUID();
        SimpleProduct existingProduct = new SimpleProduct("Test product", 100, existingProductId);


        // Мокируем storageService.getSearchable() возвращать список с одним продуктом
        when(storageService.getSearchable()).thenReturn(List.of(existingProduct));

        // Мокируем storageService.getProductMap() возвращать список с одним продуктом
        when(storageService.getProductMap()).thenReturn(List.of(existingProduct));

        //мок для результата поиска по айди
        when(storageService.getProductById(existingProductId)).thenReturn(existingProduct);
    }

    @Test
    void addProductByIdToBasket_whenProductExists_shouldAddProduct() {
        //искал ошибку
       // System.out.println(existingProduct.getId().equals(existingProductId));

        basketService.addProductByIdToBasket(existingProductId);

        Map<UUID, UserBasket> userBasket = basketService.getUserBasket();

        assertThat(userBasket).containsKey(existingProductId);
        UserBasket basket = userBasket.get(existingProductId);
        assertThat(basket.getCount()).isEqualTo(1);
        assertThat(basket.getBasketItem()).hasSize(1);
        BasketItem item = basket.getBasketItem().get(0);
        assertThat(item.getProduct()).isEqualTo(existingProduct);
        assertThat(item.getCount()).isEqualTo(1);
    }

    @Test
    void addProductByIdToBasket_whenProductDoesNotExist_shouldThrowException() {
        UUID nonExistingId = UUID.randomUUID();

        // Мокируем, что такого продукта нет в getSearchable()
        when(storageService.getSearchable()).thenReturn(Collections.emptyList());

        assertThatThrownBy(() -> basketService.addProductByIdToBasket(nonExistingId))
                .isInstanceOf(NoSuchProductException.class);
    }

    @Test
    void getUserBasket_whenMultipleSameProductsAdded_shouldAccumulateCount() {
        basketService.addProductByIdToBasket(existingProductId);
        basketService.addProductByIdToBasket(existingProductId);
        basketService.addProductByIdToBasket(existingProductId);
        //отлавливал ошибку
        //System.out.println(existingProduct);
        //System.out.println(existingProductId);

        Map<UUID, UserBasket> userBasket = basketService.getUserBasket();

        assertThat(userBasket).containsKey(existingProductId);
        UserBasket basket = userBasket.get(existingProductId);

        assertThat(basket.getCount()).isEqualTo(3);
        assertThat(basket.getBasketItem()).hasSize(1);

    }

    @Test
    void getUserBasket_whenBasketIsEmpty_shouldReturnEmptyMap() {
        Map<UUID, UserBasket> userBasket = basketService.getUserBasket();
        assertThat(userBasket).isEmpty();
    }
}

