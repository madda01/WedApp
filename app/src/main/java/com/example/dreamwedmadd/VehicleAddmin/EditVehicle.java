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

public class EditVehicle extends AppCompatActivity {

    //Adding References
    private EditText brand,model,year,price,description,owner,phone,address;
    private Button edit;
    private VehicleDBHandler vehicleDBHandler;
    private Context context;
    ValidateInputs validateInputs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_vehicle);

        context=this;
        vehicleDBHandler=new VehicleDBHandler(context);

        //references
        brand = findViewById(R.id.editVehBrand);
        model = findViewById(R.id.editVehModel);
        year = findViewById(R.id.editVehYear);
        price = findViewById(R.id.editVehPrice);
        description = findViewById(R.id.editVehDescription);
        owner = findViewById(R.id.editVehOwner);
        phone = findViewById(R.id.editVehPhone);
        address = findViewById(R.id.editVehAddress);

        edit = findViewById(R.id.vehEditBtn);

        final String id = getIntent().getStringExtra("id");
        Vehicle vehicle = vehicleDBHandler.getSingleVehicle(Integer.parseInt(id));

        validateInputs = new ValidateInputs();

        //adding data to object
        brand.setText(vehicle.getBrand());
        model.setText(vehicle.getModel());
        year.setText(vehicle.getYear());
        price.setText(String.valueOf(vehicle.getPrice()));
        description.setText(vehicle.getDescription());
        owner.setText(vehicle.getOwner());
        phone.setText(vehicle.getPhone());
        address.setText(vehicle.getAddress());

        System.out.println(phone);

        //handling button
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String editBrand = brand.getText().toString();
                String editModel = model.getText().toString();
                String editYear = year.getText().toString();
                String editPrice = price.getText().toString();
                String editDescription = description.getText().toString();
                String editOwner = owner.getText().toString();
                String editPhone = phone.getText().toString();
                String editAddress = address.getText().toString();

                //string price convert to double
                double doublePrice=0 ;
                try{
                    doublePrice =Double.parseDouble(editPrice);
                }catch (NumberFormatException e){
                    Toast.makeText(context, "Please enter valid number", Toast.LENGTH_SHORT).show();
                }


                //Validations using Validate input class

                boolean invalid = validateInputs.ValidateData(editBrand,editModel,editYear,editPrice,editDescription,editOwner,editPhone,editAddress);
                boolean invalidPhone = validateInputs.ValidatePhone(editPhone);
                boolean invalidPrice = validateInputs.ValidatePrice(doublePrice);
                boolean invalidDate = validateInputs.ValidateDate(editYear);

                if (invalid){

                    Toast.makeText(context, "Please enter all details", Toast.LENGTH_SHORT).show();

                }
                else if(invalidPrice){

                    Toast.makeText(context, "Please enter price", Toast.LENGTH_SHORT).show();
                }
                else if(invalidPhone){

                    Toast.makeText(context, "Please enter a valid phone number", Toast.LENGTH_SHORT).show();
                }
                else if(invalidDate){

                    Toast.makeText(context, "Please enter a valid model year", Toast.LENGTH_SHORT).show();
                }else {
                    Vehicle vehicle = new Vehicle(Integer.parseInt(id), editBrand, editModel, editYear, doublePrice, editDescription, editOwner, editPhone, editAddress);
                    int state = vehicleDBHandler.updateVehicle(vehicle);
                    startActivity(new Intent(context, AddminVehicleList.class));
                }
            }
        });

    }
}