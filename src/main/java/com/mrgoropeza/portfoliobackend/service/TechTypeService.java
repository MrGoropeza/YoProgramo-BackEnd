package com.mrgoropeza.portfoliobackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrgoropeza.portfoliobackend.model.TechType;
import com.mrgoropeza.portfoliobackend.repository.TechTypeRepository;
import com.mrgoropeza.portfoliobackend.service.interfaces.ITechTypeService;

@Service
public class TechTypeService implements ITechTypeService{

    @Autowired
    private TechTypeRepository tipoTecnologiaRepository;

    @Override
    public List<TechType> getTiposTecnologias() {
        return tipoTecnologiaRepository.findAll();
    }

    @Override
    public void saveTipoTecnologia(TechType tipoTecnologia) {
        tipoTecnologiaRepository.save(tipoTecnologia);
    }

    @Override
    public TechType deleteTipoTecnologia(Long id) {
        TechType deleted = tipoTecnologiaRepository.findById(id).orElse(null);
        tipoTecnologiaRepository.deleteById(id);
        return deleted;
    }

    @Override
    public TechType findTipoTecnologia(Long id) {
        return tipoTecnologiaRepository.findById(id).orElse(null);
    }
    
}
