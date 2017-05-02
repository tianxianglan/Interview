package com.lan.testMongo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.lan.testMongo.entity.Person;
import com.lan.testMongo.repository.PersonRepository;

/**
 * Created by tianxianglan on 2017/5/2.
 */
@Service
public class PersonService {

    public PersonRepository personRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    public Person findByName(String name){
        Person person = mongoTemplate.findOne(new Query(Criteria.where("name").is(name)), Person.class);
        return person;
    }
}
