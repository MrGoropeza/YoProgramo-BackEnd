package com.mrgoropeza.portfoliobackend.utils.QueryClasses;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;

import com.mrgoropeza.portfoliobackend.utils.JsonConverter;

import lombok.Data;

@Data
public class QueryMeta {
    int first;
    int last;
    int rows;
    String sortField;
    int sortOrder;
    String globalFilter;
    SortMeta[] multiSortMeta;
    Map<String, FilterMeta> filters;


    public static QueryMeta fromCodedString(String codedString) throws IOException{
        String s = new String(Base64.getDecoder().decode(codedString), java.nio.charset.StandardCharsets.UTF_8);
        return JsonConverter.fromJsonString(s, QueryMeta.class);
    }
}