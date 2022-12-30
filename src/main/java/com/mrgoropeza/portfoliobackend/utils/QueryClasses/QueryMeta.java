package com.mrgoropeza.portfoliobackend.utils.QueryClasses;

import java.util.Map;

import lombok.Data;

@Data
public class QueryMeta {
    int first;
    int last;
    int rows;
    String sortField;
    SortOrder sortOrder;
    String globalFilter;
    SortMeta[] multiSortMeta;
    Map<String, FilterMeta> filters;
}