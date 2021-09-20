package com.example.dreamwedmadd.VehicleAddmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dreamwedmadd.R;
import com.example.dreamwedmadd.database.VehicleDBHandler;

public class AddminVehicleList extends AppCompatActivity {

    //define variables
    private Button add;
    private ListView listView;
    private TextView count;
    Context context;
    VehicleDBHandler vehicleDBHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmin_vehicle_list);

        //adding ID
        add = findViewById(R.id.addVehicleBtn);
        listView = findViewById(R.id.adminVehicleList);
        count = findViewById(R.id.vehicleCount);
        context = this;

        //Initializing
        vehicleDBHandler = new VehicleDBHandler(context);

        //getVehicle count from database
        int vehicleCount = vehicleDBHandler.countVehicles();

        //display vehicle count
        count.setText("You have "+vehicleCount+" vehicles");

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context,AddVehicle.class));
            }
        });

    }
}