package com.assignment11.service;

import com.assignment11.model.Person;
import java.util.List;

public interface PersonService {
  Person save(Person p);
  List<Person> findAll();
}
