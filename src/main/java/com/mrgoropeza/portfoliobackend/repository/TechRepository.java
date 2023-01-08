package com.mrgoropeza.portfoliobackend.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mrgoropeza.portfoliobackend.model.Tech;

public interface TechRepository extends JpaRepository<Tech, Long> {
    
    List<Tech> findByTipo_id(Long idTipo);

    Page<Tech> findByNameContainingAndTipo_name(String name, String nameTipo, Pageable pageable);

    Page<Tech> findByTipo_name(String nameTipo, Pageable pageable);

    List<Tech> findByTipo_name(String nameTipo);

    Page<Tech> findByName(String name, Pageable pageable);

}
