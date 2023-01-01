package com.mrgoropeza.portfoliobackend.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mrgoropeza.portfoliobackend.model.Place;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    Page<Place> findByName(String name, Pageable pageable);

    List<Place> findByName(String name);
    
}
