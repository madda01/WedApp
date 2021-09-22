package com.example.dreamwedmadd.VehicleAddmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dreamwedmadd.DecorationAdmin.AddDeco;
import com.example.dreamwedmadd.R;
import com.example.dreamwedmadd.database.VehicleDBHandler;
import com.example.dreamwedmadd.models.Vehicle;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.ByteArrayOutputStream;

public class AddVehicle extends AppCompatActivity {

    //creating objects
    private EditText brand,model,year,price,description,owner,phone,address;
    private Button add;
    Context context;
    VehicleDBHandler vehicleDBHandler;
    ImageView imageView;

    public  static final int CAMERA_REQUEST=100;
    public  static final int STORAGE_REQUEST=101;

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
        imageView=findViewById(R.id.vehAddImg);

        add = findViewById(R.id.vehAddBtn);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int imageView=0;
                if (imageView==0){
                    if (!checkCameraPermission()){
                        requestCameraPermission();
                    }else {
                        pickFromGallery();
                    }
                }else if (imageView==1){
                    if (!checkStrogepermission()){
                        requestStrogePermission();
                    }else{
                        pickFromGallery();
                    }
                }
            }
        });

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
                Vehicle vehicle = new Vehicle(vehicleBrand,vehicleModel,vehicleYear,vehicleDescription,vehicleOwner,vehiclePhone,vehicleAddress,doublePrice,imageViewToBy(imageView));
                vehicleDBHandler.addVehicle(vehicle);

                //after  insertion redirect to Vehicle List
                startActivity(new Intent(context,AddminVehicleList.class));

            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case CAMERA_REQUEST:{
                if (grantResults.length>0){

                    boolean camera_accepted=grantResults[0]== PackageManager.PERMISSION_GRANTED;
//                    boolean storage_accepted=grantResults[1]==PackageManager.PERMISSION_GRANTED;

                    if (camera_accepted ){
                        pickFromGallery();

                    }else {
                        Toast.makeText(this, "enable camera and storage permissions", Toast.LENGTH_SHORT).show();
                    }
                }

            }break;
            case STORAGE_REQUEST:{
                if (grantResults.length>0){
                    boolean storage_accepted=grantResults[0]==PackageManager.PERMISSION_GRANTED;

                    if (storage_accepted){
                        pickFromGallery();
                    }else {
                        Toast.makeText(this, "enable camera and storage permissions", Toast.LENGTH_SHORT).show();
                    }
                }
            }break;
        }


    }


    private void requestStrogePermission() {
//        requestPermissions(storagePermission,STORAGE_REQUEST);

        ActivityCompat.requestPermissions(AddVehicle.this, new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE }, STORAGE_REQUEST);
    }

    private boolean checkStrogepermission() {

        boolean result= ContextCompat.checkSelfPermission(AddVehicle.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)==(PackageManager.PERMISSION_GRANTED);
        return result;

    }

    private void pickFromGallery() {
        CropImage.activity().start(this);
    }


    private void requestCameraPermission() {

//        requestPermissions(cameraPermission,CAMERA_REQUEST);
        ActivityCompat.requestPermissions(AddVehicle.this,new String[] { Manifest.permission.CAMERA }, CAMERA_REQUEST);


    }

    private boolean checkCameraPermission() {

        boolean result= ContextCompat.checkSelfPermission(AddVehicle.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)==(PackageManager.PERMISSION_GRANTED);
        boolean result1=ContextCompat.checkSelfPermission(AddVehicle.this,Manifest.permission.CAMERA)==(PackageManager.PERMISSION_GRANTED);

        return result && result1;

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result=CropImage.getActivityResult(data);

            if (resultCode==RESULT_OK){
                Uri resultUri=result.getUri();
                Picasso.with(AddVehicle.this).load(resultUri).into(imageView);
            }
        }

    }

    private byte[] imageViewToBy(ImageView imageView) {
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] imageInByte = baos.toByteArray();
        return imageInByte;
    }


}