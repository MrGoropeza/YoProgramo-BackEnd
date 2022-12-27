package com.mrgoropeza.portfoliobackend.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrgoropeza.portfoliobackend.model.Tecnologia;
import com.mrgoropeza.portfoliobackend.service.interfaces.IStorageService;
import com.mrgoropeza.portfoliobackend.service.interfaces.ITecnologiaService;

@RestController
public class TecnologiaController {

    @Autowired
    private ITecnologiaService tecnologiaService;

    @Autowired
    private IStorageService storageService;

    @GetMapping("/techs/list")
    public List<Tecnologia> getTecnologias(@RequestParam String tipo) {
        return tecnologiaService.getTecnologias(tipo);
    }

    @PostMapping(value = "/techs/create", consumes = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE })
    public void createTecnologia(@RequestPart(value = "tech") String tecnologia,
            @RequestPart(value = "imagen", required = false) MultipartFile imagen) throws IOException {

        Tecnologia techJson = new Tecnologia();

        try {
            ObjectMapper om = new ObjectMapper();
            techJson = om.readValue(tecnologia, Tecnologia.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        String nombreArchivo = storageService.save(imagen);
        String imageUrl = storageService.getImageUrl(nombreArchivo);

        techJson.setImageUrl(imageUrl);

        tecnologiaService.saveTecnologia(techJson);
    }

    @PutMapping(value = "/techs/update/{id}")
    public Tecnologia editTecnologia(@PathVariable Long id, @RequestBody Tecnologia tecnologia) {
        Tecnologia tech = tecnologiaService.findTecnologia(id);

        tech.setDescription(tecnologia.getDescription());
        tech.setName(tecnologia.getName());
        tech.setTipo(tecnologia.getTipo());
        tech.setImageUrl(tecnologia.getImageUrl());
        tech.setId(tecnologia.getId());

        tecnologiaService.saveTecnologia(tech);

        return tech;
    }

    @DeleteMapping(value = "/techs/delete/{id}")
    public Tecnologia deleteTecnologia(@PathVariable Long id, @RequestBody Tecnologia tecnologia) {
        tecnologiaService.deleteTecnologia(id);
        return tecnologia;
    }
}
