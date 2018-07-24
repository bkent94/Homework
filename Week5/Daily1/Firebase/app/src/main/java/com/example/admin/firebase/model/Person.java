package com.example.admin.firebase.model;

public class Person {
    String firstName;
    String lastName;

    public Person(){

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Person(String firstName, String lastName) {

        this.firstName = firstName;
        this.lastName = lastName;
    }
}
