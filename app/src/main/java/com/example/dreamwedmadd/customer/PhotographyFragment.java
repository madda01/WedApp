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

import com.airbnb.lottie.L;
import com.example.dreamwedmadd.R;
import com.example.dreamwedmadd.database.photoDbHandler;
import com.example.dreamwedmadd.models.Photographermodel;
import com.example.dreamwedmadd.photographyAdmin.photoAdapterfragment;
import com.example.dreamwedmadd.photographyAdmin.photographerAdapter;

import java.util.ArrayList;
import java.util.List;

public class PhotographyFragment extends Fragment {

    Button btnvalue;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_photography, container, false);

        btnvalue = root.findViewById(R.id.photocusbtn1);

        btnvalue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),photoCustomView.class));
            }
        });

        return  root;
    }
}
