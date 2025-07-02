package model.product;
import model.search.Searchable;

import java.util.UUID;
import java.util.Objects;

public abstract class Product implements Searchable {
    private UUID id;
    protected String productName;

    public Product(String productName) throws IllegalArgumentException {
        if (productName.isBlank()) {
            throw new IllegalArgumentException("Не указано наименование продукта");
        } else {
            this.productName = productName;
        }

    }
    public boolean isSpecial() {
        return false;
    }

    public abstract int getProductPrice();

    public String getProductName() {
        return productName;
    }

    public String getProductType() {
        return "PRODUCT";
    }

    public String getStringRepresentation() {

        return getProductName() + " - " + getProductType();
    }
     public UUID getId(){
         id = UUID.randomUUID();
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
