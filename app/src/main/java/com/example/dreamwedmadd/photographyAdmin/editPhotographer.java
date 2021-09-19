package com.example.dreamwedmadd.photographyAdmin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.dreamwedmadd.R;

public class editPhotographer extends AppCompatActivity {


    private EditText fnameedit,lnameedit,emailedit,mobilenumedit,companynameedit,addressedit,priceedit,descriptionedit ;
    private Button addbtnedit ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_photographer);

        fnameedit = findViewById(R.id.etfirstnameedit);
        lnameedit = findViewById(R.id.etlastnameedit);
        emailedit = findViewById(R.id.etemailedit);
        mobilenumedit = findViewById(R.id.etphoneedit);
        companynameedit = findViewById(R.id.etcompanynameedit);
        addressedit = findViewById(R.id.etaddressedit);
        priceedit = findViewById(R.id.etpriceedit);
        descriptionedit = findViewById(R.id.etdescriptionedit);

        addbtnedit = findViewById(R.id.btn5edit);



    }
}