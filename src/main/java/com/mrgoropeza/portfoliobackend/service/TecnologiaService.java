package com.mrgoropeza.portfoliobackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrgoropeza.portfoliobackend.model.Tecnologia;
import com.mrgoropeza.portfoliobackend.repository.TecnologiaRepository;
import com.mrgoropeza.portfoliobackend.service.interfaces.ITecnologiaService;

@Service
public class TecnologiaService implements ITecnologiaService{
    
    @Autowired
    private TecnologiaRepository techRepository;

    @Override
    public List<Tecnologia> getTecnologias(String nombreTipo) {
        List<Tecnologia> tecnologias = techRepository.findByTipo_name(nombreTipo);
        return tecnologias;
    }

    @Override
    public void saveTecnologia(Tecnologia tecnologia) {
        techRepository.save(tecnologia);
    }

    @Override
    public void deleteTecnologia(Long id) {
        techRepository.deleteById(id);
    }

    @Override
    public Tecnologia findTecnologia(Long id) {
        return techRepository.findById(id).orElse(null);
    }
}
