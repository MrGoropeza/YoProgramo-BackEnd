package com.mrgoropeza.portfoliobackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mrgoropeza.portfoliobackend.model.TipoTecnologia;

public interface TipoTecnologiaRepository extends JpaRepository<TipoTecnologia, Long> {
    
}
