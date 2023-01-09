package com.mrgoropeza.portfoliobackend.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrgoropeza.portfoliobackend.model.Experience;
import com.mrgoropeza.portfoliobackend.repository.ExperienceRepository;
import com.mrgoropeza.portfoliobackend.service.interfaces.IExperienceService;
import com.mrgoropeza.portfoliobackend.utils.QueryClasses.QueryMeta;

@Service
public class ExperienceService implements IExperienceService {

    @Autowired
    private ExperienceRepository repo;

    @Override
    public List<Experience> getAll() {
        return repo.findAll();
    }

    @Override
    public List<Experience> getWithQuery(String query) throws IOException {
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
    public Experience getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Experience save(Experience value) {
        Experience prevWork = repo.findByActualWork(true).orElse(null);
        if(value.isActualWork() && prevWork != null){
            prevWork.setActualWork(false);
            prevWork.setFinishDate(new Date());
            repo.save(prevWork);
        }
        return repo.save(value);
    }

    @Override
    public Experience delete(Long id) {
        Experience deleted = repo.findById(id).orElse(null);
        repo.deleteById(id);
        return deleted;
    }
    
}
