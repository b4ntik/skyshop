package org.skypro.skyshop.model.search;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.article.Article;
import java.util.UUID;

public interface Searchable {

 UUID getId();
 default String searchTerm() {
  return "";
 };

 default String getProductType() {
  return "";
 };

 default String getStringRepresentation() {
  return "";
 };

 default String getProductName(){  return ""; }

 //переопределение equals для продуктов
 boolean equals(Product product);

 //переопределение equals для статей
 boolean equals(Article article);

 void setId(UUID existingProductId);

 void setTitle(String testProduct);
}
