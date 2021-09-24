package com.example.dreamwedmadd.models;

import java.text.DecimalFormat;

public class Rating {

    private int id;
    private float decoRating;
    private float photoRating;
    private float cosRating;
    private float vehRating;
    private float totalRate;
    private float rateCount;
    private float rateSum;

    public Rating(){}

    public Rating(int id, float decoRating, float photoRating, float cosRating, float vehRating) {
        this.id = id;
        this.decoRating = decoRating;
        this.photoRating = photoRating;
        this.cosRating = cosRating;
        this.vehRating = vehRating;

    }

    public Rating(float decoRating, float photoRating, float cosRating, float vehRating) {
        this.decoRating = decoRating;
        this.photoRating = photoRating;
        this.cosRating = cosRating;
        this.vehRating = vehRating;

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDecoRating(float decoRating) {
        this.decoRating = decoRating;
    }

    public void setPhotoRating(float photoRating) {
        this.photoRating = photoRating;
    }

    public void setCosRating(float cosRating) {
        this.cosRating = cosRating;
    }

    public void setVehRating(float vehRating) {
        this.vehRating = vehRating;
    }

    public int getId() {
        return id;
    }

    public float getDecoRating() {
        return decoRating;
    }

    public float getPhotoRating() {
        return photoRating;
    }

    public float getCosRating() {
        return cosRating;
    }

    public float getVehRating() {
        return vehRating;
    }

    public float CalculateRate(float rateCount, float rateSum){

        totalRate = (rateSum/(rateCount*5))*5f;

        //float value into 2 decimal points
        DecimalFormat fd = new DecimalFormat("#.##");
        float f =Float.valueOf(fd.format(totalRate));

        return f;

    }
}
