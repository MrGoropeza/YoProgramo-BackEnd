package com.mrgoropeza.portfoliobackend.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mrgoropeza.portfoliobackend.model.TechType;
import com.mrgoropeza.portfoliobackend.repository.TechTypeRepository;
import com.mrgoropeza.portfoliobackend.service.interfaces.ITechTypeService;
import com.mrgoropeza.portfoliobackend.utils.QueryClasses.QueryMeta;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class TechTypeService implements ITechTypeService {

    @Autowired
    private TechTypeRepository techTypeRepo;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public long getTotalRecords() {
        return techTypeRepo.count();
    }

    @Override
    public List<TechType> getAll() {
        return techTypeRepo.findAll();
    }

    @Override
    public List<TechType> getWithQuery(String query) throws IOException {
        QueryMeta queryMeta = QueryMeta.fromCodedString(query);
        System.out.println(queryMeta);

        Direction direction = queryMeta.getSortOrder() == 1 ? Direction.ASC : Direction.DESC;

        Sort sorting = Sort.by(direction, queryMeta.getSortField());

        Pageable paging = PageRequest.of(queryMeta.getFirst() / queryMeta.getRows(), queryMeta.getRows(), sorting);

        if(queryMeta.getGlobalFilter().equalsIgnoreCase("")){
            System.out.println("Resultado sin globalFilter");
            return techTypeRepo.findAll(paging).toList();
        }
        System.out.println("Resultado con globalFilter");
        return techTypeRepo.findByNameContaining(queryMeta.getGlobalFilter(), paging).toList();
    }

    @Override
    public TechType save(TechType tipoTecnologia) {
        return techTypeRepo.save(tipoTecnologia);
    }

    @Override
    public TechType delete(Long id) {
        TechType deleted = techTypeRepo.findById(id).orElse(null);
        techTypeRepo.deleteById(id);
        return deleted;
    }

    @Override
    public TechType getById(Long id) {
        return techTypeRepo.findById(id).orElse(null);
    }

    @Override
    public long getQueryTotalRecords(String query) throws IOException {
        QueryMeta queryMeta = QueryMeta.fromCodedString(query);
        if(queryMeta.getGlobalFilter().equalsIgnoreCase("")){
            return getTotalRecords();
        }
        return techTypeRepo.findByNameContaining(queryMeta.getGlobalFilter()).size();
    }

}
