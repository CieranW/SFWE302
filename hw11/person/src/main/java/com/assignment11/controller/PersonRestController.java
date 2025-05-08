package com.assignment11.controller;

import com.assignment11.model.Person;
import com.assignment11.service.PersonService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/people")
public class PersonRestController {
  private final PersonService service;
  public PersonRestController(PersonService service) {
    this.service = service;
  }

  // produces JSON or XML depending on Accept header
  @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE,
                           MediaType.APPLICATION_XML_VALUE })
  public List<Person> all() {
    return service.findAll();
  }
}
