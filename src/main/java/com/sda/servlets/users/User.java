package com.sda.servlets.users;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    Integer id;
    String firstName;
    String lastName;
    int age;
    String gender;
    @JsonCreator
    public User(
           @JsonProperty("firstName") String firstName,
           @JsonProperty("lastName") String lastName,
           @JsonProperty("age")int age,
           @JsonProperty("gender") String gender,
           @JsonProperty("id") Integer id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.id=id;
    }

    public User(String firstName, String lastName, int age, String gender) {
    this(firstName,lastName,age,gender,null); //----------wywołanie konstruktora !! z poziomu cztero parametrowego konsturktora
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
