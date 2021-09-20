package com.example.dreamwedmadd.photographyAdmin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dreamwedmadd.R;
import com.example.dreamwedmadd.models.Photographermodel;

import java.util.List;



public class photoAdapterfragment  extends  RecyclerView.Adapter<photoAdapterfragment.myviewholder>{


    List<Photographermodel> phtr;


    public photoAdapterfragment(List<Photographermodel> phtr) {
        this.phtr = phtr;
    }



    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photography_single_row,parent,false) ;
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  photoAdapterfragment.myviewholder holder, int position) {

        holder.txt1.setText(phtr.get(position).getFnamee());
        holder.txt2.setText(phtr.get(position).getComanpnynamee());

    }

    @Override
    public int getItemCount() {
        return phtr.size();
    }

    class myviewholder extends RecyclerView.ViewHolder{

        TextView txt1,txt2;
        ImageView imagel ;

        public myviewholder(@NonNull  View itemView) {
            super(itemView);

            txt1=itemView.findViewById(R.id.tvtitle);
            txt2=itemView.findViewById(R.id.tvdecription);
            imagel= itemView.findViewById(R.id.imageview);
        }
    }


}
