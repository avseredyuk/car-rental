package com.avseredyuk.carrental.web.util;

/**
 * Created by lenfer on 1/25/17.
 */
public class PaginationInformation {
    private final Integer currentPage;
    private final Integer numberOfPages;
    private final Integer startIndex;
    private final Integer itemsPerPage;

    public PaginationInformation(Integer currentPage, Integer numberOfPages, Integer startIndex, Integer itemsPerPage) {
        this.currentPage = currentPage;
        this.numberOfPages = numberOfPages;
        this.startIndex = startIndex;
        this.itemsPerPage = itemsPerPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public Integer getItemsPerPage() {
        return itemsPerPage;
    }
}
