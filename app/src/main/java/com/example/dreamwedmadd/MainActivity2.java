package com.example.dreamwedmadd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.dreamwedmadd.customer.customercart;
import com.example.dreamwedmadd.database.photoDbHandler;
import com.example.dreamwedmadd.models.Photographermodel;
import com.example.dreamwedmadd.photographyAdmin.photographerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;


import java.util.List;

public class MainActivity2 extends AppCompatActivity {


    Button btn ;
    Context context ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btn = findViewById(R.id.btncart);
        context = this;


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomnavigation);
        NavController navController = Navigation.findNavController(this, R.id.myfrag);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        NavigationUI.setupActionBarWithNavController(this, navController);

        //get intents
        Intent receiveintent = getIntent();
        String message2 = receiveintent.getStringExtra("Message2");
        Toast.makeText(getApplicationContext(), message2, Toast.LENGTH_LONG).show();

        /*Intent recieveintent1= getIntent();
        String messageReg=recieveintent1.getStringExtra("MessageReg");
        Toast.makeText(getApplicationContext(),messageReg,Toast.LENGTH_LONG).show();*/

        Intent recieveintent2 = getIntent();
        String messagerestpass = recieveintent2.getStringExtra("MessageReset");
        Toast.makeText(getApplicationContext(), messagerestpass, Toast.LENGTH_LONG).show();



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context,customercart.class));
            }
        });



    }





}