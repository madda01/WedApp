package com.example.dreamwedmadd.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dreamwedmadd.MainActivity2;
import com.example.dreamwedmadd.R;
import com.example.dreamwedmadd.database.photoDbHandler;
import com.example.dreamwedmadd.models.Photographermodel;
import com.example.dreamwedmadd.photographyAdmin.photography_Mainlist;

public class singlePhotographerCutomerView extends AppCompatActivity {

    //views
    private EditText fnameeditrl,lnameeditrl,emaileditrl,mobilenumeditrl,companynameeditrl,addresseditrl,priceeditrl,descriptioneditrl ;
    private TextView photographername;
    private Button addbtneditrl ;
    private photoDbHandler photoDbhandler;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_photographer_cutomer_view);

        //link views
        context=this;
        photoDbhandler = new photoDbHandler(context);
        photographername = findViewById(R.id.tvaddphotoeditre);
        fnameeditrl = findViewById(R.id.etfirstnameeditre);
        lnameeditrl = findViewById(R.id.etlastnameeditre);
        emaileditrl = findViewById(R.id.etemaileditre);
        mobilenumeditrl = findViewById(R.id.etphoneeditre);
        companynameeditrl = findViewById(R.id.etcompanynameeditre);
        addresseditrl = findViewById(R.id.etaddresseditre);
        priceeditrl = findViewById(R.id.etpriceeditre);
        descriptioneditrl = findViewById(R.id.etdescriptioneditre);

        addbtneditrl = findViewById(R.id.btn5editre);

        final String id = getIntent().getStringExtra("id");



        //create object from photographer model class and call database for get photographer information
        Photographermodel photol = photoDbhandler.getSinglePhotographer(Integer.parseInt(id));

        //set photographer information to views
        photographername.setText(photol.getFnamee()+" "+photol.getLnamee());
        fnameeditrl.setText(photol.getFnamee());
        lnameeditrl.setText(photol.getLnamee());
        emaileditrl.setText(photol.getEmaile());
        mobilenumeditrl.setText(photol.getPhonee());
        companynameeditrl.setText(photol.getComanpnynamee());
        addresseditrl.setText(photol.getAddresse());
        priceeditrl.setText(String.valueOf(photol.getPricee()));
        descriptioneditrl.setText(photol.getDescriptione());

        //set edit text values to not editable
        fnameeditrl.setKeyListener(null);
        lnameeditrl.setKeyListener(null);
        emaileditrl.setKeyListener(null);
        mobilenumeditrl.setKeyListener(null);
        companynameeditrl.setKeyListener(null);
        addresseditrl.setKeyListener(null);
        priceeditrl.setKeyListener(null);
        descriptioneditrl.setKeyListener(null);


        //book button for single photographer
        addbtneditrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //set photographer values to sharedPreference

                SharedPreferences sharedPreferences = getSharedPreferences("customercart",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("name",fnameeditrl.getText().toString() +"" + lnameeditrl.getText().toString() );
                editor.putString("price",priceeditrl.getText().toString());
                editor.putString("email",emaileditrl.getText().toString());
                editor.commit();

                //navigate for user cart
                startActivity(new Intent(context, customercart.class));
            }
        });

    }
}