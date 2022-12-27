package com.mrgoropeza.portfoliobackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class TipoTecnologia {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idTipoTecnologia;
    private String nameTipoTecnologia;
}
