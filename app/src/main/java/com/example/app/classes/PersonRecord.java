package com.example.app.classes;

public class PersonRecord {

    private int identifier;
    private String lastName;
    private String firstName;

    public PersonRecord() {}

    public PersonRecord(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}