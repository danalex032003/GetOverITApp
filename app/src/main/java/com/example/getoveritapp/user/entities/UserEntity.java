package com.example.getoveritapp.user.entities;


import java.util.List;

public class UserEntity {
    private String email;
    private String password;
    private String username;
    private String name;
    private Integer type;

    public UserEntity(String email, String password, String username, String name, Integer type) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.name = name;
        this.type = type;
    }

    public UserEntity() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
