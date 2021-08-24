package com.example.dreamwedmadd.models;

public class Costume {
    String title;
    String price;
    String discount;
    String sizes;
    String shop;
    String tmobile;
    String description;

    public Costume(){}

    public Costume(String title, String price, String discount, String sizes, String shop, String tmobile, String description) {
        this.title = title;
        this.price = price;
        this.discount = discount;
        this.sizes = sizes;
        this.shop = shop;
        this.tmobile = tmobile;
        this.description = description;
    }

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

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getSizes() {
        return sizes;
    }

    public void setSizes(String sizes) {
        this.sizes = sizes;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getTmobile() {
        return tmobile;
    }

    public void setTmobile(String tmobile) {
        this.tmobile = tmobile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

