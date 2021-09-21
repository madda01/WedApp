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


import com.example.dreamwedmadd.DecorationAdmin.DecoAdaptor;
import com.example.dreamwedmadd.DecorationAdmin.DecoAdaptorRe;

import com.example.dreamwedmadd.R;
import com.example.dreamwedmadd.database.DBDecorator;
import com.example.dreamwedmadd.models.Decorator;

import java.util.List;

public class DecorationFragment extends Fragment {


    Button btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root=inflater.inflate(R.layout.fragment_decoration, container, false);
        btn=root.findViewById(R.id.btnDecocusView);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getContext(), com.example.dreamwedmadd.customer.DecoCusView.class));
            }
        });

        return root;


    }



}
