package com.example.dreamwedmadd.customer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;


import com.example.dreamwedmadd.DecorationAdmin.DecoAdaptor;
import com.example.dreamwedmadd.DecorationAdmin.DecoAdaptorRe;

import com.example.dreamwedmadd.R;
import com.example.dreamwedmadd.database.DBDecorator;
import com.example.dreamwedmadd.database.RatingDBHandler;
import com.example.dreamwedmadd.models.Decorator;
import com.example.dreamwedmadd.models.Rating;

import java.text.DecimalFormat;
import java.util.List;

public class DecorationFragment extends Fragment {


    Button btn;

    //.......................Rating..............................

    RatingBar ratingBar;
    TextView textView;
    RatingDBHandler ratingDBHandler;
    Context context;
    float rateCount=0;
    float rateSum=0;
    float totalRate;
    DecimalFormat fd;
    Rating rating;

    //..........................................................


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root=inflater.inflate(R.layout.fragment_decoration, container, false);
        btn=root.findViewById(R.id.btnDecocusView);

        //.....................Ratings...........................

        context=getContext();
        ratingBar=root.findViewById(R.id.cusDecoRateBar);
        textView =root.findViewById(R.id.custDecoRateSum);
        ratingDBHandler = new RatingDBHandler(context);
       rating = new Rating();


        //geting ratings from database
        rateSum= ratingDBHandler.getDecoRatings();
        rateCount=ratingDBHandler.getRatingCount();

        //totalRating calculation
        totalRate=rating.CalculateRate(rateCount,rateSum);

        //setValues into Views
        ratingBar.setRating(totalRate);
        textView.setText(String.valueOf(totalRate)+"/5");

        //........................................................

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getContext(), com.example.dreamwedmadd.customer.DecoCusView.class));
            }
        });

        return root;


    }



}
