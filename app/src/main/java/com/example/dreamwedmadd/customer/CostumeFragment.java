package com.example.dreamwedmadd.customer;


import android.content.Intent;

import android.content.Context;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;

import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;


import com.example.dreamwedmadd.DecorationAdmin.DecoAdaptor;
import com.example.dreamwedmadd.MainActivity2;
import com.example.dreamwedmadd.R;
import com.example.dreamwedmadd.database.DBDecorator;
import com.example.dreamwedmadd.database.RatingDBHandler;
import com.example.dreamwedmadd.models.Decorator;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DecimalFormat;
import java.util.List;

public class CostumeFragment extends Fragment {
    Button btnviewcostumes;

    //.......................Rating..............................

    RatingBar ratingBar;
    TextView textView;
    RatingDBHandler ratingDBHandler;
    Context context;
    float rateCount=0;
    float rateSum=0;
    float totalRate;
    DecimalFormat fd;

    //..........................................................


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root= inflater.inflate(R.layout.fragment_costume, container, false);
        btnviewcostumes=root.findViewById(R.id.btnCostumeView);

        //.....................Ratings...........................

        context=getContext();
        ratingBar=root.findViewById(R.id.cusCosRateBar);
        textView =root.findViewById(R.id.custCosRateSum);
        ratingDBHandler = new RatingDBHandler(context);

        //geting ratings from database
        rateSum= ratingDBHandler.getCosRatings();
        rateCount=ratingDBHandler.getRatingCount();

        //totalRating calculation
        totalRate = (rateSum/(rateCount*5))*5f;

        //float value into 2 decimal points
        fd = new DecimalFormat("#.##");
        float f =Float.valueOf(fd.format(totalRate));

        //setValues into Views
        ratingBar.setRating(totalRate);
        textView.setText(String.valueOf(f)+"/5");

        //........................................................


        btnviewcostumes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),com.example.dreamwedmadd.customer.CostumeView.class));
            }
        });

        return root;

    }

}
