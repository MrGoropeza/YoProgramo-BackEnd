package com.mrgoropeza.portfoliobackend.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mrgoropeza.portfoliobackend.model.Experience;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {

    Page<Experience> findByPlace_nameContaining(String placeName, Pageable pageable);
    
    List<Experience> findByPlace_nameContaining(String placeName);
}
