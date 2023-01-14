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
import com.mrgoropeza.portfoliobackend.model.Project;
import com.mrgoropeza.portfoliobackend.service.interfaces.IProjectService;

@RestController
public class ProjectController {
    
    @Autowired
    private IProjectService service;

    @GetMapping("/projects/")
    public MultipleRecordsDTO<Project> getAll(@RequestParam(required = false) String query) throws IOException {
        MultipleRecordsDTO<Project> response = new MultipleRecordsDTO<Project>();

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

    @GetMapping("/project/{id}")
    public Project getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping("/project/")
    public Project add(@RequestBody() Project Model) throws IOException {
        return service.save(Model);
    }

    @DeleteMapping("/project/{id}")
    public Project delete(@PathVariable Long id) {
        return service.delete(id);
    }

}
