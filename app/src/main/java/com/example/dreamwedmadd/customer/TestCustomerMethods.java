package com.example.dreamwedmadd.customer;

public class TestCustomerMethods {
    //method to validate the email
    public static boolean validateEmail(String email){
        String emailcheck= "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        boolean iscorrect= email.trim().matches(emailcheck);
        return iscorrect;
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

    //method to validate username
    public static boolean validateName(String name) {
        int i=0;
        i=name.length();
        if(i>3){
            return true;
        }
        return false;
    }

    //method to validate password
    public static boolean validatePassword(String password) {
        int i=0;
        i=password.length();
        if(i>4){
            return true;
        }
        return false;
    }
}
