package com.mrgoropeza.portfoliobackend.service.interfaces;

import java.io.IOException;
import java.util.List;

public interface ICrudService<Model> {
    
    public List<Model> getAll();

    public List<Model> getWithQuery(String query) throws IOException;

    public long getTotalRecords();

    public long getQueryTotalRecords(String query) throws IOException;

    public Model getById(Long id);

    public Model save(Model model);

    public Model delete(Long id);

}
