package com.example.dreamwedmadd.DecorationAdmin;

public class TestDecoMethods {
    //method to get a last price
    public double getLastPrice(double price, double charge) {

        double k=0;
        k=price+price*charge/100;
        return k;
    }
    public boolean validateEmail(String email){
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        boolean istrue=email.trim().matches(emailPattern);


        return istrue;
    }
    public boolean validatePhone(String phone){
        int i=0;
        i=phone.length();
        if(i==10){
            return true;
        }
        return false;

    }
    public boolean vlidateName(String Fname,String Lname){

        boolean istrue=false;
        if (Fname.length()<=3 || Lname.length()<=3){
            return istrue;
        }

        return true;
    }

    public boolean nullDetails(String fName,String lName,String Email,String Mobile,String cName,String address,String description,String Price,String serviceCharge){
        boolean isnull=false;

        if (fName.equals("")||lName.equals("")||Email.equals("")||Mobile.equals("")||cName.equals("")||address.equals("")||description.equals("")||Price.equals("")||serviceCharge.equals("")){

            return isnull;
        }


        return true;
    }
    public boolean priceValid(double price){

        if (price<=0){
            return true;

        }
        return false;
    }

}
