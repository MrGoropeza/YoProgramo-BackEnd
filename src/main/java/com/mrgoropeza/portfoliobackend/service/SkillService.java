package com.mrgoropeza.portfoliobackend.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrgoropeza.portfoliobackend.model.Skill;
import com.mrgoropeza.portfoliobackend.repository.SkillRepository;
import com.mrgoropeza.portfoliobackend.service.interfaces.ISkillService;
import com.mrgoropeza.portfoliobackend.utils.QueryClasses.QueryMeta;

@Service
public class SkillService implements ISkillService {

    @Autowired
    private SkillRepository repo;

    @Override
    public List<Skill> getAll() {
        return repo.findAll();
    }

    @Override
    public List<Skill> getWithQuery(String query) throws IOException {
        QueryMeta queryMeta = QueryMeta.fromCodedString(query);
        if(queryMeta.getGlobalFilter().equalsIgnoreCase("")){
            return repo.findAll(queryMeta.toPageable()).toList();
        }
        return repo.findByNameContaining(queryMeta.getGlobalFilter(), queryMeta.toPageable()).toList();
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
        return repo.findByNameContaining(queryMeta.getGlobalFilter()).size();
    }

    @Override
    public Skill getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Skill save(Skill model) {
        return repo.save(model);
    }

    @Override
    public Skill delete(Long id) {
        Skill deleted = repo.findById(id).orElse(null);
        repo.deleteById(id);
        return deleted;
    }
    
}
