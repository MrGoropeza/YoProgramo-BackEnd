package com.mrgoropeza.portfoliobackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mrgoropeza.portfoliobackend.model.Tecnologia;

public interface TecnologiaRepository extends JpaRepository<Tecnologia, Long> {
    
    List<Tecnologia> findByTipo_id(Long idTipo);

    List<Tecnologia> findByTipo_name(String nameTipo);

}
