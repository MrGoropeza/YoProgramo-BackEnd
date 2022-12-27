package com.mrgoropeza.portfoliobackend.service.interfaces;

import java.util.List;

import com.mrgoropeza.portfoliobackend.model.Tecnologia;

public interface ITecnologiaService {

    public List<Tecnologia> getTecnologias(String nombreTipo);

    public void saveTecnologia(Tecnologia tecnologia);

    public void deleteTecnologia(Long id);

    public Tecnologia findTecnologia(Long id);
    
}
