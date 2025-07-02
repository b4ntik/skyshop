package model.product;

public class BestResultNotFound extends Exception {

    private String textException;

    public BestResultNotFound(String textException) {
        super(textException);
        this.textException = textException;
    }

    @Override
    public String toString() {
        return "Exception - " + textException;
    }

}
