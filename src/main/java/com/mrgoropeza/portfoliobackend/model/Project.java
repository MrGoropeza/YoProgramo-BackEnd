package com.mrgoropeza.portfoliobackend.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String url;
    private String description;
    @ManyToMany()
    private List<Person> collab;
    @ManyToMany()
    private List<Tech> techs;
    @OneToOne()
    @JoinColumn()
    private Place place;
}
