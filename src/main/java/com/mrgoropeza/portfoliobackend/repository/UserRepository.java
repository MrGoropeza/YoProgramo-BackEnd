package com.mrgoropeza.portfoliobackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mrgoropeza.portfoliobackend.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByName(String name);
    
}
