package com.urucas.sting.model;

/**
 * @copyright Urucas
 * @license   Copyright (C) 2013. All rights reserved
 * @link       http://urucas.com
 * @developers Bruno Alassia, Pamela Prosperi
 * @date {5/29/14}
**/
public class User {

    private int id;
    private String name, email, pass;
    private String token;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
