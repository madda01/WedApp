package com.example.dreamwedmadd.photographyAdmin;

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
import com.example.dreamwedmadd.models.Photographermodel;

import java.util.List;

public class photographerAdapter extends ArrayAdapter<Photographermodel> {

    private Context context;
    private int resoursee;
    List<Photographermodel> phto;


    photographerAdapter(Context context,int resoursee, List<Photographermodel> phto){
        super(context,resoursee,phto);
        this.context=context;
        this.resoursee=resoursee;
        this.phto=phto;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resoursee,parent,false);

        TextView titletv = row.findViewById(R.id.tvtitle);
        TextView titledecrption = row.findViewById(R.id.tvdecription);
        ImageView image = row.findViewById(R.id.imageview);

        Photographermodel phtolist = phto.get(position);
        titletv.setText(phtolist.getFnamee()+" "+phtolist.getLnamee());
        titledecrption.setText(phtolist.getDescriptione());
        image.setVisibility(row.VISIBLE);

        return row;
    }
}
