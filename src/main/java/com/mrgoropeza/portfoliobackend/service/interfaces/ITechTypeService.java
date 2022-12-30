package com.mrgoropeza.portfoliobackend.service.interfaces;

import java.util.List;

import com.mrgoropeza.portfoliobackend.model.TechType;

public interface ITechTypeService {
    
    public List<TechType> getTiposTecnologias();

    public void saveTipoTecnologia(TechType tipoTecnologia);

    public TechType deleteTipoTecnologia(Long id);

    public TechType findTipoTecnologia(Long id);

}
