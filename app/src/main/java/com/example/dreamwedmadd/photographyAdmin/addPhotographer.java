package com.example.dreamwedmadd.photographyAdmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dreamwedmadd.R;
import com.example.dreamwedmadd.database.photoDbHandler;
import com.example.dreamwedmadd.models.Photographermodel;

public class addPhotographer extends AppCompatActivity {

    private  EditText fname,lname,email,mobilenum,companyname,address,price,description ;
    private  Button addbtn ;
    private photoDbHandler photoDbhandler ;
    private Context context;

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
                        dcri

                );
                photoDbhandler.addPhotographer(phto);
                startActivity(new Intent(context,photography_Mainlist.class));

            }
        });


    }
}