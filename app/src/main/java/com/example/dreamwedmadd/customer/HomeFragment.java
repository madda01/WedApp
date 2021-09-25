package com.example.dreamwedmadd.customer;


import android.content.Intent;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.dreamwedmadd.R;

public class HomeFragment extends Fragment {

    //views
    ImageView deco,photo,cos,veh,usercart;
    ImageButton myProf;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        //link views
        deco = root.findViewById(R.id.imageDec);
        photo = root.findViewById(R.id.imagePho);
        cos = root.findViewById(R.id.imageCos);
        veh = root.findViewById(R.id.imageVeh);
        usercart = root.findViewById(R.id.imagecartview);
        myProf = root.findViewById(R.id.imageBtnMyprof);

        //navigate to decoration fragment
        deco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.myfrag,new DecorationFragment());
                fr.commit();
            }
        });

        //navigate to Photographer fragment
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.myfrag,new PhotographyFragment());
                fr.commit();
            }
        });

        //navigate to costume fragment
        cos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.myfrag,new CostumeFragment());
                fr.commit();
            }
        });

        //navigate to costume fragment
        veh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.myfrag,new VehicleFragment());
                fr.commit();
            }
        });

        //navigate to decoration fragment
        deco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.myfrag,new DecorationFragment());
                fr.commit();
            }
        });

        //navigate to my profile
        myProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), CustomerProfile.class));
            }
        });


        //navigate to user cart
        usercart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),customercart.class));
            }
        });


        return root;
    }
}
