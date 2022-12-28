package com.mrgoropeza.portfoliobackend.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.mrgoropeza.portfoliobackend.model.TipoTecnologia;
import com.mrgoropeza.portfoliobackend.service.TipoTecnologiaService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class TipoTecnologiaController {

    @Autowired
    private TipoTecnologiaService tipoTecnologiaService;

    @GetMapping(value="/tipoTecnologia/list")
    public List<TipoTecnologia> getTiposTecnologias() {
        return tipoTecnologiaService.getTiposTecnologias();
    }

    @PostMapping(value="/tipoTecnologia/create")
    public void createTipoTecnologia(@RequestBody TipoTecnologia tipoTecnologia) {
        tipoTecnologiaService.saveTipoTecnologia(tipoTecnologia);
    }

    @PutMapping(value="/tipoTecnologia/update/{id}")
    public TipoTecnologia editTipoTecnologia(@PathVariable Long id, @RequestBody TipoTecnologia tipoTecnologia) {
        TipoTecnologia tipo = tipoTecnologiaService.findTipoTecnologia(id);
        if(tipo == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo de Tecnologia no encontrada");
        }
        tipo.setName(tipoTecnologia.getName());

        tipoTecnologiaService.saveTipoTecnologia(tipo);
        
        return tipo;
    }
    
    @DeleteMapping(value = "/tipoTecnologia/delete/{id}")
    public TipoTecnologia deleteTipoTecnologia(@PathVariable Long id, @RequestBody TipoTecnologia tipoTecnologia) {
        tipoTecnologiaService.deleteTipoTecnologia(id);
        return tipoTecnologia;
    }
    
    
}
