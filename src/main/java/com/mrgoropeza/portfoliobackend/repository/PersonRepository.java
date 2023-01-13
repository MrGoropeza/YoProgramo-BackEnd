package com.mrgoropeza.portfoliobackend.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mrgoropeza.portfoliobackend.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

    public List<Person> findByitsMeFalse();

    public List<Person> findByItsMeTrue();

    public Page<Person> findByitsMeFalse(Pageable pageable);

    public Page<Person> findByitsMeFalseAndNameContaining(String name, Pageable pageable);

    public List<Person> findByitsMeFalseAndNameContaining(String name);

}
