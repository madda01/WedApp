package com.example.dreamwedmadd.customer;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dreamwedmadd.R;

public class VehicleFragment extends Fragment {

    //define variables
    Button btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_vehicle, container, false);

        btn=root.findViewById(R.id.cusViewVehicleListBtn);

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
