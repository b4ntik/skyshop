package org.skypro.skyshop.model.error;

public class NoSuchProductException extends RuntimeException{
    private final ShopError shopError;
    public  NoSuchProductException(){
      super ("404 продукт не найден");
        this.shopError = new ShopError("404 ", "продукт не найден");
    }
    public ShopError getShopError(){
        return shopError;
    }
}
