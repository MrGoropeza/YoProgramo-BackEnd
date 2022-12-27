package com.mrgoropeza.portfoliobackend.service.interfaces;

import java.util.List;

import com.mrgoropeza.portfoliobackend.model.TipoTecnologia;

public interface ITipoTecnologiaService {
    
    public List<TipoTecnologia> getTiposTecnologias();

    public void saveTipoTecnologia(TipoTecnologia tipoTecnologia);

    public void deleteTipoTecnologia(Long id);

    public TipoTecnologia findTipoTecnologia(Long id);

}
