package org.skypro.skyshop.model.product;

import org.skypro.skyshop.model.article.Article;

import java.util.UUID;

public class FixPriceProduct extends Product {
    private static final int FIX_PRICE = 150;
    private final UUID id;

    public FixPriceProduct(String productName, UUID id) throws IllegalArgumentException {
        super(productName);

        if (productName.isBlank()) {
            throw new IllegalArgumentException("Не указано наименование продукта");
        } else {
            this.productName = productName;
            this.id = id;
        }
    }

    @Override
    public int getProductPrice() {
        return FIX_PRICE;
    }

    @Override
    public String toString() {
        return getProductName() + " : " + "Фиксированная цена " + getProductPrice();
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public boolean equals(Article article) {
        return false;
    }

}
