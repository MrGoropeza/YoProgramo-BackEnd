package com.mrgoropeza.portfoliobackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mrgoropeza.portfoliobackend.model.Tecnologia;

public interface TecnologiaRepository extends JpaRepository<Tecnologia, Long> {
    
    List<Tecnologia> findByTipo_idTipoTecnologia(Long idTipo);

    List<Tecnologia> findByTipo_nameTipoTecnologia(String nameTipo);

}
