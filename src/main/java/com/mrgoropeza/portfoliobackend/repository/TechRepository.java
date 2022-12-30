package com.mrgoropeza.portfoliobackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mrgoropeza.portfoliobackend.model.Tech;

public interface TechRepository extends JpaRepository<Tech, Long> {
    
    List<Tech> findByTipo_id(Long idTipo);

    List<Tech> findByTipo_name(String nameTipo);

}
