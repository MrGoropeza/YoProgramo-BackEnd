package com.mrgoropeza.portfoliobackend.service.interfaces;

import java.io.IOException;
import java.util.List;

import com.mrgoropeza.portfoliobackend.model.Place;

public interface IPlaceService {
    
    public List<Place> getAll();

    public List<Place> getWithQuery(String query) throws IOException;

    public Place getById(Long id);

    public Place save(Place Place);

    public Place delete(Long id);

    public long getTotalRecords();

    public long getQueryTotalRecords(String name);

}
