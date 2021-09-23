package com.example.dreamwedmadd.customer;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageButton;
import android.widget.ImageView;

import android.widget.Button;


import com.example.dreamwedmadd.MainActivity;
import com.example.dreamwedmadd.R;

public class HomeFragment extends Fragment {


    ImageView deco,photo,cos,veh,usercart;
    ImageButton myProf;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        deco = root.findViewById(R.id.imageDec);
        photo = root.findViewById(R.id.imagePho);
        cos = root.findViewById(R.id.imageCos);
        veh = root.findViewById(R.id.imageVeh);
        usercart = root.findViewById(R.id.imagecartview);
        myProf = root.findViewById(R.id.imageBtnMyprof);

        deco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.myfrag,new DecorationFragment());
                fr.commit();
            }
        });

        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.myfrag,new PhotographyFragment());
                fr.commit();
            }
        });

        cos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.myfrag,new CostumeFragment());
                fr.commit();
            }
        });

        veh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.myfrag,new VehicleFragment());
                fr.commit();
            }
        });

        deco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.myfrag,new DecorationFragment());
                fr.commit();
            }
        });

        myProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), CustomerProfile.class));
            }
        });


        usercart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),customercart.class));
            }
        });

        return root;
    }
}
