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


import com.example.dreamwedmadd.DecorationAdmin.DecoAdaptor;
import com.example.dreamwedmadd.MainActivity2;
import com.example.dreamwedmadd.R;
import com.example.dreamwedmadd.database.DBDecorator;
import com.example.dreamwedmadd.models.Decorator;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class CostumeFragment extends Fragment {
    Button btnviewcostumes;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root= inflater.inflate(R.layout.fragment_costume, container, false);
        btnviewcostumes=root.findViewById(R.id.btnCostumeView);

        btnviewcostumes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),com.example.dreamwedmadd.customer.CostumeView.class));
            }
        });

        return root;




    }



}
