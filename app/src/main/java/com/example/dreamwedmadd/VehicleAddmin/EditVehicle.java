package com.example.dreamwedmadd.VehicleAddmin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.dreamwedmadd.R;

public class EditVehicle extends AppCompatActivity {

    //Adding References
    private EditText brand,model,year,price,description,owner,phone,address;
    private Button edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_vehicle);

        brand = findViewById(R.id.editVehBrand);
        model = findViewById(R.id.editVehModel);
        year = findViewById(R.id.editVehYear);
        price = findViewById(R.id.editVehPrice);
        description = findViewById(R.id.editVehDescription);
        owner = findViewById(R.id.editVehOwner);
        phone = findViewById(R.id.editVehPhone);
        address = findViewById(R.id.editVehAddress);

        edit = findViewById(R.id.vehEditBtn);

    }
}