package com.mrgoropeza.portfoliobackend.utils.QueryClasses;

public enum SortOrder {
    DESC(-1), ASC(1);

    public final int value;

    private SortOrder(int value) {
        this.value = value;
    }
}
