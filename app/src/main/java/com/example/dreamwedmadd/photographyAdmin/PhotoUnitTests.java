package com.example.dreamwedmadd.photographyAdmin;

public class PhotoUnitTests {

    public boolean MnumberValidate(String MobileNum){

        if (MobileNum.length()==10){
            return true;

        }
        return false;
    }

    public boolean checkNull(String fName,String lName,String Email,String Mobile,String cName,String address,String Price,String description){

        if (fName.equals("")||lName.equals("")||Email.equals("")||Mobile.equals("")||cName.equals("")||address.equals("")||Price.equals("")||description.equals(""))
        {
            return true;
        }

        return false;
    }
    public boolean EmailValid(String Email){
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        boolean istrue=Email.trim().matches(emailPattern);


        return istrue;
    }
    public boolean priceValid(double price){

        if (price<=0){
            return true;

        }
        return false;
    }
    public double TotalPrice(double prise1 ,double dprice ,double vhlprice ,double ctmprioce){
        double total= prise1 + dprice + vhlprice + ctmprioce;
        return total;
    }

}
