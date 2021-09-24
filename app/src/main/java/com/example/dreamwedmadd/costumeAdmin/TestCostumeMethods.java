package com.example.dreamwedmadd.costumeAdmin;

public class TestCostumeMethods {

    //method to validate the email
    public static boolean validateEmail(String email){
        String emailcheck= "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        boolean iscorrect= email.trim().matches(emailcheck);
        return iscorrect;
    }

    //method to get service charges
    public static double getNewPrice(double price, double rate) {
        double finalamount=0;
        finalamount = ((price*rate)+price); //adding our service charge of 10%
        return finalamount;
    }

    //method to check the phone number digits
    public static boolean validateMobile(String phone){
        int k=0;
        k=phone.length();
        if(k==10){
            return true;
        }
        return false;
    }

}
