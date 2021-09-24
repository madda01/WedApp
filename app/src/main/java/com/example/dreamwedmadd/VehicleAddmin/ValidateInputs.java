package com.example.dreamwedmadd.VehicleAddmin;

import android.widget.Toast;

public class ValidateInputs {

          private  String vehicleBrand ;
          private  String vehicleModel ;
          private  String vehicleYear ;
          private  String vehiclePrice ;
          private  String vehicleDescription;
          private  String vehicleOwner ;
          private  String vehiclePhone ;
          private  String vehicleAddress;

          public ValidateInputs(){}

          public boolean ValidateData(String vehicleBrand,String vehicleModel,String vehicleYear,String vehiclePrice,String vehicleDescription, String vehicleOwner, String vehiclePhone, String vehicleAddress){

            if (vehicleBrand.equals("")||vehicleModel.equals("")||vehicleYear.equals("")||vehiclePrice.equals("")||vehicleDescription.equals("")||vehicleOwner.equals("")||vehiclePhone.equals("")||vehicleAddress.equals("")){
                return true;
            }
            return false;
          }

          public boolean ValidatePhone(String phone){

              if(phone.length() != 10)
                  return true;
              else
                  return false;

          }

          public boolean ValidatePrice(double price){

              if(price==0)
                  return true;
              else
                  return false;

          }

          public boolean ValidateDate(String date){

              if(date.length() != 4)
                  return true;
              else
                  return false;

          }
}
