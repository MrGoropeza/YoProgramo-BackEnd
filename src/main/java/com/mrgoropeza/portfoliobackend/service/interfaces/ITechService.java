package com.mrgoropeza.portfoliobackend.service.interfaces;

import java.io.IOException;
import java.util.List;

import com.mrgoropeza.portfoliobackend.model.Tech;

public interface ITechService {

    public List<Tech> getAll(String techTypeName);

    public List<Tech> getWithQuery(String techTypeName, String query) throws IOException;

    public Tech save(Tech tech);

    public Tech delete(Long id);

    public Tech getById(Long id);

    public long getTotalRecords();
    
}
