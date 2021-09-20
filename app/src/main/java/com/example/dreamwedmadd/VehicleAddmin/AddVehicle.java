package com.example.dreamwedmadd.VehicleAddmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dreamwedmadd.R;
import com.example.dreamwedmadd.database.VehicleDBHandler;
import com.example.dreamwedmadd.models.Vehicle;

public class AddVehicle extends AppCompatActivity {

    //creating objects
    private EditText brand,model,year,price,description,owner,phone,address;
    private Button add;
    Context context;
    VehicleDBHandler vehicleDBHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);

        //Adding references by IDs
        brand = findViewById(R.id.vehBrand);
        model = findViewById(R.id.vehModel);
        year = findViewById(R.id.vehYear);
        price = findViewById(R.id.vehPrice);
        description = findViewById(R.id.vehDescription);
        owner = findViewById(R.id.vehOwner);
        phone = findViewById(R.id.vehPhone);
        address = findViewById(R.id.vehAddress);

        add = findViewById(R.id.vehAddBtn);

        //initializing objects
        context = this;
        vehicleDBHandler = new VehicleDBHandler(context);

        //handle add button
        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //getting input values
                String vehicleBrand = brand.getText().toString();
                String vehicleModel = model.getText().toString();
                String vehicleYear = year.getText().toString();
                String vehiclePrice = price.getText().toString();
                String vehicleDescription = description.getText().toString();
                String vehicleOwner = owner.getText().toString();
                String vehiclePhone = phone.getText().toString();
                String vehicleAddress = address.getText().toString();

                //string price convert to double
                double doublePrice=0 ;
                try{
                    doublePrice =Double.parseDouble(vehiclePrice);
                }catch (NumberFormatException e){
                    Toast.makeText(context, "Please enter valid number", Toast.LENGTH_SHORT).show();
                }

                //adding vehicle data into database
                Vehicle vehicle = new Vehicle(vehicleBrand,vehicleModel,vehicleYear,doublePrice,vehicleDescription,vehicleOwner,vehiclePhone,vehicleAddress);
                vehicleDBHandler.addVehicle(vehicle);

                //after  insertion redirect to Vehicle List
                startActivity(new Intent(context,AddminVehicleList.class));

            }
        });

    }
}