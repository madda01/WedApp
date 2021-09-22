package com.example.dreamwedmadd.models;
//model class for user
public class User {
    private int id;
    private String name;
    private String email;
    private String mobile;
    private String password;

    public User() {}

    public User(int id, String name, String email, String mobile, String password) {
        this.id=id;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
    }

    public User(String name, String email, String mobile, String password) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
    }

    public User(String email){
        this.email=email;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
