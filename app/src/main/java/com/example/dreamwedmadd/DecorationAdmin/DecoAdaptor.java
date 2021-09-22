package com.example.dreamwedmadd.DecorationAdmin;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.dreamwedmadd.R;
import com.example.dreamwedmadd.models.Decorator;
import com.google.android.material.textview.MaterialTextView;

import java.io.ByteArrayInputStream;
import java.util.List;

public class DecoAdaptor extends ArrayAdapter<Decorator> {

    Context context;
    int resource;
    List<Decorator> decorators;

    public DecoAdaptor(Context context,int resource,List<Decorator> decorators){
        super(context,resource,decorators);
        this.context=context;
        this.resource=resource;
        this.decorators=decorators;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View row=layoutInflater.inflate(resource,parent,false);


        TextView name=row.findViewById(R.id.SingleDecTitle);
        TextView description=row.findViewById(R.id.SingleDecdis);

        Decorator decorator=decorators.get(position);

        ImageView imageView=row.findViewById(R.id.DecoSingImg);
        byte[] data = decorator.getImage();
        Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
        imageView.setImageBitmap(bmp);

        name.setText(decorator.getfName()+" "+decorator.getlName());
        description.setText(decorator.getDescription());



        return row;
    }
}


