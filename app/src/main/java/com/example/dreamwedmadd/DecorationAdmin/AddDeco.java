package com.example.dreamwedmadd.DecorationAdmin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
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
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dreamwedmadd.R;
import com.example.dreamwedmadd.database.DBDecorator;
import com.example.dreamwedmadd.models.Decorator;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class AddDeco extends AppCompatActivity {

    Button btn,btnE;
    EditText et1,et2,et3,et4,et5,et6,et7,et8;
    Context context;
    DBDecorator dbDecorator;
    ImageView imageView;


    public  static final int CAMERA_REQUEST=100;
    public  static final int STORAGE_REQUEST=101;

    String cameraPermission[];
    String storagePermission[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_deco);
        btn=findViewById(R.id.btnDecoSub);
        et1=findViewById(R.id.etDecoFname);
        et2=findViewById(R.id.etDecoLname);
        et3=findViewById(R.id.etDecoEmail);
        et4=findViewById(R.id.etDecoMobile);
        et5=findViewById(R.id.etDecoCname);
        et6=findViewById(R.id.etDecoAddress);
        et7=findViewById(R.id.etDecoPRice);
        et8=findViewById(R.id.etDecoDes);
        btnE=findViewById(R.id.DecoEmail);
        context =this;
        dbDecorator=new DBDecorator(context);
        imageView=(ImageView) findViewById(R.id.DecoImageUp);

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

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               String fName=et1.getText().toString();
               String lName=et2.getText().toString();
               String  Email=et3.getText().toString();
               String Mobile=et4.getText().toString();
               String cName=et5.getText().toString();
               String address=et6.getText().toString();
               String description=et8.getText().toString();
               String Price=et7.getText().toString();





                double price=0;
               try {
                   price=Double.parseDouble(Price);
               }catch (NumberFormatException e){
                   Toast.makeText(context, "Please enter valid price", Toast.LENGTH_SHORT).show();

               }



               if (fName.equals("")||lName.equals("")||Email.equals("")||Mobile.equals("")||cName.equals("")||address.equals("")||description.equals("")||Price.equals("")){

                   Toast.makeText(context, "Please enter all details", Toast.LENGTH_SHORT).show();
               }
                else {
                   Decorator decorator=new Decorator(fName,lName,Email,Mobile,cName,description,address,price,imageViewToBy(imageView));
                   dbDecorator.addDeco(decorator);
                   startActivity(new Intent(context,AdminDecoView.class));

               }


            }


        });

        btnE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_SEND);
                Intent chooser;
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL,new String[]{et3.getText().toString()});
                intent.putExtra(Intent.EXTRA_SUBJECT,"You are added to the Dream Wedding App");
                intent.putExtra(Intent.EXTRA_TEXT,"Congratulations we have now added you to our app.");
                intent.setType("text/plain");
                chooser=Intent.createChooser(intent,"Send Email test App");
                startActivity(chooser);

            }
        });





    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case CAMERA_REQUEST:{
                if (grantResults.length>0){

                    boolean camera_accepted=grantResults[0]==PackageManager.PERMISSION_GRANTED;
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

        ActivityCompat.requestPermissions(AddDeco.this, new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE }, STORAGE_REQUEST);
    }

    private boolean checkStrogepermission() {

        boolean result= ContextCompat.checkSelfPermission(AddDeco.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)==(PackageManager.PERMISSION_GRANTED);
        return result;

    }

    private void pickFromGallery() {
        CropImage.activity().start(this);
    }


    private void requestCameraPermission() {

//        requestPermissions(cameraPermission,CAMERA_REQUEST);
        ActivityCompat.requestPermissions(AddDeco.this,new String[] { Manifest.permission.CAMERA }, CAMERA_REQUEST);


    }

    private boolean checkCameraPermission() {

        boolean result= ContextCompat.checkSelfPermission(AddDeco.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)==(PackageManager.PERMISSION_GRANTED);
        boolean result1=ContextCompat.checkSelfPermission(AddDeco.this,Manifest.permission.CAMERA)==(PackageManager.PERMISSION_GRANTED);

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
                Picasso.with(AddDeco.this).load(resultUri).into(imageView);
            }
        }

    }


}