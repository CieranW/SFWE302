package com.assignment10;

public class Employee {
    private Long   id;
    private String name;
    private String email;
    private Integer age;
    private String gender;
    private Long   salary;

    public Employee() { }

    // Constructor for new Employees (no id)
    public Employee(String name, String email, Integer age, String gender, Long salary) {
        this.name   = name;
        this.email  = email;
        this.age    = age;
        this.gender = gender;
        this.salary = salary;
    }

    // Constructor for existing Employees (with id)
    public Employee(Long id, String name, String email, Integer age, String gender, Long salary) {
        this(name, email, age, gender, salary);
        this.id = id;
    }

    // Getters & setters
    public Long   getId()      { return id; }
    public void   setId(Long id) { this.id = id; }

    public String getName()    { return name; }
    public void   setName(String name) { this.name = name; }

    public String getEmail()   { return email; }
    public void   setEmail(String email) { this.email = email; }

    public Integer getAge()    { return age; }
    public void    setAge(Integer age) { this.age = age; }

    public String getGender()  { return gender; }
    public void   setGender(String gender) { this.gender = gender; }

    public Long   getSalary()  { return salary; }
    public void   setSalary(Long salary) { this.salary = salary; }

    @Override
    public String toString() {
        return "Employee{" +
               "id=" + id +
               ", name='"   + name   + '\'' +
               ", email='"  + email  + '\'' +
               ", age="     + age    +
               ", gender='" + gender + '\'' +
               ", salary="  + salary +
               '}';
    }
}
