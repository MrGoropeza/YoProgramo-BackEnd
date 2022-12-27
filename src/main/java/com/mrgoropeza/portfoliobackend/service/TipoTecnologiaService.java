package com.mrgoropeza.portfoliobackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrgoropeza.portfoliobackend.model.TipoTecnologia;
import com.mrgoropeza.portfoliobackend.repository.TipoTecnologiaRepository;
import com.mrgoropeza.portfoliobackend.service.interfaces.ITipoTecnologiaService;

@Service
public class TipoTecnologiaService implements ITipoTecnologiaService{

    @Autowired
    private TipoTecnologiaRepository tipoTecnologiaRepository;

    @Override
    public List<TipoTecnologia> getTiposTecnologias() {
        return tipoTecnologiaRepository.findAll();
    }

    @Override
    public void saveTipoTecnologia(TipoTecnologia tipoTecnologia) {
        tipoTecnologiaRepository.save(tipoTecnologia);
    }

    @Override
    public void deleteTipoTecnologia(Long id) {
        tipoTecnologiaRepository.deleteById(id);
        
    }

    @Override
    public TipoTecnologia findTipoTecnologia(Long id) {
        return tipoTecnologiaRepository.findById(id).orElse(null);
    }
    
}
