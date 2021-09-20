package com.example.dreamwedmadd.customer;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dreamwedmadd.R;

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
