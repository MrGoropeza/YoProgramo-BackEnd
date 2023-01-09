package com.mrgoropeza.portfoliobackend.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mrgoropeza.portfoliobackend.dto.MultipleRecordsDTO;
import com.mrgoropeza.portfoliobackend.model.Experience;
import com.mrgoropeza.portfoliobackend.service.interfaces.IExperienceService;

@RestController
public class ExperienceController {

    @Autowired
    private IExperienceService expService;

    @GetMapping("/experiences/")
    public MultipleRecordsDTO<Experience> getAll(@RequestParam(required = false) String query) throws IOException {
        MultipleRecordsDTO<Experience> response = new MultipleRecordsDTO<Experience>();

        if (query != null) {
            if (query.equalsIgnoreCase("")) {
                query = null;
            }
        }

        if (query == null) {
            response.setData(expService.getAll());
            response.setTotalRecords(expService.getTotalRecords());
        } else {
            response.setData(expService.getWithQuery(query));
            response.setTotalRecords(expService.getQueryTotalRecords(query));
        }
        System.out.println(response);
        return response;
    }

    @GetMapping("/experience/{id}")
    public Experience getById(@PathVariable Long id) {
        return expService.getById(id);
    }

    @PostMapping("/experience/")
    public Experience add(@RequestBody() Experience Model) throws IOException {
        return expService.save(Model);
    }

    @DeleteMapping("/experience/{id}")
    public Experience delete(@PathVariable Long id) {
        return expService.delete(id);
    }

}
