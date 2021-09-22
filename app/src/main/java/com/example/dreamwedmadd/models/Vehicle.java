package com.example.dreamwedmadd.models;
//vehicle model class
public class Vehicle {

    private int id;
    private String brand,model,year,description,owner,phone,address;
    private double price;
    private byte[] image;

    public Vehicle(String brand, String model, String year, String description, String owner, String phone, String address, double price, byte[] image) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.description = description;
        this.owner = owner;
        this.phone = phone;
        this.address = address;
        this.price = price;
        this.image = image;
    }

    public Vehicle(int id, String brand, String model, String year, String description, String owner, String phone, String address, double price, byte[] image) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.description = description;
        this.owner = owner;
        this.phone = phone;
        this.address = address;
        this.price = price;
        this.image = image;
    }

    public Vehicle(){}

    public Vehicle(int id, String brand, String model, String year, double price, String description, String owner, String phone, String address) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.description = description;
        this.owner = owner;
        this.phone = phone;
        this.address = address;
        this.price = price;
    }

    public Vehicle(String brand, String model, String year,double price, String description, String owner, String phone, String address) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.description = description;
        this.owner = owner;
        this.phone = phone;
        this.address = address;
        this.price = price;
    }



    public void setId(int id) {
        this.id = id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getYear() {
        return year;
    }

    public String getDescription() {
        return description;
    }

    public String getOwner() {
        return owner;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public double getPrice() {
        return price;
    }
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
