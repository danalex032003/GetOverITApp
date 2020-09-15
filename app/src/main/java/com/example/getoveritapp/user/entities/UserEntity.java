package com.example.getoveritapp.user.entities;


public class UserEntity {
    private String email;
    private String password;
    private String username;
    private String name;
    private String type;
    private boolean isVisible;
    private String description;
    private String phone;

    public UserEntity(String email, String password, String username, String name, String type, boolean isVisible, String description, String phone) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.name = name;
        this.type = type;
        this.isVisible = isVisible;
        this.description = description;
        this.phone = phone;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}
