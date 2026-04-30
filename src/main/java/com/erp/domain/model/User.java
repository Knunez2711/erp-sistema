package com.erp.domain.model;

public class User {

    private final Long id;
    private final String email;
    private final String password;
    private final String role;

    public User(Long id, String email, String password, String role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Long getId()       { return id; }
    public String getEmail()  { return email; }
    public String getPassword() { return password; }
    public String getRole()   { return role; }
}
