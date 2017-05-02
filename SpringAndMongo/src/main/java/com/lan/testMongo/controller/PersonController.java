package com.lan.testMongo.controller;

import com.lan.testMongo.Service.PersonService;
import com.lan.testMongo.common.Response;
import com.lan.testMongo.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by tianxianglan on 2017/5/2.
 */
@Controller
public class PersonController {

    @Autowired
    PersonService personService;

    @Autowired
    MongoTemplate mongoTemplate;

    @ResponseBody
    @RequestMapping(value = "person/findByName", method = RequestMethod.POST)
    public Response findByName(String name){
        Person findPerson = personService.findByName(name);
        return new Response(findPerson);
    }

    @RequestMapping(value = "person/insert", method = RequestMethod.POST)
    public void insert(Person person){
        mongoTemplate.insert(person);
    }
}
