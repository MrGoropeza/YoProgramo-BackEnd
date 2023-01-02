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
import com.mrgoropeza.portfoliobackend.model.Place;
import com.mrgoropeza.portfoliobackend.service.interfaces.IPlaceService;
import com.mrgoropeza.portfoliobackend.service.interfaces.IStorageService;
import com.mrgoropeza.portfoliobackend.utils.JsonConverter;

@RestController
public class PlaceController {

    @Autowired
    private IPlaceService placeService;

    @Autowired
    private IStorageService storageService;

    // getAll
    @GetMapping("/places/")
    public MultipleRecordsDTO<Place> getAll(@RequestParam(required = false) String query) throws IOException {
        MultipleRecordsDTO<Place> response = new MultipleRecordsDTO<Place>();

        if(query != null){
            if(query.equalsIgnoreCase("")){
                query = null;
            }
        }
        
        if(query == null){
            response.setData(placeService.getAll());
            response.setTotalRecords(placeService.getTotalRecords());
        }else{
            response.setData(placeService.getWithQuery(query));
            response.setTotalRecords(placeService.getQueryTotalRecords(query));
        }
        System.out.println(response);
        return response;
    }

    // getById
    @GetMapping("/place/{id}")
    public Place getById(@PathVariable Long id){
        return placeService.getById(id);
    }

    // add
    @PostMapping(value = "/place/", consumes = { 
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE 
    })
    public Place add(
        @RequestPart(value = "place") String place,
        @RequestPart(value = "imagen", required = false) MultipartFile imagen
    ) throws IOException {

        Place PlaceJson = JsonConverter.fromJsonString(place, Place.class);

        PlaceJson = placeService.save(PlaceJson);

        String imageUrl = storageService.getImageUrl("no-image.jpg");

        if(imagen != null){
            storageService.save(imagen, PlaceJson.getId().toString(), "places");
            
            String path = "places/" + PlaceJson.getId().toString();
            imageUrl = storageService.getImageUrl(path);
        }else if(PlaceJson.getImageUrl() != null){
            imageUrl = PlaceJson.getImageUrl();
        }

        PlaceJson.setImageUrl(imageUrl);

       return placeService.save(PlaceJson);
    }

    @PutMapping(value = "/place/{id}", consumes = { 
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE 
    })
    public Place editTecnologia(
        @PathVariable Long id, 
        @RequestPart(value = "place") String place,
        @RequestPart(value = "imagen", required = false) MultipartFile imagen
    ) throws IOException {
        Place anterior = placeService.getById(id);
        if(anterior == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lugar no encontrado");
        }
        Place nueva = JsonConverter.fromJsonString(place, Place.class);

        anterior.setDescription(nueva.getDescription());
        anterior.setName(nueva.getName());

        String imageUrl = anterior.getImageUrl();

        if(imagen != null){
            storageService.save(imagen, anterior.getId().toString(), "places/");
            
            String path = "places/" + anterior.getId().toString();
            imageUrl = storageService.getImageUrl(path);
        }
        
        anterior.setImageUrl(imageUrl);

        placeService.save(anterior);

        return anterior;
    }

    // delete
    @DeleteMapping(value = "/place/{id}")
    public Place deleteTecnologia(@PathVariable Long id) {
        return placeService.delete(id);
    }
    
}
