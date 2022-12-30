package com.mrgoropeza.portfoliobackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrgoropeza.portfoliobackend.model.Tech;
import com.mrgoropeza.portfoliobackend.repository.TechRepository;
import com.mrgoropeza.portfoliobackend.service.interfaces.ITechService;

@Service
public class TechService implements ITechService{
    
    @Autowired
    private TechRepository techRepository;

    @Override
    public List<Tech> getTecnologias(String nombreTipo) {
        List<Tech> tecnologias = techRepository.findByTipo_name(nombreTipo);
        return tecnologias;
    }

    @Override
    public void saveTecnologia(Tech tecnologia) {
        techRepository.save(tecnologia);
    }

    @Override
    public void deleteTecnologia(Long id) {
        techRepository.deleteById(id);
    }

    @Override
    public Tech findTecnologia(Long id) {
        return techRepository.findById(id).orElse(null);
    }
}
