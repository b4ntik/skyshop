package org.skypro.skyshop.model.error;

public class ShopError {
private final String code;
private final String message;

    public ShopError(String code, String message) {
        this.code = code;
        this.message = message;
    }
    public String getCode(){
        return "Упс, 404";
    }
    public String getMessage(){
        return "Товар не найден, проверьте введенный код";
    }
}
