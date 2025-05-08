package com.assignment11.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Person {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message="Name is required")
  private String name;

  @Email(message="Must be a valid email")
  @NotBlank(message="Email is required")
  private String email;

  @NotNull
  @Min(value=0, message="Age must be ≥ 0")
  @Max(value=150, message="Age must be ≤ 150")
  private Integer age;

  // Constructors, getters/setters…
  public Person() {}
  public Person(String name, String email, Integer age) {
    this.name = name;
    this.email = email;
    this.age = age;
  }
  // …getters & setters omitted for brevity
}
