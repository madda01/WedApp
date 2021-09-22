package com.example.dreamwedmadd.photographyAdmin;

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
import com.example.dreamwedmadd.database.photoDbHandler;
import com.example.dreamwedmadd.models.Photographermodel;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.ByteArrayOutputStream;

public class addPhotographer extends AppCompatActivity {

    private  EditText fname,lname,email,mobilenum,companyname,address,price,description ;
    private  Button addbtn ;
    private photoDbHandler photoDbhandler ;
    private Context context;
    private ImageView imageView;

    public  static final int CAMERA_REQUEST=100;
    public  static final int STORAGE_REQUEST=101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photographer);

        fname = findViewById(R.id.etfirstname);
        lname = findViewById(R.id.vehModel);
        email = findViewById(R.id.vehYear);
        mobilenum = findViewById(R.id.vehPhone);
        companyname = findViewById(R.id.vehOwner);
        address = findViewById(R.id.vehAddress);
        price = findViewById(R.id.vehPrice);
        description = findViewById(R.id.vehDescription);
        imageView=findViewById(R.id.PhoImgAdd);

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

        context=this;
        addbtn = findViewById(R.id.vehAddBtn);

        photoDbhandler = new photoDbHandler(context);


        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fnme = fname.getText().toString();
                String lnme = lname.getText().toString();
                String eml = email.getText().toString();
                String mobilen = mobilenum.getText().toString();
                String cpyname = companyname.getText().toString();
                String addr = address.getText().toString();
                String pri = price.getText().toString();
                String dcri = description.getText().toString();

                double prise=0 ;
                try{
                    prise =Double.parseDouble(pri);
                }catch (NumberFormatException e){
                    Toast.makeText(context, "Please enter valid number", Toast.LENGTH_SHORT).show();
                }
                Photographermodel phto = new Photographermodel(
                        fnme,
                        lnme,
                        eml,
                        mobilen,
                        cpyname,
                        addr,
                        prise,
                        dcri,
                        imageViewToBy(imageView)

                );
                photoDbhandler.addPhotographer(phto);
                startActivity(new Intent(context,photography_Mainlist.class));

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

        ActivityCompat.requestPermissions(addPhotographer.this, new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE }, STORAGE_REQUEST);
    }

    private boolean checkStrogepermission() {

        boolean result= ContextCompat.checkSelfPermission(addPhotographer.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)==(PackageManager.PERMISSION_GRANTED);
        return result;

    }

    private void pickFromGallery() {
        CropImage.activity().start(this);
    }


    private void requestCameraPermission() {

//        requestPermissions(cameraPermission,CAMERA_REQUEST);
        ActivityCompat.requestPermissions(addPhotographer.this,new String[] { Manifest.permission.CAMERA }, CAMERA_REQUEST);


    }

    private boolean checkCameraPermission() {

        boolean result= ContextCompat.checkSelfPermission(addPhotographer.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)==(PackageManager.PERMISSION_GRANTED);
        boolean result1=ContextCompat.checkSelfPermission(addPhotographer.this,Manifest.permission.CAMERA)==(PackageManager.PERMISSION_GRANTED);

        return result && result1;

    }

    public static byte[] imageViewToBy(ImageView imageView) {


        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] imageInByte = baos.toByteArray();
        return imageInByte;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result=CropImage.getActivityResult(data);

            if (resultCode==RESULT_OK){
                Uri resultUri=result.getUri();
                Picasso.with(addPhotographer.this).load(resultUri).into(imageView);
            }
        }

    }


}