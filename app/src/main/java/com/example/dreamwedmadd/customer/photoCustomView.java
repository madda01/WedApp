package com.example.dreamwedmadd.customer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dreamwedmadd.R;
import com.example.dreamwedmadd.database.photoDbHandler;
import com.example.dreamwedmadd.models.Photographermodel;
import com.example.dreamwedmadd.photographyAdmin.deletePhotographer;
import com.example.dreamwedmadd.photographyAdmin.editPhotographer;
import com.example.dreamwedmadd.photographyAdmin.photographerAdapter;

import java.util.ArrayList;
import java.util.List;

public class photoCustomView extends AppCompatActivity {

    //views
    private Button adde;
    private ListView listViewe;
    private Context context;
    private photoDbHandler photoDbhandler;
    private List<Photographermodel> photograe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_custom_view);


        //link views
        listViewe = findViewById(R.id.photocustomlistView);
        context = this;
        photoDbhandler = new photoDbHandler(context);

        //get photographers details form database and store in List array
        photograe = photoDbhandler.getAllPhotographers();


        //set photographers list to view
        photographerAdapter adapter = new photographerAdapter(context,R.layout.photography_single_row,photograe);
        listViewe.setAdapter(adapter);

        //if user click single view
        listViewe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                final Photographermodel phtodetails = photograe.get(i);

                //set alert box if user click item
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(phtodetails.getFnamee()+" "+phtodetails.getLnamee());
                builder.setMessage(phtodetails.getComanpnynamee());

                //navigate button for user click photographer
                builder.setPositiveButton("View Photographer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent = new Intent(context, singlePhotographerCutomerView.class);
                        intent.putExtra("id",String.valueOf(phtodetails.getId()));
                        startActivity(intent);
                    }
                });

                builder.show();
            }
        });

    }
}