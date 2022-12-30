package com.mrgoropeza.portfoliobackend.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.mrgoropeza.portfoliobackend.dto.MultipleRecordsDTO;
import com.mrgoropeza.portfoliobackend.model.TechType;
import com.mrgoropeza.portfoliobackend.service.TechTypeService;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class TechTypeController {

    @Autowired
    private TechTypeService techTypeService;

    // getAll
    @GetMapping(value="/techtypes/")
    public MultipleRecordsDTO<TechType> getAll(@RequestParam(required = false) String query) throws IOException {
        long totalRecords = techTypeService.getTotalRecords();
        MultipleRecordsDTO<TechType> response = new MultipleRecordsDTO<TechType>();
        if(query != null){
            response.setData(techTypeService.getWithQuery(query));
        }else{
            response.setData(techTypeService.getAll());
        }
        response.setTotalRecords(totalRecords);
        return response;
    }

    // getById
    @GetMapping("/techtype/{id}")
    public TechType getById(@PathVariable Long id){
        return techTypeService.getById(id);
    }

    // add
    @PostMapping(value="/techtype/")
    public TechType save(@RequestBody TechType tipoTecnologia) {
        return techTypeService.save(tipoTecnologia);
    }

    // update
    @PutMapping(value="/techtype/{id}")
    public TechType update(@PathVariable Long id, @RequestBody TechType tipoTecnologia) {
        TechType tipo = techTypeService.getById(id);
        if(tipo == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo de Tecnologia no encontrada");
        }
        tipo.setName(tipoTecnologia.getName());

        techTypeService.save(tipo);
        
        return tipo;
    }
    
    // delete
    @DeleteMapping(value = "techtype/{id}")
    public TechType delete(@PathVariable Long id) {
        return techTypeService.delete(id);
    }
    
    
}
