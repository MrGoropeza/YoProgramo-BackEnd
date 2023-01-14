package com.mrgoropeza.portfoliobackend.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mrgoropeza.portfoliobackend.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    Page<Project> findByNameContaining(String name, Pageable pageable);

    List<Project> findByNameContaining(String name);
    
}
