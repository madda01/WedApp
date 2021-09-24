package com.example.dreamwedmadd.customer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

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

import com.airbnb.lottie.L;
import com.example.dreamwedmadd.R;
import com.example.dreamwedmadd.database.RatingDBHandler;
import com.example.dreamwedmadd.database.photoDbHandler;
import com.example.dreamwedmadd.models.Photographermodel;
import com.example.dreamwedmadd.models.Rating;
import com.example.dreamwedmadd.photographyAdmin.photoAdapterfragment;
import com.example.dreamwedmadd.photographyAdmin.photographerAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class PhotographyFragment extends Fragment {

    Button btnvalue;

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
        View root=inflater.inflate(R.layout.fragment_photography, container, false);

        btnvalue = root.findViewById(R.id.photocusbtn1);

        //.....................Ratings...........................

        context=getContext();
        ratingBar=root.findViewById(R.id.cusPhotoRateBar);
        textView =root.findViewById(R.id.custPhotoRateSum);
        ratingDBHandler = new RatingDBHandler(context);
        rating = new Rating();

        //geting ratings from database
        rateSum= ratingDBHandler.getPhotoRatings();
        rateCount=ratingDBHandler.getRatingCount();

        //totalRating calculation
        totalRate=rating.CalculateRate(rateCount,rateSum);


        //setValues into Views
        ratingBar.setRating(totalRate);
        textView.setText(String.valueOf(totalRate)+"/5");

        //........................................................



        btnvalue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),photoCustomView.class));
            }
        });

        return  root;
    }
}
