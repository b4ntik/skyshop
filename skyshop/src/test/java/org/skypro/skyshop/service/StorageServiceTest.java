package org.skypro.skyshop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
public class StorageServiceTest {
    private StorageService storageService;

    @BeforeEach
    void setUp() throws Exception {
        storageService = new StorageService();
    }
    //получение всех продуктов
    @Test
    void getProductMap_shouldReturnAllProducts() {
        Collection<Product> products = storageService.getProductMap();
        assertThat(products)
                .isNotNull()
                // в createTestProducts есть два продукта
                .hasSize(2)
                .allMatch(product -> product.getProductName().startsWith("Product"));
    }
    //получить все статьи
    @Test
    void getArticleMap_shouldReturnAllArticles() {
        Collection<Article> articles = storageService.getArticleMap();
        assertThat(articles)
                .isNotNull()
                // в тестовом методе две статьи
                .hasSize(2)
                .allMatch(article -> article.getProductName().startsWith("Article"));
    }
    //получить все объекты Searchable - продукты и статьи
    @Test
    void getSearchable_shouldReturnAllProductsAndArticles() {
        Collection<Searchable> searchables = storageService.getSearchable();
        assertThat(searchables)
                .isNotNull()
                //всего 4 тестовых сущности - 2 продукта и две статьи
                .hasSize(4)
                .anyMatch(item -> item instanceof Product)
                .anyMatch(item -> item instanceof Article);
    }
    //получить продукт по существующему Айди
    @Test
    void getProductById_existingId_shouldReturnProduct() {
        Product anyProduct = storageService.getProductMap().iterator().next();
        UUID id = anyProduct.getId();

        Optional<Product> found = storageService.getProductById(id);

        assertThat(found)
                .isPresent()
                .contains(anyProduct);
    }
    //попытаться получить продукты по несуществующему Айди - должен вернуть пустой список
    @Test
    void getProductById_nonExistingId_shouldReturnEmpty() {
        UUID fakeId = UUID.randomUUID();

        Optional<Product> found = storageService.getProductById(fakeId);

        assertThat(found)
                .isEmpty();
    }

}
