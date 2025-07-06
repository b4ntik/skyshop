package org.skypro.skyshop.model.product;

import org.skypro.skyshop.model.article.Article;
import java.util.UUID;

public class SimpleProduct extends Product {
    private int productPrice;
    private final UUID id;


    public SimpleProduct(String productName, int productPrice, UUID id) throws IllegalArgumentException {
        super(productName);
        if (productPrice <= 0) {
            throw new IllegalArgumentException("Цена меньше нуля");
        } else {

            this.productPrice = productPrice;
            this.id = id;
        }
    }

    @Override
    public int getProductPrice() {
        return productPrice;
    }

    @Override
    public String toString() {
        return getProductName() + " : " + getProductPrice();
    }

    public void setProduct(String productName, int productPrice) throws IllegalArgumentException {
        if (productName.isBlank()) {

            throw new IllegalArgumentException("Вы передаете пустое значение имени");
        } else {

            this.productName = productName;
            this.productPrice = productPrice;
        }

    }


    @Override
    public boolean equals(Article article) {
        return false;
    }
}

