package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.Searchable;

import java.lang.Exception;
import java.util.UUID;

public class Article implements Searchable {
    private final String articleTitle;
    private final String articleBody;
    private final UUID id;

    public Article(String articleTitle, String articleBody, UUID id) throws Exception {
        if (articleTitle.isBlank() || articleBody.isBlank()) {
            throw new Exception("Название статьи или сама статья пустые");
        }
        this.articleTitle = articleTitle;
        this.articleBody = articleBody;
        this.id = UUID.randomUUID();
    }

    @JsonIgnore
    @Override
    public String getProductType() {
        return "ARTICLE";
    }

    public String toString() {
        return articleTitle + "\n" + articleBody;
    }

    public String searchTerm() {
        String result = getProductName() + "\n" + getArticleBody();
        return result;
    }

    @JsonIgnore
    public String getStringRepresentation() {

        return getProductName() + " - " + getProductType();
    }

    public String getArticleBody() {
        return articleBody;
    }

    public String getProductName() {
        return articleTitle;
    }

    public UUID getId() {
        return id;
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

    @Override
    public void setId(UUID existingProductId) {

    }

    @Override
    public void setTitle(String testProduct) {

    }

    //переопределение hashCode для статей
    @Override
    public int hashCode() {

        return 0;
    }

}
