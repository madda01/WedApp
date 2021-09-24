package com.example.dreamwedmadd.DecorationAdmin;

public class TestDecoMethods {
    //method to get a last price
    public double getLastPrice(double price, double charge) {

        double k=0;
        k=price+price*charge/100;
        return k;
    }

}
