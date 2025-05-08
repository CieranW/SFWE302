package com.assignment11.controller;

import com.assignment11.model.Person;
import com.assignment11.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class PersonController {
  private final PersonService service;
  public PersonController(PersonService service) {
    this.service = service;
  }

  @GetMapping({"/","/home"})
  public String home(Model model) {
    model.addAttribute("people", service.findAll());
    return "home";
  }

  @GetMapping("/add")
  public String showForm(Person person) {
    return "addPerson";
  }

  @PostMapping("/add")
  public String addPerson(@Valid Person person, BindingResult br) {
    if (br.hasErrors()) {
      return "addPerson";
    }
    service.save(person);
    return "redirect:/home";
  }
}
