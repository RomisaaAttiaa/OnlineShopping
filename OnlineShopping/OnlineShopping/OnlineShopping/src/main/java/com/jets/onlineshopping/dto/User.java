/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.onlineshopping.dto;

import java.util.Date;

/**
 *
 * @author Eslam
 */
public class User {

    private String email;
    private String name;
    private String password;
    private Date birthdate;
    private String job;
    private float creditLimit;
    private String address;
    private String role;

    private static String ROLE_USER = "user";
    private static String ROLE_ADMIN = "admin";

    public User() {
    }

    public User(String email, String name, String password, Date birthdate, String job, float creditLimit, String address, String role) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.birthdate = birthdate;
        this.job = job;
        this.creditLimit = creditLimit;
        this.address = address;
        this.role = role;
    }

    public User(String email, String name) {
        this.email = email;
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public float getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(float creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static String getROLE_USER() {
        return ROLE_USER;
    }

    public static void setROLE_USER(String aROLE_USER) {
        ROLE_USER = aROLE_USER;
    }

    public static String getROLE_ADMIN() {
        return ROLE_ADMIN;
    }

    public static void setROLE_ADMIN(String aROLE_ADMIN) {
        ROLE_ADMIN = aROLE_ADMIN;
    }

}
