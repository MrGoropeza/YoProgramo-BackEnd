package com.mrgoropeza.portfoliobackend.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private boolean itsMe;
    private String name;
    private String nameUrl;
    private String title;
    private String imageUrl;
    @OneToOne()
    @JoinColumn()
    private Experience actualWork;
    @OneToOne()
    @JoinColumn()
    private Education actualCareer;
    @OneToMany()
    @JoinColumn()
    private List<Tech> techs;
}
