package com.example.getoveritapp.user.entities;


import java.util.List;

public class UserEntity {
    private String email;
    private String password;
    private String username;
    private String name;
    private Integer typeOfUser;
    private List<UserEntity> userEntities;

    public UserEntity(String email, String password, String username, String name, Integer typeOfUser, List<UserEntity> userEntities) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.name = name;
        this.typeOfUser = typeOfUser;
        this.userEntities = userEntities;
    }

    public UserEntity(String email, String password, String username, String name) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.name = name;
    }

    public UserEntity(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public UserEntity() {

    }

    public String getEmail(String email) {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword(String password) {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername(String username) {
        return this.username;
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

    public Integer getTypeOfUser() {
        return typeOfUser;
    }

    public void setTypeOfUser(Integer typeOfUser) {
        this.typeOfUser = typeOfUser;
    }
}
