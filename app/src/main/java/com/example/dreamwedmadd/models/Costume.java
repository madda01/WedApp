package com.example.dreamwedmadd.models;
//model class for costumes
public class Costume {
    private int id;
    private String title;
    private double price;
    private String email;
    private String shop;
    private String phone;
    private String description;
    private byte[] image;

    public Costume(){}

    public Costume(int id,String title, double price, String email, String shop, String phone, String description, byte[] image) {
        this.id=id;
        this.title = title;
        this.price = price;
        this.email = email;
        this.shop = shop;
        this.phone = phone;
        this.description = description;
        this.image=image;
    }

    public Costume(String title, double price, String email, String shop, String phone, String description, byte[] image) {
        this.title = title;
        this.price = price;
        this.email = email;
        this.shop = shop;
        this.phone = phone;
        this.description = description;
        this.image=image;
    }

    public Costume(int id, String title, double price, String email, String shop, String phone, String description) {
        this.id=id;
        this.title = title;
        this.price = price;
        this.email = email;
        this.shop = shop;
        this.phone = phone;
        this.description = description;
    }

    public int getId() { return id; }

    public void setId(int cosid) { this.id = cosid; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() { return image; }

    public void setImage(byte[] image) { this.image = image; }
}


