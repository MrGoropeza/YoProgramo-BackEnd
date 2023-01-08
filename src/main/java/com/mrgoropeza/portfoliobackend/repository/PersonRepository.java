package com.mrgoropeza.portfoliobackend.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mrgoropeza.portfoliobackend.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

    public List<Person> findByitsMe(boolean flag);

    public Page<Person> findByitsMe(boolean flag, Pageable pageable);

    public Page<Person> findByitsMeAndNameContaining(boolean flag, String name, Pageable pageable);

    public List<Person> findByitsMeAndNameContaining(boolean flag, String name);

}
