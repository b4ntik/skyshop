package org.skypro.skyshop.model.controller;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.service.SearchService;
import org.skypro.skyshop.model.service.StorageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class ShopController {
    private final StorageService storageService;
    private final SearchService searchResult;

    public ShopController(StorageService storageService, SearchService searchResult) {
        this.storageService = storageService;
        this.searchResult = searchResult;
    }

    @GetMapping("/")
    public String home() {
        return "Welcome to Skyshop!";
    }

    @GetMapping("/products")
    public Collection<Product> getAllProducts() {
        return storageService.getProductMap();
    }

    @GetMapping("/articles")
    public Collection<Article> getAllArticles() {

        return storageService.getArticleMap();
    }

    @GetMapping("/search")
    public Collection<SearchResult> search(@RequestParam("pattern") String pattern) {
        //на случай пустого паттерна добавил трай-кетч
        try {
            if (pattern == null || pattern.isEmpty()) {
                throw new IllegalArgumentException("Паттерн не передан");
            }

            return searchResult.search(pattern);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}