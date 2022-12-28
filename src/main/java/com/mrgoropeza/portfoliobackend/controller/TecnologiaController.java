package com.mrgoropeza.portfoliobackend.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.server.ResponseStatusException;

import com.mrgoropeza.portfoliobackend.model.Tecnologia;
import com.mrgoropeza.portfoliobackend.service.interfaces.IStorageService;
import com.mrgoropeza.portfoliobackend.service.interfaces.ITecnologiaService;
import com.mrgoropeza.portfoliobackend.utils.JsonConverter;

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

    @PostMapping(value = "/techs/create", consumes = { 
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE 
    })
    public void createTecnologia(
        @RequestPart(value = "tech") String tecnologia,
        @RequestPart(value = "imagen", required = false) MultipartFile imagen
    ) throws IOException {

        Tecnologia techJson = JsonConverter.fromJsonString(tecnologia, Tecnologia.class);

        String imageUrl = storageService.getImageUrl("no-image.jpg");

        if(imagen != null){
            storageService.save(imagen, techJson.getId().toString(), "techs/" + techJson.getTipo().getId());
            
            String path = "techs/" + techJson.getTipo().getId() + "/" + techJson.getId();
            imageUrl = storageService.getImageUrl(path);
        }

        techJson.setImageUrl(imageUrl);

        tecnologiaService.saveTecnologia(techJson);
    }

    @PutMapping(value = "/techs/update/{id}", consumes = { 
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE 
    })
    public Tecnologia editTecnologia(
        @PathVariable Long id, 
        @RequestPart(value = "tech") String tecnologia,
        @RequestPart(value = "imagen", required = false) MultipartFile imagen
    ) throws IOException {
        Tecnologia anterior = tecnologiaService.findTecnologia(id);
        if(anterior == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tecnologia no encontrada");
        }
        Tecnologia nueva = JsonConverter.fromJsonString(tecnologia, Tecnologia.class);

        anterior.setDescription(nueva.getDescription());
        anterior.setName(nueva.getName());
        anterior.setTipo(nueva.getTipo());

        String imageUrl = anterior.getImageUrl();

        System.out.println(imagen);

        if(imagen != null){
            System.out.println("Actualizando Imagen");
            storageService.save(imagen, anterior.getId().toString(), "techs/" + anterior.getTipo().getId());
            
            String path = "techs/" + anterior.getTipo().getId() + "/" + anterior.getId();
            imageUrl = storageService.getImageUrl(path);
        }
        
        anterior.setImageUrl(imageUrl);

        tecnologiaService.saveTecnologia(anterior);

        return anterior;
    }

    @DeleteMapping(value = "/techs/delete/{id}")
    public Tecnologia deleteTecnologia(@PathVariable Long id, @RequestBody Tecnologia tecnologia) {
        tecnologiaService.deleteTecnologia(id);
        return tecnologia;
    }
}
