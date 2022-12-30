package com.mrgoropeza.portfoliobackend.service.interfaces;

import java.util.List;

import com.mrgoropeza.portfoliobackend.model.Tech;

public interface ITechService {

    public List<Tech> getTecnologias(String nombreTipo);

    public void saveTecnologia(Tech tecnologia);

    public void deleteTecnologia(Long id);

    public Tech findTecnologia(Long id);
    
}
