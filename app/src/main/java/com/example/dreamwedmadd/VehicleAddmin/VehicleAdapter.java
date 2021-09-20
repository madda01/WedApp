package com.example.dreamwedmadd.VehicleAddmin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.dreamwedmadd.R;
import com.example.dreamwedmadd.database.VehicleDBHandler;
import com.example.dreamwedmadd.models.Vehicle;

import java.util.List;

public class VehicleAdapter extends ArrayAdapter<Vehicle> {

    private Context context;
    private int resource;
    List<Vehicle> vehicles;

    VehicleAdapter(Context context, int resource, List<Vehicle> vehicles){

        super(context,resource,vehicles);
        this.context=context;
        this.resource=resource;
        this.vehicles=vehicles;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resource,parent,false);

        TextView title = row.findViewById(R.id.singleVehTitle);
        TextView description = row.findViewById(R.id.singleVehDescription);
        ImageView imageView =row.findViewById(R.id.singleVehImageView);

        Vehicle vehicle = vehicles.get(position);
        title.setText(vehicle.getBrand());
        description.setText(vehicle.getDescription());
        imageView.setVisibility(row.VISIBLE);

        return row;
    }
}
