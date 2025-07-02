package model.product;

//import org.skypro.skyshop.text.Article;

import model.article.Article;

public class FixPriceProduct extends Product {
    private static final int FIX_PRICE = 150;

    public FixPriceProduct(String productName) throws IllegalArgumentException {
        super(productName);

        if (productName.isBlank()) {
            throw new IllegalArgumentException("Не указано наименование продукта");
        } else {
            this.productName = productName;
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
