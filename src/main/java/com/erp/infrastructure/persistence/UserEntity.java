package com.erp.infrastructure.persistence;

import com.erp.domain.model.User;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    public UserEntity() {}

    public Long getId()                { return id; }
    public String getEmail()           { return email; }
    public String getPassword()        { return password; }
    public String getRole()            { return role; }

    public void setId(Long id)         { this.id = id; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String pw) { this.password = pw; }
    public void setRole(String role)   { this.role = role; }

    public User toDomain() {
        return new User(id, email, password, role);
    }
}
