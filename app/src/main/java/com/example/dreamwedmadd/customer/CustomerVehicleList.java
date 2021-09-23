package com.example.dreamwedmadd.customer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.dreamwedmadd.R;
import com.example.dreamwedmadd.VehicleAddmin.DeleteVehicle;
import com.example.dreamwedmadd.VehicleAddmin.EditVehicle;
import com.example.dreamwedmadd.VehicleAddmin.VehicleAdapter;
import com.example.dreamwedmadd.database.VehicleDBHandler;
import com.example.dreamwedmadd.models.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class CustomerVehicleList extends AppCompatActivity {

    //define variables
    private ListView listView;
    Context context;
    private VehicleDBHandler vehicleDBHandler;
    private List<Vehicle> vehicles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R .layout.activity_customer_vehicle_list);

        //adding ID
        listView = findViewById(R.id.cusVehicleList);
        context = this;

        //Initializing
        vehicleDBHandler = new VehicleDBHandler(context);
        vehicles = new ArrayList<>();

        //get vehicle List
        vehicles = vehicleDBHandler.getAllVehicles();

        //display vehicle list using the vehicle adapter
        VehicleAdapter adapter = new VehicleAdapter(context,R.layout.vehicle_single_row,vehicles);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Vehicle vehicle = vehicles.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(vehicle.getBrand());
                builder.setMessage(vehicle.getDescription());

                //navigate customer to the selected vehicle
                builder.setPositiveButton("View More", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(context, CustomerVehicleView.class);
                        intent.putExtra("id",String.valueOf(vehicle.getId()));
                        startActivity(intent);
                    }
                });
                builder.show();
            }
        });



    }
}