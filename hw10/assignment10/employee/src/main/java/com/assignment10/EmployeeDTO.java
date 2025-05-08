package com.assignment10;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDTO {
    private static final String URL = "jdbc:derby:memory:employeeDB;create=true";

    public EmployeeDTO() throws SQLException {
        // Create table if it doesn't exist
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(
              "CREATE TABLE Employee (" +
              " id     BIGINT      NOT NULL GENERATED ALWAYS AS IDENTITY " +
              "            (START WITH 1, INCREMENT BY 1)," +
              " name   VARCHAR(100) NOT NULL," +
              " email  VARCHAR(100) NOT NULL," +
              " age    INTEGER     NOT NULL," +
              " gender CHAR(1)     NOT NULL," +
              " salary BIGINT      NOT NULL," +
              " PRIMARY KEY (id)" +
              ")"
            );
        } catch (SQLException e) {
            // Table already exists: SQLState X0Y32
            if (!"X0Y32".equals(e.getSQLState())) throw e;
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    /** Insert or update based on whether emp.getId()==null */
    public Employee save(Employee emp) throws SQLException {
        if (emp.getId() == null) {
            String sql = "INSERT INTO Employee(name,email,age,gender,salary) " +
                         "VALUES(?,?,?,?,?)";
            try (Connection conn = getConnection();
                 PreparedStatement ps =
                   conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, emp.getName());
                ps.setString(2, emp.getEmail());
                ps.setInt   (3, emp.getAge());
                ps.setString(4, emp.getGender());
                ps.setLong  (5, emp.getSalary());
                ps.executeUpdate();
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) emp.setId(keys.getLong(1));
                }
            }
        } else {
            String sql = "UPDATE Employee " +
                         "SET name=?, email=?, age=?, gender=?, salary=? " +
                         "WHERE id=?";
            try (Connection conn = getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, emp.getName());
                ps.setString(2, emp.getEmail());
                ps.setInt   (3, emp.getAge());
                ps.setString(4, emp.getGender());
                ps.setLong  (5, emp.getSalary());
                ps.setLong  (6, emp.getId());
                ps.executeUpdate();
            }
        }
        return emp;
    }

    /** Find by primary key */
    public Employee findById(Long id) throws SQLException {
        String sql = "SELECT * FROM Employee WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        }
        return null;
    }

    /** Delete by primary key */
    public boolean delete(Long id) throws SQLException {
        String sql = "DELETE FROM Employee WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    /** Return all rows */
    public List<Employee> findAll() throws SQLException {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM Employee";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        }
        return list;
    }

    /** Total number of rows */
    public long count() throws SQLException {
        String sql = "SELECT COUNT(*) FROM Employee";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            rs.next();
            return rs.getLong(1);
        }
    }

    /** Find by arbitrary WHERE clause */
    public List<Employee> find(String whereClause) throws SQLException {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM Employee WHERE " + whereClause;
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) list.add(mapRow(rs));
        }
        return list;
    }

    /** Helper to map a ResultSet row to an Employee */
    private Employee mapRow(ResultSet rs) throws SQLException {
        return new Employee(
          rs.getLong  ("id"),
          rs.getString("name"),
          rs.getString("email"),
          rs.getInt   ("age"),
          rs.getString("gender"),
          rs.getLong  ("salary")
        );
    }
}