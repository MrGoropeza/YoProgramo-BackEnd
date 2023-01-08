package com.mrgoropeza.portfoliobackend.service.interfaces;

import java.io.IOException;
import java.util.List;

import com.mrgoropeza.portfoliobackend.model.Person;

public interface IPersonService {

    public Person getMyInfo();

    public Person saveMyInfo(Person me);

    public List<Person> getAll();

    public List<Person> getWithQuery(String query) throws IOException;

    public Person getById(Long id);

    public Person save(Person person);

    public Person delete(Long id);

    public long getTotalRecords();

    public long getQueryTotalRecords(String query) throws IOException;
    
}
