package com.mrgoropeza.portfoliobackend.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrgoropeza.portfoliobackend.model.Tech;
import com.mrgoropeza.portfoliobackend.repository.TechRepository;
import com.mrgoropeza.portfoliobackend.service.interfaces.ITechService;
import com.mrgoropeza.portfoliobackend.utils.QueryClasses.QueryMeta;

@Service
public class TechService implements ITechService{
    
    @Autowired
    private TechRepository techRepository;

    @Override
    public List<Tech> getAll() {
        return techRepository.findAll();
    }

    @Override
    public List<Tech> getAllByType(String techTypeName) {
        return techRepository.findByTipo_name(techTypeName);
    }

        
    @Override
    public List<Tech> getWithQuery(String query) throws IOException {
        QueryMeta queryMeta = QueryMeta.fromCodedString(query);
        if(queryMeta.getGlobalFilter().equalsIgnoreCase("")){
            return techRepository.findAll(queryMeta.toPageable()).toList();
        }
        return techRepository.findByTipo_name(queryMeta.getGlobalFilter(), queryMeta.toPageable()).toList();
    }


    @Override
    public List<Tech> getWithQueryAndType(String techTypeName, String query) throws IOException {
        QueryMeta queryMeta = QueryMeta.fromCodedString(query);

        if(queryMeta.getGlobalFilter().equalsIgnoreCase("")){
            return techRepository.findAll(queryMeta.toPageable()).toList();
        }
        return techRepository.findByNameContainingAndTipo_name(techTypeName, queryMeta.getGlobalFilter(), queryMeta.toPageable()).toList();
    }

    @Override
    public Tech save(Tech tech) {
        return techRepository.save(tech);
    }

    @Override
    public Tech delete(Long id) {
        Tech deleted = techRepository.findById(id).orElse(null);
        techRepository.delete(deleted);
        return deleted;
    }

    @Override
    public Tech getById(Long id) {
        return techRepository.findById(id).orElse(null);
    }

    @Override
    public long getTotalRecords() {
        return techRepository.count();
    }

    @Override
    public long getTypeTotalRecords(String techTypeName) {
        return techRepository.findByTipo_name(techTypeName).size();
    }

    
}
