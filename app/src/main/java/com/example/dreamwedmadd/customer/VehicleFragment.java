package com.example.dreamwedmadd.customer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.dreamwedmadd.R;
import com.example.dreamwedmadd.database.RatingDBHandler;
import com.example.dreamwedmadd.models.Rating;

import java.text.DecimalFormat;

public class VehicleFragment extends Fragment {

    //define variables
    Button btn;

    RatingBar ratingBar;
    TextView textView;
    RatingDBHandler ratingDBHandler;
    Context context;
    float rateCount=0;
    float rateSum=0;
    float totalRate;
    DecimalFormat fd;
    Rating rating;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_vehicle, container, false);

        context=getContext();
        btn=root.findViewById(R.id.cusViewVehicleListBtn);
        ratingBar=root.findViewById(R.id.cusVehRateBar);
        textView =root.findViewById(R.id.custVehRateSum);
        ratingDBHandler = new RatingDBHandler(context);
        rating = new Rating();

        //geting ratings from database
        rateSum= ratingDBHandler.getVehRatings();
        rateCount=ratingDBHandler.getRatingCount();

        //totalRating calculation
        totalRate=rating.CalculateRate(rateCount,rateSum);

        //setValues into Views
        ratingBar.setRating(totalRate);
        textView.setText(String.valueOf(totalRate)+"/5");


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //navigate customer to the Vehicle list
                startActivity(new Intent(getContext(), com.example.dreamwedmadd.customer.CustomerVehicleList.class));
            }
        });

        return root;
    }
}
