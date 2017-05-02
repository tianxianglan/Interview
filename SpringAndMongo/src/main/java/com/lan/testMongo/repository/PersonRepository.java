package com.lan.testMongo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import com.lan.testMongo.entity.Person;

/**
 * Created by tianxianglan on 2017/5/2.
 */
public interface PersonRepository extends PagingAndSortingRepository<Person, String>{
    Person findByName(String name);
}
