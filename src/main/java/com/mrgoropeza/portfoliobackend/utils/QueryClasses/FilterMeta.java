package com.mrgoropeza.portfoliobackend.utils.QueryClasses;

import lombok.Data;

@Data
public class FilterMeta {
    Object value;
    FilterMatchMode matchMode;
    String operator;
}
