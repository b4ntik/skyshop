package org.skypro.skyshop.model.product;

import org.skypro.skyshop.model.article.Article;

import java.util.UUID;

public class DiscountedProduct extends Product {

    private int basePrice;
    private int discount;
    private final UUID id;

    public DiscountedProduct(String productName, int basePrice, int discount, UUID id) throws IllegalArgumentException {
        super(productName);
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("Некорректна указана скидка");

        } else if (basePrice <= 0) {
            throw new IllegalArgumentException("Некорректно указана цена");

        } else {
            this.basePrice = basePrice;
            this.discount = discount;
            this.id = id;
        }
    }

    public void setProduct(String productName, int basePrice) {
        this.productName = productName;
        this.basePrice = basePrice;
    }

    @Override
    public int getProductPrice() {
        return basePrice - (basePrice * discount / 100);
    }

    @Override
    public String toString() {
        return getProductName() + " : " + getProductPrice() + "(" + discount + "%)";
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public boolean equals(Product product) {
        if (this == product) return true;
        if (product == null || getClass() != product.getClass()) return false;
        Product that = (Product) product;
        return productName.equals(that.productName);
    }

    @Override
    public boolean equals(Article article) {
        return false;
    }

  }

