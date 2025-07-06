package org.skypro.skyshop.model.search;

public class SearchResult {
    private final String id;
    private final String name;
    private final String contentType;

    public SearchResult(String id, String name, String contentType) {
        this.id = id;
        this.name = name;
        this.contentType = contentType;
    }

    // Конструктор из Searchable-объектов
    public SearchResult(Searchable searchable) {
        this.id = String.valueOf(searchable.getId());
        this.name = searchable.getProductName();
        this.contentType = searchable.getProductType();
    }


    public static SearchResult fromSearchable(Searchable searchable) {
        return new SearchResult(searchable);
    }
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContentType() {
        return contentType;
    }


}
