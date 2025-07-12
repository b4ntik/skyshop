package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.error.NoSuchProductException;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StorageService {
    private final Map<UUID, Product> productMap;
    private final Map<UUID, Article> articleMap;

    public StorageService() throws Exception {

        this.productMap = createTestProducts();
        this.articleMap = createTestArticles();
    }

    public Collection<Product> getProductMap() {
        return productMap.values();
    }

    public Collection<Article> getArticleMap() {
        return articleMap.values();
    }

    //объединение мапов articleMap и productMap в одну коллекцию Searchable объектов
    public Collection<Searchable> getSearchable() {

        Collection<Searchable> searchable = Stream.concat(articleMap.values().stream(), productMap.values().stream())
                .collect(Collectors.toList());
        return searchable;
    }

    public Optional<Product> getProductById(UUID id) {
        try {
            return getProductMap().stream()
                    .filter(product -> product.getId().equals(id))
                    .findFirst();
        } catch (NoSuchProductException exc) {
            exc.printStackTrace();
        }
        return Optional.empty();
    }

    //метод для создания тестовых продуктов
    private Map<UUID, Product> createTestProducts() {
        Map<UUID, Product> products = new HashMap<>();
        Product p1 = new SimpleProduct("Product 1", 100, UUID.randomUUID()) {

            @Override
            public int getProductPrice() {
                return 0;
            }
        };
        Product p2 = new SimpleProduct("Product 2", 200, UUID.randomUUID()) {

            @Override
            public int getProductPrice() {
                return 0;
            }
        };
        products.put(p1.getId(), p1);
        products.put(p2.getId(), p2);
        return products;
    }

    //метод для создания тестовых статей
    private Map<UUID, Article> createTestArticles() throws Exception {
        Map<UUID, Article> articles = new HashMap<>();
        Article a1 = new Article("Article", "Content of article 1", UUID.randomUUID());
        Article a2 = new Article("Article 2", "Content of article 2", UUID.randomUUID());
        articles.put(a1.getId(), a1);
        articles.put(a2.getId(), a2);
        return articles;
    }

}
