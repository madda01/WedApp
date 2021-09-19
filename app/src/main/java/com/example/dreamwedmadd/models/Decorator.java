package com.example.dreamwedmadd.models;

public class Decorator {

    int id;
    private String fName ,lName,Email,Mobile,cName,description,address;
    private double price;

    public Decorator(){

    }

    public Decorator(String fName, String lName, String email, String mobile, String cName, String description, String address, double price) {
        this.fName = fName;
        this.lName = lName;
        Email = email;
        Mobile = mobile;
        this.cName = cName;
        this.description = description;
        this.address = address;
        this.price = price;
    }

    public Decorator(int id, String fName, String lName, String email, String mobile, String cName, String description, String address, double price) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        Email = email;
        Mobile = mobile;
        this.cName = cName;
        this.description = description;
        this.address = address;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
