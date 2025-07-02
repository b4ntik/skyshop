package model.article;

import model.product.Product;
import model.search.Searchable;

import java.lang.Exception;
import java.util.UUID;

public class Article implements Searchable {
    private String articleTitle;
    private String articleBody;

    public Article(String articleTitle, String articleBody) throws Exception {
        if (articleTitle.isBlank() || articleBody.isBlank()) {
            throw new Exception("Название статьи или сама статья пустые");
        } else {
            this.articleTitle = articleTitle;
            this.articleBody = articleBody;
        }
    }

    @Override
    public String getProductType() {
        return "ARTICLE";
    }

    public String toString() {
        return articleTitle + "\n" + articleBody;
    }

    @Override
    public UUID getId() {
        return null;
    }

    public String searchTerm() {
        String result = getArticleTitle() + "\n" + getArticleBody();
        return result;
    }

    public String getStringRepresentation() {

        return getArticleTitle() + " - " + getProductType();
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public String getArticleBody() {
        return articleBody;
    }

    public String getProductName() {
        return articleTitle;
    }

    @Override
    public boolean equals(Product product) {
        return false;
    }

    //переопределение equals для статей
    @Override
    public boolean equals(Article article) {
        if (this == article) return true;
        if (article == null || getClass() != article.getClass()) return false;
        Article that = (Article) article;
        return articleTitle.equals(that.articleTitle);
    }

    //переопределение hashCode для статей
    @Override
    public int hashCode() {

        return 0;
    }

}
