package com.mrgoropeza.portfoliobackend.service.interfaces;

import com.mrgoropeza.portfoliobackend.model.Person;

public interface IPersonService extends ICrudService<Person> {

    public Person getMyInfo();

    public Person saveMyInfo(Person me);
    
}
