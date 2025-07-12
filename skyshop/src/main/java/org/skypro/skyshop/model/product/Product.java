package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable {
    private UUID id;
    protected String productName;

    public Product(String productName) throws IllegalArgumentException {
        if (productName.isBlank()) {
            throw new IllegalArgumentException("Не указано наименование продукта");
        }
        this.productName = productName;
        this.id = UUID.randomUUID();


    }

    public Product(String s, double v, UUID uuid) {
    }

    public boolean isSpecial() {
        return false;
    }

    public abstract int getProductPrice();

    public String getProductName() {
        return productName;
    }

    @JsonIgnore
    public String getProductType() {
        return "PRODUCT";
    }

    public String getStringRepresentation() {

        return getProductName() + " - " + getProductType();
    }

    public UUID getId() {
        return id;
    }

    //форматирование строки
    @Override
    public String toString() {
        return getProductName() + " : " + getProductPrice();
    }


    @Override
    public String searchTerm() {
        return productName;

    }

    //переопределение equals для продуктов, сравнение по имени и классу продукта
    @Override
    public boolean equals(Product product) {
        if (this == product) return true;
        if (product == null || getClass() != product.getClass()) return false;
        Product that = (Product) product;
        return productName.equals(that.productName);
    }

    //переопределение hashCode для продуктов с полями Имя продукта и класс продукта
    @Override
    public int hashCode() {

        return Objects.hash(productName, getClass());

    }

}
