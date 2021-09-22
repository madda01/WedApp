package com.example.dreamwedmadd.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dreamwedmadd.R;
import com.example.dreamwedmadd.VehicleAddmin.AddminVehicleList;
import com.example.dreamwedmadd.database.VehicleDBHandler;
import com.example.dreamwedmadd.models.Vehicle;

public class CustomerVehicleView extends AppCompatActivity {

    //Adding References
    private TextView brand,model,year,price,description,owner,phone,address;
    private Button odr;
    private VehicleDBHandler vehicleDBHandler;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_vehicle_view);

        context=this;
        vehicleDBHandler=new VehicleDBHandler(context);

        brand = findViewById(R.id.cusVehBrand);
        model = findViewById(R.id.cusVehModel);
        year = findViewById(R.id.cusVehYear);
        price = findViewById(R.id.cusVehPrice);
        description = findViewById(R.id.cusVehDescription);
        owner = findViewById(R.id.cusVehOwner);
        phone = findViewById(R.id.cusVehPhone);
        address = findViewById(R.id.cusVehAddress);

        odr = findViewById(R.id.vehBookBtn);

        final String id = getIntent().getStringExtra("id");
        Vehicle vehicle = vehicleDBHandler.getSingleVehicle(Integer.parseInt(id));



        brand.setText(vehicle.getBrand());
        model.setText(vehicle.getModel());
        year.setText(vehicle.getYear());
        price.setText(String.valueOf(vehicle.getPrice()));
        description.setText(vehicle.getDescription());
        owner.setText(vehicle.getOwner());
        phone.setText(vehicle.getPhone());
        address.setText(vehicle.getAddress());

        brand.setKeyListener(null);
        model.setKeyListener(null);
        year.setKeyListener(null);
        price.setKeyListener(null);
        description.setKeyListener(null);
        owner.setKeyListener(null);
        phone.setKeyListener(null);
        address.setKeyListener(null);

        odr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                SharedPreferences sharedPreferences = getSharedPreferences("customercart",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("vename",brand.getText().toString() +"" + model.getText().toString() );
                editor.putString("veprice",price.getText().toString());
                editor.putString("vowner",owner.getText().toString());
                editor.commit();

                startActivity(new Intent(context, customercart.class));



            }
        });

    }
}