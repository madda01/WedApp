package com.example.dreamwedmadd.VehicleAddmin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dreamwedmadd.LoginActivity;
import com.example.dreamwedmadd.R;
import com.example.dreamwedmadd.costumeAdmin.CostumeAdminHome;
import com.example.dreamwedmadd.database.VehicleDBHandler;
import com.example.dreamwedmadd.models.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class AddminVehicleList extends AppCompatActivity {

    //define variables
    private Button add,logout;
    private ListView listView;
    private TextView count;
    Context context;
    private VehicleDBHandler vehicleDBHandler;
    private List<Vehicle> vehicles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmin_vehicle_list);

        //adding ID
        add = findViewById(R.id.addVehicleBtn);
        listView = findViewById(R.id.adminVehicleList);
        logout=findViewById(R.id.btnlogoutvehicle);
        count = findViewById(R.id.vehicleCount);
        context = this;

        //getting data
        SharedPreferences sharedPreferences= getSharedPreferences("login",MODE_PRIVATE);
        String adminemail= sharedPreferences.getString("Email","no email");

        //Initializing
        vehicleDBHandler = new VehicleDBHandler(context);
        vehicles = new ArrayList<>();

        //get vehicle List
        vehicles = vehicleDBHandler.getAllVehicles();

        //display vehicle list using the vehicle adapter
        VehicleAdapter adapter = new VehicleAdapter(context,R.layout.vehicle_single_row,vehicles);
        listView.setAdapter(adapter);

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

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedpreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.remove("Email");
                editor.apply();
                startActivity(new Intent(AddminVehicleList.this, LoginActivity.class));
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Vehicle vehicle = vehicles.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(vehicle.getBrand());
                builder.setMessage(vehicle.getDescription());


                builder.setNegativeButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(context,EditVehicle.class);
                        intent.putExtra("id",String.valueOf(vehicle.getId()));
                        startActivity(intent);
                    }
                });
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(context,DeleteVehicle.class);
                        intent.putExtra("idd",String.valueOf(vehicle.getId()));
                        startActivity(intent);
                    }
                });
                builder.show();
            }
        });

    }
}