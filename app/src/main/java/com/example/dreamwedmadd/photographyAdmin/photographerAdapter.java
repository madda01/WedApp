package com.example.dreamwedmadd.photographyAdmin;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

    //views
    private Context context;
    private int resoursee;
    List<Photographermodel> phto;


    //photographer adapter constructor
    public photographerAdapter(Context context, int resoursee, List<Photographermodel> phto){
        super(context,resoursee,phto);
        this.context=context;
        this.resoursee=resoursee; //photographer single row
        this.phto=phto; //photographer model data
    }


    //method to create single photographer row
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //inflater for convert xml to java side
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resoursee,parent,false);

        //link views
        TextView titletv = row.findViewById(R.id.tvtitle);
        TextView titledecrption = row.findViewById(R.id.tvdecription);
        ImageView image = row.findViewById(R.id.DecoSingImg);


        //get position of single view and getting photographer data
        Photographermodel phtolist = phto.get(position);

        byte[] data = phtolist.getImage();
        Bitmap bmp = BitmapFactory.decodeByteArray(data, 0,data.length);
        image.setImageBitmap(bmp);

        titletv.setText(phtolist.getFnamee()+" "+phtolist.getLnamee());
        titledecrption.setText(phtolist.getDescriptione());
        image.setVisibility(row.VISIBLE);

        return row;
    }
}
