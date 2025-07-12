package org.skypro.skyshop.controller;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.error.NoSuchProductException;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;
import org.skypro.skyshop.service.UserBasket;
import org.springframework.http.ResponseEntity;
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

    //найти продукт по заданному айди
    @GetMapping("/search")
    public Collection<SearchResult> search(@RequestParam("pattern") String pattern) {

        if (pattern == null || pattern.isEmpty()) {
            throw new NoSuchProductException();
        }

        return searchResult.search(pattern);
    }

    //добавить продукт с заданным id
    @GetMapping("/basket/{id}")
    public ResponseEntity<String> addProduct(@PathVariable("id") String id) {
        UUID idProduct;
        try {
            idProduct = UUID.fromString(id);
        } catch (IllegalArgumentException exc) {
            return ResponseEntity.badRequest().body(exc.getMessage());
        }
        basketService.addProductByIdToBasket(idProduct);
        return ResponseEntity.ok("Продукт добавлен");
    }

    //получить корзину
    @GetMapping("/basket")
    public void getUserBasket() { //Map<UUID, UserBasket>
        try {
            System.out.println(basketService.getUserBasket());
        } catch (NoSuchProductException exc) {
            exc.printStackTrace();
        }

    }
}