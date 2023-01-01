package com.mrgoropeza.portfoliobackend.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrgoropeza.portfoliobackend.model.Place;
import com.mrgoropeza.portfoliobackend.repository.PlaceRepository;
import com.mrgoropeza.portfoliobackend.service.interfaces.IPlaceService;
import com.mrgoropeza.portfoliobackend.utils.QueryClasses.QueryMeta;

@Service
public class PlaceService implements IPlaceService {

    @Autowired
    private PlaceRepository placeRepo;

    @Override
    public List<Place> getAll() {
        return placeRepo.findAll();
    }

    @Override
    public List<Place> getWithQuery(String query) throws IOException {
        QueryMeta queryMeta = QueryMeta.fromCodedString(query);
        if(queryMeta.getGlobalFilter().equalsIgnoreCase("")){
            return placeRepo.findAll(queryMeta.toPageable()).toList();
        }
        return placeRepo.findByNameContaining(queryMeta.getGlobalFilter(), queryMeta.toPageable()).toList();
    }

    @Override
    public Place getById(Long id) {
        return placeRepo.findById(id).orElse(null);
    }

    @Override
    public Place save(Place Place) {
        return placeRepo.save(Place);
    }

    @Override
    public Place delete(Long id) {
        Place deleted = placeRepo.findById(id).orElse(null);
        placeRepo.delete(deleted);
        return deleted;
    }

    @Override
    public long getTotalRecords() {
        return placeRepo.count();
    }

    @Override
    public long getQueryTotalRecords(String query) throws IOException {
        QueryMeta queryMeta = QueryMeta.fromCodedString(query);
        if(queryMeta.getGlobalFilter().equalsIgnoreCase("")){
            return getTotalRecords();
        }
        return placeRepo.findByNameContaining(queryMeta.getGlobalFilter()).size();
    }
    
}
