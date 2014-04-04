package com.macs.starter.model;

/**
 * Client object
 */
public class Client {
    private String id;
    private String email;
    private String description;

    public Client(String id, String email, String description) {
        this.id = id;
        this.email = email;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
