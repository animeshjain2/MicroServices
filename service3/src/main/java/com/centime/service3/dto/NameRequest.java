package com.centime.service3.dto;

import jakarta.validation.constraints.NotBlank;

public class NameRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "surname is required")
    private String surname;

    // Getters & Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }
}
