package com.mrgoropeza.portfoliobackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mrgoropeza.portfoliobackend.model.Education;

public interface EducationRepository extends JpaRepository<Education, Long> {

    Optional<Education> findByActualEducation(boolean actualEducation);

    Page<Education> findByPlace_nameContaining(String placeName, Pageable pageable);
    
    List<Education> findByPlace_nameContaining(String placeName);
}
