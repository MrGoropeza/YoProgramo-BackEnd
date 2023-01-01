package com.mrgoropeza.portfoliobackend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mrgoropeza.portfoliobackend.model.TechType;

public interface TechTypeRepository extends JpaRepository<TechType, Long> {

    Page<TechType> findByName(String name, Pageable pageable);
}