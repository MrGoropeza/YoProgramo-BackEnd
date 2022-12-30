package com.mrgoropeza.portfoliobackend.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.mrgoropeza.portfoliobackend.model.TechType;
import com.mrgoropeza.portfoliobackend.service.TechTypeService;

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
public class TechTypeController {

    @Autowired
    private TechTypeService tipoTecnologiaService;

    // getAll
    @GetMapping(value="/techtypes")
    public List<TechType> getTiposTecnologias() {
        return tipoTecnologiaService.getTiposTecnologias();
    }

    // add
    @PostMapping(value="/techtype")
    public void createTipoTecnologia(@RequestBody TechType tipoTecnologia) {
        tipoTecnologiaService.saveTipoTecnologia(tipoTecnologia);
    }

    // update
    @PutMapping(value="/techtype/{id}")
    public TechType editTipoTecnologia(@PathVariable Long id, @RequestBody TechType tipoTecnologia) {
        TechType tipo = tipoTecnologiaService.findTipoTecnologia(id);
        if(tipo == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo de Tecnologia no encontrada");
        }
        tipo.setName(tipoTecnologia.getName());

        tipoTecnologiaService.saveTipoTecnologia(tipo);
        
        return tipo;
    }
    
    // delete
    @DeleteMapping(value = "techtype/{id}")
    public TechType deleteTipoTecnologia(@PathVariable Long id) {
        return tipoTecnologiaService.deleteTipoTecnologia(id);
    }
    
    
}
