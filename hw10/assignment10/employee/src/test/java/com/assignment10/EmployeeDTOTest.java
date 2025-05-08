package com.assignment10;

import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EmployeeDTOTest {
    private EmployeeDTO dao;

    @BeforeAll
    void init() throws SQLException {
        dao = new EmployeeDTO();
    }

    @BeforeEach
    void cleanTable() throws SQLException {
        for (Employee e : dao.findAll()) {
            dao.delete(e.getId());
        }
    }

    @Test
    void saveAndFindById() throws SQLException {
        Employee e = new Employee("Alice","alice@example.com",30,"F",75000L);
        dao.save(e);
        assertNotNull(e.getId());
        Employee fetched = dao.findById(e.getId());
        assertEquals("Alice", fetched.getName());
    }

    @Test
    void updateExisting() throws SQLException {
        Employee e = new Employee("Bob","bob@x.com",40,"M",80000L);
        dao.save(e);
        e.setSalary(82000L);
        dao.save(e);
        assertEquals(82000L, dao.findById(e.getId()).getSalary());
    }

    @Test
    void deleteEmployee() throws SQLException {
        Employee e = new Employee("Carol","carol@x.com",28,"F",68000L);
        dao.save(e);
        assertTrue(dao.delete(e.getId()));
        assertNull(dao.findById(e.getId()));
    }

    @Test
    void findAllAndCount() throws SQLException {
        dao.save(new Employee("X","x@x.com",20,"M",50000L));
        dao.save(new Employee("Y","y@y.com",22,"F",52000L));
        List<Employee> all = dao.findAll();
        assertEquals(2, all.size());
        assertEquals(2, dao.count());
    }

    @Test
    void findWithClause() throws SQLException {
        dao.save(new Employee("D","d@d.com",45,"M",95000L));
        dao.save(new Employee("E","e@e.com",35,"F",85000L));
        List<Employee> result = dao.find("salary > 90000");
        assertEquals(1, result.size());
        assertEquals("D", result.get(0).getName());
    }
}
