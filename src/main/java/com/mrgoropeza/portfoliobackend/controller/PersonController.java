package com.mrgoropeza.portfoliobackend.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mrgoropeza.portfoliobackend.dto.MultipleRecordsDTO;
import com.mrgoropeza.portfoliobackend.model.Person;
import com.mrgoropeza.portfoliobackend.service.interfaces.IPersonService;
import com.mrgoropeza.portfoliobackend.service.interfaces.IStorageService;
import com.mrgoropeza.portfoliobackend.utils.JsonConverter;

@RestController
public class PersonController {
    
    @Autowired
    private IPersonService personService;

    @Autowired
    private IStorageService storageService;

    // getAll
    @GetMapping("/persons/")
    public MultipleRecordsDTO<Person> getAll(@RequestParam(required = false) String query) throws IOException {
        MultipleRecordsDTO<Person> response = new MultipleRecordsDTO<Person>();

        if(query != null){
            if(query.equalsIgnoreCase("")){
                query = null;
            }
        }
        
        if(query == null){
            response.setData(personService.getAll());
            response.setTotalRecords(personService.getTotalRecords());
        }else{
            response.setData(personService.getWithQuery(query));
            response.setTotalRecords(personService.getQueryTotalRecords(query));
        }
        System.out.println(response);
        return response;
    }

    // getById
    @GetMapping("/person/{id}")
    public Person getById(@PathVariable Long id){
        return personService.getById(id);
    }

    @GetMapping("/person/me/")
    public Person getMe(){
        Person me = personService.getMyInfo();
        if(me.getImageUrl() == null){
            me.setImageUrl(storageService.getImageUrl("no-image-profile.png"));
        }
        return me;
    }

    // add
    @PostMapping(value = "person/", consumes = { 
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE 
    })
    public Person add(
        @RequestPart(value = "person") String Person,
        @RequestPart(value = "imagen", required = false) MultipartFile imagen
    ) throws IOException {

        Person PersonJson = JsonConverter.fromJsonString(Person, Person.class);

        PersonJson = personService.save(PersonJson);

        String imageUrl = storageService.getImageUrl("no-image.jpg");

        if(imagen != null){
            storageService.save(imagen, PersonJson.getId().toString(), "persons");
            
            String path = "persons/" + PersonJson.getId().toString();
            imageUrl = storageService.getImageUrl(path);
        }else if(PersonJson.getImageUrl() != null){
            imageUrl = PersonJson.getImageUrl();
        }

        PersonJson.setImageUrl(imageUrl);

       return personService.save(PersonJson);
    }

    // @PutMapping(value = "/person/{id}", consumes = { 
    //     MediaType.APPLICATION_JSON_VALUE,
    //     MediaType.MULTIPART_FORM_DATA_VALUE 
    // })
    // public Person update(
    //     @PathVariable Long id, 
    //     @RequestPart(value = "person") String Person,
    //     @RequestPart(value = "imagen", required = false) MultipartFile imagen
    // ) throws IOException {
    //     Person anterior = personService.getById(id);
    //     if(anterior == null){
    //         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lugar no encontrado");
    //     }
    //     Person nueva = JsonConverter.fromJsonString(Person, Person.class);

    //     anterior.setName(nueva.getName());

    //     String imageUrl = anterior.getImageUrl();

    //     if(imagen != null){
    //         storageService.save(imagen, anterior.getId().toString(), "persons/");
            
    //         String path = "persons/" + anterior.getId().toString();
    //         imageUrl = storageService.getImageUrl(path);
    //     }
        
    //     anterior.setImageUrl(imageUrl);

    //     personService.save(anterior);

    //     return anterior;
    // }

    // delete
    @DeleteMapping(value = "/person/{id}")
    public Person deleteTecnologia(@PathVariable Long id) {
        return personService.delete(id);
    }
    

}
