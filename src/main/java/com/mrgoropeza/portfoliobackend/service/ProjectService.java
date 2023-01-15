package com.mrgoropeza.portfoliobackend.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrgoropeza.portfoliobackend.model.Project;
import com.mrgoropeza.portfoliobackend.repository.ProjectRepository;
import com.mrgoropeza.portfoliobackend.service.interfaces.IProjectService;
import com.mrgoropeza.portfoliobackend.utils.QueryClasses.QueryMeta;

@Service
public class ProjectService implements IProjectService {

    @Autowired
    private ProjectRepository repo;

    @Override
    public List<Project> getAll() {
        return repo.findAll();
    }

    @Override
    public List<Project> getWithQuery(String query) throws IOException {
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
    public Project getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Project save(Project model) {
        return repo.save(model);
    }

    @Override
    public Project delete(Long id) {
        Project deleted = repo.findById(id).orElse(null);
        repo.deleteById(id);
        return deleted;
    }
    
}
