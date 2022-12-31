package com.mrgoropeza.portfoliobackend.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.mrgoropeza.portfoliobackend.dto.MultipleRecordsDTO;
import com.mrgoropeza.portfoliobackend.model.Tech;
import com.mrgoropeza.portfoliobackend.service.interfaces.IStorageService;
import com.mrgoropeza.portfoliobackend.service.interfaces.ITechService;
import com.mrgoropeza.portfoliobackend.utils.JsonConverter;

@RestController
public class TechController {

    @Autowired
    private ITechService techService;

    @Autowired
    private IStorageService storageService;

    // getAll
    @GetMapping("/techs/")
    public MultipleRecordsDTO<Tech> getAllByType(@RequestParam(required = false) String techTypeName, @RequestParam(required = false) String query) throws IOException {
        MultipleRecordsDTO<Tech> response = new MultipleRecordsDTO<Tech>();

        if(query != null){
            if(query.equalsIgnoreCase("")){
                query = null;
            }
        }
        if(techTypeName != null){
            if(techTypeName.equalsIgnoreCase("")){
                techTypeName = null;
            }
        }
        System.out.println("Query: " + query);
        System.out.println("TechTypeName: " + techTypeName);

        if(techTypeName == null && query == null){
            response.setData(techService.getAll());
            response.setTotalRecords(techService.getTotalRecords());
        }else if(techTypeName != null && query == null){
            response.setData(techService.getAllByType(techTypeName));
            response.setTotalRecords(techService.getTypeTotalRecords(techTypeName));
        }else if(techTypeName == null && query != null){
            response.setData(techService.getWithQuery(query));
            response.setTotalRecords(techService.getTotalRecords());
        }else{
            response.setData(techService.getWithQueryAndType(techTypeName, query));
            response.setTotalRecords(techService.getTypeTotalRecords(techTypeName));
        }
        return response;
    }

    // getById
    @GetMapping("/tech/{id}")
    public Tech getById(@PathVariable Long id){
        return techService.getById(id);
    }

    // add
    @PostMapping(value = "/tech/", consumes = { 
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE 
    })
    public Tech createTecnologia(
        @RequestPart(value = "tech") String tecnologia,
        @RequestPart(value = "imagen", required = false) MultipartFile imagen
    ) throws IOException {

        Tech techJson = JsonConverter.fromJsonString(tecnologia, Tech.class);

        techJson = techService.save(techJson);

        String imageUrl = storageService.getImageUrl("no-image.jpg");

        if(imagen != null){
            storageService.save(imagen, techJson.getId().toString(), "techs/" + techJson.getTipo().getId());
            
            String path = "techs/" + techJson.getTipo().getId() + "/" + techJson.getId();
            imageUrl = storageService.getImageUrl(path);
        }else if(techJson.getImageUrl() != null){
            imageUrl = techJson.getImageUrl();
        }

        techJson.setImageUrl(imageUrl);

       return techService.save(techJson);
    }

    @PutMapping(value = "/tech/{id}", consumes = { 
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE 
    })
    public Tech editTecnologia(
        @PathVariable Long id, 
        @RequestPart(value = "tech") String tecnologia,
        @RequestPart(value = "imagen", required = false) MultipartFile imagen
    ) throws IOException {
        Tech anterior = techService.getById(id);
        if(anterior == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tecnologia no encontrada");
        }
        Tech nueva = JsonConverter.fromJsonString(tecnologia, Tech.class);

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

        techService.save(anterior);

        return anterior;
    }

    // delete
    @DeleteMapping(value = "/tech/{id}")
    public Tech deleteTecnologia(@PathVariable Long id) {
        return techService.delete(id);
    }
}
