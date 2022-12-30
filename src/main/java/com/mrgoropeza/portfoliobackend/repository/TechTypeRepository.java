package com.mrgoropeza.portfoliobackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mrgoropeza.portfoliobackend.model.TechType;

public interface TechTypeRepository extends JpaRepository<TechType, Long> {
    
}
