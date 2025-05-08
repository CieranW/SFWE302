package com.assignment11.service;

import com.assignment11.model.Person;
import com.assignment11.repository.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import java.util.List;

@Service
@Validated
public class PersonServiceImpl implements PersonService {
  private final PersonRepository repo;
  public PersonServiceImpl(PersonRepository repo) {
    this.repo = repo;
  }

  @Override
  public Person save(@Valid Person p) {
    return repo.save(p);
  }

  @Override
  public List<Person> findAll() {
    return repo.findAll();
  }
}
