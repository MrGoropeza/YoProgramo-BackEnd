package com.mrgoropeza.portfoliobackend.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mrgoropeza.portfoliobackend.model.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long> {

    Page<Skill> findByNameContaining(String name, Pageable pageable);
    
    List<Skill> findByNameContaining(String name);
    
}
