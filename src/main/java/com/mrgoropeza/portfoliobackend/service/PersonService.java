package com.mrgoropeza.portfoliobackend.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrgoropeza.portfoliobackend.model.Person;
import com.mrgoropeza.portfoliobackend.repository.EducationRepository;
import com.mrgoropeza.portfoliobackend.repository.ExperienceRepository;
import com.mrgoropeza.portfoliobackend.repository.PersonRepository;
import com.mrgoropeza.portfoliobackend.service.interfaces.IPersonService;
import com.mrgoropeza.portfoliobackend.utils.QueryClasses.QueryMeta;

@Service
public class PersonService implements IPersonService {

    @Autowired
    private PersonRepository personRepo;

    @Autowired
    private ExperienceRepository expRepo;

    @Autowired
    private EducationRepository educationRepository;

    @Override
    public Person getMyInfo() {
        List<Person> request = personRepo.findByitsMe(true);
        Person me;
        if(request.size() == 0){
            me = new Person();
            me.setItsMe(true);
            personRepo.save(me);
            me.setActualWork(expRepo.findByActualWork(true).orElse(null));
            me.setActualCareer(educationRepository.findByActualEducation(true).orElse(null));
            return me;
        }
        me = request.get(0);
        return me;
    }

    @Override
    public Person saveMyInfo(Person me) {
        me.setItsMe(true);
        return personRepo.save(me);
    }

    @Override
    public List<Person> getAll() {
        return personRepo.findByitsMe(false);
    }

    @Override
    public List<Person> getWithQuery(String query) throws IOException {
        QueryMeta queryMeta = QueryMeta.fromCodedString(query);
        if(queryMeta.getGlobalFilter().equalsIgnoreCase("")){
            return personRepo.findAll(queryMeta.toPageable()).toList();
        }
        return personRepo.findByitsMeAndNameContaining(false, queryMeta.getGlobalFilter(), queryMeta.toPageable()).toList();
    }

    @Override
    public Person getById(Long id) {
        return personRepo.findById(id).orElse(null);
    }

    @Override
    public Person save(Person person) {
        return personRepo.save(person);
    }

    @Override
    public Person delete(Long id) {
        Person deleted = personRepo.findById(id).orElse(null);
        personRepo.deleteById(id);
        return deleted;
    }

    @Override
    public long getTotalRecords() {
        return personRepo.findByitsMe(false).size();
    }

    @Override
    public long getQueryTotalRecords(String query) throws IOException {
        QueryMeta queryMeta = QueryMeta.fromCodedString(query);
        if(queryMeta.getGlobalFilter().equalsIgnoreCase("")){
            return getTotalRecords();
        }
        return personRepo.findByitsMeAndNameContaining(false, queryMeta.getGlobalFilter()).size();
    }
    
}
