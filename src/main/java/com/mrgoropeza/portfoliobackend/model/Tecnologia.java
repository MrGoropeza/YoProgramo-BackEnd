package com.mrgoropeza.portfoliobackend.model;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mrgoropeza.portfoliobackend.utils.JsonConverter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Tecnologia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String imageUrl;
    private String description;
    @OneToOne()
    @JoinColumn()
    private TipoTecnologia tipo;

}
