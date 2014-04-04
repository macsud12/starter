package com.macs.starter.model;

/**
 * User object
 */
public class User {
    private String username;
    private String password;

    public User(String username, String password) {
        if (username == null || password == null) {
            throw new IllegalArgumentException("invalid credentials");
        }

        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}