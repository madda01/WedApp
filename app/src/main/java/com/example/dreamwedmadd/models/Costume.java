package com.example.dreamwedmadd.models;
//model class for costumes
public class Costume {
    private int id;
    private String title;
    private String price;
    private String size;
    private String shop;
    private String phone;
    private String description;
    private int costumepic;

    public Costume(){}

    public Costume(int id,String title, String price, String size, String shop, String phone, String description) {
        this.id=id;
        this.title = title;
        this.price = price;
        this.size = size;
        this.shop = shop;
        this.phone = phone;
        this.description = description;
    }

    public Costume(String title, String price, String size, String shop, String phone, String description) {
        this.title = title;
        this.price = price;
        this.size = size;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
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

    public int getCostumepic() { return costumepic; }

    public void setCostumepic(int costumepic) { this.costumepic = costumepic; }
}


