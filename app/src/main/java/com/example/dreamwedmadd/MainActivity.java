package com.example.dreamwedmadd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {
    Button btnenter;
    ImageView logo,splashImg;
    TextView header;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mapping the button
        btnenter=findViewById(R.id.btnapp);
        logo=findViewById(R.id.logo);
        splashImg=findViewById(R.id.backimg);
        header=findViewById(R.id.idapp);
        lottieAnimationView=findViewById(R.id.lottie);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //event handling
        btnenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent creation: Explicit
                Intent i= new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
    }
    @Override
    public void onBackPressed() {
        finishAffinity();
        finish();
    }
}