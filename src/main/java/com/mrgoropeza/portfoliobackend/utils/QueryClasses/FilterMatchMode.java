package com.mrgoropeza.portfoliobackend.utils.QueryClasses;

public enum FilterMatchMode {
    STARTS_WITH,
    CONTAINS,
    NOT_CONTAINS,
    ENDS_WITH,
    EQUALS,
    NOT_EQUALS,
    LESS_THAN,
    LESS_THAN_OR_EQUAL_TO,
    GREATER_THAN,
    GREATER_THAN_OR_EQUAL_TO,
    DATE_IS,
    DATE_IS_NOT,
    DATE_BEFORE,
    DATE_AFTER,
}
