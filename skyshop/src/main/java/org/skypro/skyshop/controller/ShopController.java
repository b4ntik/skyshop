package org.skypro.skyshop.controller;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;
import org.skypro.skyshop.service.UserBasket;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

@RestController
public class ShopController {
    private final StorageService storageService;
    private final SearchService searchResult;
    private final BasketService basketService;

    public ShopController(StorageService storageService, SearchService searchResult, BasketService basketService) {
        this.storageService = storageService;
        this.searchResult = searchResult;
        this.basketService = basketService;
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
    @GetMapping("/basket/{id}")
    public String addProduct(@PathVariable("id") UUID id){
        basketService.addProductByIdToBasket(id);
        return "Продукт добавлен";
    }

    @GetMapping("/basket")
    public Map<UUID, UserBasket> getUserBasket(){
        return basketService.getUserBasket();
    }

}