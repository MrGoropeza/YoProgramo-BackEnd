package com.mrgoropeza.portfoliobackend.service.interfaces;

import java.io.IOException;
import java.util.List;

import com.mrgoropeza.portfoliobackend.model.Tech;

public interface ITechService {

    public List<Tech> getAll();

    public List<Tech> getAllByType(String techTypeName);

    public List<Tech> getWithQuery(String query) throws IOException;

    public List<Tech> getWithQueryAndType(String techTypeName, String query) throws IOException;

    public Tech save(Tech tech);

    public Tech delete(Long id);

    public Tech getById(Long id);

    public long getTotalRecords();

    public long getTypeTotalRecords(String techTypeName);
    
}
