package com.mrgoropeza.portfoliobackend.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class MultipleRecordsDTO<Model> implements Serializable{
    private List<Model> data;
    private long totalRecords;
}
