package com.example.dreamwedmadd.VehicleAddmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dreamwedmadd.R;
import com.example.dreamwedmadd.database.VehicleDBHandler;
import com.example.dreamwedmadd.models.Vehicle;

public class DeleteVehicle extends AppCompatActivity {

    //Adding References
    private EditText brand,model,year,price,description,owner,phone,address;
    private Button edit;
    private VehicleDBHandler vehicleDBHandler;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_vehicle);

        context=this;
        vehicleDBHandler=new VehicleDBHandler(context);
        //references
        brand = findViewById(R.id.deleteVehBrand);
        model = findViewById(R.id.deleteVehModel);
        year = findViewById(R.id.deleteVehYear);
        price = findViewById(R.id.deleteVehPrice);
        description = findViewById(R.id.deleteVehDescription);
        owner = findViewById(R.id.deleteVehOwner);
        phone = findViewById(R.id.deleteVehPhone);
        address = findViewById(R.id.deleteVehAddress);

        edit = findViewById(R.id.vehdeleteBtn);

        final String idd = getIntent().getStringExtra("idd");
        Vehicle vehicle = vehicleDBHandler.getSingleVehicle(Integer.parseInt(idd));



        brand.setText(vehicle.getBrand());
        model.setText(vehicle.getModel());
        year.setText(vehicle.getYear());
        price.setText(String.valueOf(vehicle.getPrice()));
        description.setText(vehicle.getDescription());
        owner.setText(vehicle.getOwner());
        phone.setText(vehicle.getPhone());
        address.setText(vehicle.getAddress());

        //disable accessing
        brand.setKeyListener(null);
        model.setKeyListener(null);
        year.setKeyListener(null);
        price.setKeyListener(null);
        description.setKeyListener(null);
        owner.setKeyListener(null);
        phone.setKeyListener(null);
        address.setKeyListener(null);

        //edit button handle
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                vehicleDBHandler.deleteVehicle(vehicle.getId());
                startActivity(new Intent(context,AddminVehicleList.class));
            }
        });

    }
}