package com.mrgoropeza.portfoliobackend.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private boolean actualWork;
    @OneToOne()
    @JoinColumn()
    private Place place;
    private String title;
    private String description;
    private Date startDate;
    private Date finishDate;
}
