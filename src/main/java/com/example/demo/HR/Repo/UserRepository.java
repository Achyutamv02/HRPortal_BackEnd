package com.example.demo.HR.Repo;

import com.example.demo.HR.Model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UserModel findByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        return jdbcTemplate.query(sql, new Object[]{email, password}, rs -> {
            if(rs.next()) {
                UserModel user = new UserModel();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
                // Don't return password for security
                return user;
            }
            return null;
        });
    }

    public int createUser(UserModel user) {
        if (user.getRole() == null || user.getRole().isEmpty()) {
            throw new IllegalArgumentException("Role must not be empty.");
        }
        String sql = "INSERT INTO users (name, email, password, role) VALUES (?, ?, ?, ?)";
        // This is correct—no change needed here if user.getRole() is sent by frontend!
        return jdbcTemplate.update(
                sql,
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getRole()   // Accepts whatever is sent by frontend: "ADMIN" or "EMPLOYEE"
        );
    }



}
