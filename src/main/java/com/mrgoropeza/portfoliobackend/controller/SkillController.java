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
import com.mrgoropeza.portfoliobackend.model.Skill;
import com.mrgoropeza.portfoliobackend.service.interfaces.ISkillService;

@RestController
public class SkillController {

    @Autowired
    private ISkillService service;

    @GetMapping("/skills/")
    public MultipleRecordsDTO<Skill> getAll(@RequestParam(required = false) String query) throws IOException {
        MultipleRecordsDTO<Skill> response = new MultipleRecordsDTO<Skill>();

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

    @GetMapping("/skill/{id}")
    public Skill getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping("/skill/")
    public Skill add(@RequestBody() Skill Model) throws IOException {
        return service.save(Model);
    }

    @DeleteMapping("/skill/{id}")
    public Skill delete(@PathVariable Long id) {
        return service.delete(id);
    }
    
}
