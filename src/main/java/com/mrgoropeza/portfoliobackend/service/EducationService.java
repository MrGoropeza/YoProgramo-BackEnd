package com.mrgoropeza.portfoliobackend.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrgoropeza.portfoliobackend.model.Education;
import com.mrgoropeza.portfoliobackend.repository.EducationRepository;
import com.mrgoropeza.portfoliobackend.service.interfaces.IEducationService;
import com.mrgoropeza.portfoliobackend.utils.QueryClasses.QueryMeta;

@Service
public class EducationService implements IEducationService {

    @Autowired
    private EducationRepository repo;

    @Override
    public List<Education> getAll() {
        return repo.findAll();
    }

    @Override
    public List<Education> getWithQuery(String query) throws IOException {
        QueryMeta queryMeta = QueryMeta.fromCodedString(query);
        if(queryMeta.getGlobalFilter().equalsIgnoreCase("")){
            return repo.findAll(queryMeta.toPageable()).toList();
        }
        return repo.findByPlace_nameContaining(queryMeta.getGlobalFilter(), queryMeta.toPageable()).toList();
    }

    @Override
    public long getTotalRecords() {
        return repo.count();
    }

    @Override
    public long getQueryTotalRecords(String query) throws IOException {
        QueryMeta queryMeta = QueryMeta.fromCodedString(query);
        if(queryMeta.getGlobalFilter().equalsIgnoreCase("")){
            return getTotalRecords();
        }
        return repo.findByPlace_nameContaining(queryMeta.getGlobalFilter()).size();
    }

    @Override
    public Education getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Education save(Education value) {
        Education prevWork = repo.findByActualEducation(true).orElse(null);
        if(value.isActualEducation() && prevWork != null){
            prevWork.setActualEducation(false);
            prevWork.setFinishDate(new Date());
            repo.save(prevWork);
        }
        return repo.save(value);
    }

    @Override
    public Education delete(Long id) {
        Education deleted = repo.findById(id).orElse(null);
        repo.deleteById(id);
        return deleted;
    }
    
}
