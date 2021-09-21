package com.example.dreamwedmadd.costumeAdmin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dreamwedmadd.R;
import com.example.dreamwedmadd.models.Costume;

import java.util.List;

public class CostumeAdaptorRetrieve extends RecyclerView.Adapter<CostumeAdaptorRetrieve.ViewHolder> {
    private Context mcontext;
    private List<Costume> costumes;

    public CostumeAdaptorRetrieve(Context mcontext,List<Costume> costumes) {

        this.costumes=costumes;
        this.mcontext = mcontext;
    }

    @NonNull

    @Override//convert layout into a view
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //create viewholder
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.costumesinglerow,parent,false);


        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull  CostumeAdaptorRetrieve.ViewHolder holder, int position) {
        //loading data

        holder.title.setText(costumes.get(position).getTitle());
        holder.description.setText(costumes.get(position).getDescription());


    }
    @Override
    public int getItemCount() {
        return costumes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView description;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.SingleCosTitle);
            description=itemView.findViewById(R.id.SingleCosDesc);

        }


    }

}
