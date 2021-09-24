package com.example.dreamwedmadd.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.dreamwedmadd.MainActivity2;
import com.example.dreamwedmadd.R;
import com.example.dreamwedmadd.database.RatingDBHandler;
import com.example.dreamwedmadd.models.Rating;

public class CustomerRate extends AppCompatActivity {

    RatingBar decoRateBar, photoRateBar, cosRateBar, vehRateBar;
    Button btnSubmit;
    float dr=0;
    float pr=0;
    float cr=0;
    float vr=0;
    Context context;
    RatingDBHandler ratingDBHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_rate);

        decoRateBar = findViewById(R.id.decoRating);
        photoRateBar = findViewById(R.id.photoRating);
        cosRateBar = findViewById(R.id.cosRating);
        vehRateBar = findViewById(R.id.vehicleRating);
        btnSubmit = findViewById(R.id.ratingSubmitBtn);

        context = this;
        ratingDBHandler = new RatingDBHandler(context);

       btnSubmit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               dr= decoRateBar.getRating();
               pr=photoRateBar.getRating();
               cr=cosRateBar.getRating();
               vr=vehRateBar.getRating();

              Rating rating = new Rating(dr,pr,cr,vr);
              ratingDBHandler.addRating(rating);

              startActivity(new Intent(context, MainActivity2.class));
               Toast.makeText(getApplicationContext(),"Thank You!",Toast.LENGTH_LONG).show();

           }
       });


    }
}