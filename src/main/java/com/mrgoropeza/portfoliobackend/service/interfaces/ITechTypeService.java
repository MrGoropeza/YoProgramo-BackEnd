package com.mrgoropeza.portfoliobackend.service.interfaces;

import java.io.IOException;
import java.util.List;

import com.mrgoropeza.portfoliobackend.model.TechType;

public interface ITechTypeService {
    
    public List<TechType> getAll();

    public List<TechType> getWithQuery(String query) throws IOException;

    public TechType save(TechType tipoTecnologia);

    public TechType delete(Long id);

    public TechType getById(Long id);

    public long getTotalRecords();

}
