package com.example.dreamwedmadd.costumeAdmin;

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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.dreamwedmadd.ForgetPasswordActivity;
import com.example.dreamwedmadd.PasswordConfirmationActivity;
import com.example.dreamwedmadd.R;
import com.example.dreamwedmadd.database.DBConnection;
import com.example.dreamwedmadd.models.Costume;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.ByteArrayOutputStream;

public class CostumeAdd extends AppCompatActivity {
    private EditText title, price, sizes, shop, mobile, desc;
    private Button add;
    private DBConnection dbHandler;
    Context context;
    ImageView imageView;

    public  static final int CAMERA_REQUEST=100;
    public  static final int STORAGE_REQUEST=101;

    String cameraPermission[];
    String storagePermission[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_costume_add);

        title = findViewById(R.id.addtitle);
        price = findViewById(R.id.addprice);
        sizes = findViewById(R.id.addsizes);
        shop = findViewById(R.id.addshop);
        mobile = findViewById(R.id.addmobile);
        desc = findViewById(R.id.adddesc);
        add = findViewById(R.id.btnaddcostume);

        context =this;
        dbHandler=new DBConnection(context);
        imageView=findViewById(R.id.costumeimage);

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

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Title = title.getText().toString();
                String Price = price.getText().toString();
                String Size = sizes.getText().toString();
                String Shop= shop.getText().toString();
                String Phone= mobile.getText().toString();
                String Decs= desc.getText().toString();

                double price=0;
                try{
                    price=Double.parseDouble(Price);
                }catch(NumberFormatException e){
                    Toast.makeText(context, "Please enter valid price", Toast.LENGTH_SHORT).show();
                }

                if (Title.equals("")||Price.equals("")||Size.equals("")||Shop.equals("")||Phone.equals("")||Decs.equals("")){

                    Toast.makeText(context, "Please enter all details", Toast.LENGTH_SHORT).show();
                }

                else {
                    Costume newcostume = new Costume(Title, price, Size, Shop, Phone, Decs,imageViewToBy(imageView));
                    Boolean checkcostumeadding = dbHandler.insertCostume(newcostume);

                    if (checkcostumeadding == true) {
                        Intent i = new Intent(CostumeAdd.this, CostumeAdminHome.class);
                        Toast.makeText(CostumeAdd.this, "Costume added", Toast.LENGTH_LONG).show();
                        startActivity(i);
                    } else {
                        Toast.makeText(CostumeAdd.this, "Insertion was not successful", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private boolean checkCameraPermission() {
        boolean result= ContextCompat.checkSelfPermission(CostumeAdd.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)==(PackageManager.PERMISSION_GRANTED);
        boolean result1=ContextCompat.checkSelfPermission(CostumeAdd.this,Manifest.permission.CAMERA)==(PackageManager.PERMISSION_GRANTED);

        return result && result1;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case CAMERA_REQUEST:{
                if (grantResults.length>0){

                    boolean camera_accepted=grantResults[0]==PackageManager.PERMISSION_GRANTED;

                    if (camera_accepted ){
                        pickFromGallery();

                    }else {
                        Toast.makeText(this, "Please enable permission", Toast.LENGTH_SHORT).show();
                    }
                }

            }break;
            case STORAGE_REQUEST:{
                if (grantResults.length>0){
                    boolean storage_accepted=grantResults[0]==PackageManager.PERMISSION_GRANTED;

                    if (storage_accepted){
                        pickFromGallery();
                    }else {
                        Toast.makeText(this, "Please enable permission", Toast.LENGTH_SHORT).show();
                    }
                }
            }break;
        }


    }

    private void requestStrogePermission() {
        ActivityCompat.requestPermissions(CostumeAdd.this, new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE }, STORAGE_REQUEST);
    }

    private boolean checkStrogepermission() {
        boolean result= ContextCompat.checkSelfPermission(CostumeAdd.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)==(PackageManager.PERMISSION_GRANTED);
        return result;
    }

    private void pickFromGallery() {
        CropImage.activity().start(this);
    }


    private void requestCameraPermission() {
        ActivityCompat.requestPermissions(CostumeAdd.this,new String[] { Manifest.permission.CAMERA }, CAMERA_REQUEST);
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
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                Picasso.with(CostumeAdd.this).load(resultUri).into(imageView);
            }
        }
    }

}
