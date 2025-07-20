package org.skypro.skyshop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SearchServiceTest {

    private StorageService storageService;
    private SearchService searchService;

    @BeforeEach
    void setUp() {
        storageService = mock(StorageService.class);
        searchService = new SearchService(storageService);
    }
    //тест поиска строки, состоящей из налл, просто пустой и состоящей из пробелов
    @Test
    void search_nullOrEmptyString_returnsEmptyList() {
        assertTrue(searchService.search(null).isEmpty());
        assertTrue(searchService.search("").isEmpty());
        assertTrue(searchService.search("   ").isEmpty());
    }
    //тест для поиска заведомо не имеющихся результатов
    @Test
    void search_noMatchingItems_returnsEmptyList() {
        Searchable item = mock(Searchable.class);
        when(item.getProductName()).thenReturn("Apple");
        when(storageService.getSearchable()).thenReturn(Set.of(item));

        List<SearchResult> results = (List<SearchResult>) searchService.search("banana");
        assertTrue(results.isEmpty());
    }
    //тест поиск заведомо имеющихся результатов
    @Test
    void search_matchingItems_returnsSearchResults() {
        Searchable item1 = mock(Searchable.class);
        Searchable item2 = mock(Searchable.class);
        when(item1.getProductName()).thenReturn("Apple iPhone");
        when(item2.getProductName()).thenReturn("Samsung Galaxy");
        when(storageService.getSearchable()).thenReturn(Set.of(item1, item2));

        List<SearchResult> results = (List<SearchResult>) searchService.search("apple");

        assertEquals(1, results.size());
        assertEquals("Apple iPhone", results.get(0).getName());
    }
    //тест для поиска несовпадающих регистров
    @Test
    void search_caseInsensitiveSearch() {
        Searchable item = mock(Searchable.class);
        when(item.getProductName()).thenReturn("Apple iPhone");
        when(storageService.getSearchable()).thenReturn(Set.of(item));

        List<SearchResult> results = (List<SearchResult>) searchService.search("APPLE");
        assertEquals(1, results.size());
    }
}