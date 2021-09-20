package com.example.dreamwedmadd.DecorationAdmin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.dreamwedmadd.R;
import com.example.dreamwedmadd.models.Decorator;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class DecoAdaptorRe extends RecyclerView.Adapter<DecoAdaptorRe.ViewHolder>{


    private Context mcontext;
    private List<Decorator> decorators;

    public DecoAdaptorRe(Context mcontext,List<Decorator> decorators) {

        this.decorators=decorators;
        this.mcontext = mcontext;
    }

    @NonNull

    @Override//convert layout into a view
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //create viewholder
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.decosingleraw,parent,false);


        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull  DecoAdaptorRe.ViewHolder holder, int position) {
        //loading data

        holder.name.setText(decorators.get(position).getlName());
        holder.description.setText(decorators.get(position).getDescription());


//        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(mcontext, "Clicked on ", Toast.LENGTH_SHORT).show();
//            }
//        });

    }
    @Override
    public int getItemCount() {
        return decorators.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView description;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.SingleDecTitle);
            description=itemView.findViewById(R.id.SingleDecdis);

        }


    }

}






