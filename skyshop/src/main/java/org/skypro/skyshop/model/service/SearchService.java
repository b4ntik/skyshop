package org.skypro.skyshop.model.service;

import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final StorageService storageService;


    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public Collection<SearchResult> search(String searchString) {
        if (searchString == null || searchString.isEmpty()) {
            return Collections.emptyList();
        }
        String lowerCaseString = searchString.toLowerCase().trim();
        Collection<Searchable> items = storageService.getSearchable();

        List<SearchResult> results = items.stream()
                .filter(item -> item.getProductName().toLowerCase().trim().contains(searchString))
                .map(SearchResult::new)
                .collect(Collectors.toList());
        return results;
    }
}
