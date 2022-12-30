package com.mrgoropeza.portfoliobackend.utils.QueryClasses;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

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

    public Pageable toPageable(){
        Direction direction = this.sortOrder == 1 ? Direction.ASC : Direction.DESC;
        Sort sorting = Sort.by(direction, this.sortField);
        return PageRequest.of(this.first / this.rows, this.rows, sorting);
    }
}