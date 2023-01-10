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
import com.mrgoropeza.portfoliobackend.model.Education;
import com.mrgoropeza.portfoliobackend.service.interfaces.IEducationService;

@RestController
public class EducationController {

    @Autowired
    private IEducationService service;

    @GetMapping("/educations/")
    public MultipleRecordsDTO<Education> getAll(@RequestParam(required = false) String query) throws IOException {
        MultipleRecordsDTO<Education> response = new MultipleRecordsDTO<Education>();

        if (query != null) {
            if (query.equalsIgnoreCase("")) {
                query = null;
            }
        }

        if (query == null) {
            response.setData(service.getAll());
            response.setTotalRecords(service.getTotalRecords());
        } else {
            response.setData(service.getWithQuery(query));
            response.setTotalRecords(service.getQueryTotalRecords(query));
        }
        System.out.println(response);
        return response;
    }

    @GetMapping("/education/{id}")
    public Education getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping("/education/")
    public Education add(@RequestBody() Education Model) throws IOException {
        return service.save(Model);
    }

    @DeleteMapping("/education/{id}")
    public Education delete(@PathVariable Long id) {
        return service.delete(id);
    }

}
