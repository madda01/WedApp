package com.example.dreamwedmadd.customer;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dreamwedmadd.R;
import com.example.dreamwedmadd.costumeAdmin.CostumeAdapter;
import com.example.dreamwedmadd.database.DBConnection;
import com.example.dreamwedmadd.models.Costume;

import java.util.List;

public class CostumeView extends AppCompatActivity {
    Context context;
    CostumeAdapter costumeAdapter;
    List<Costume> costumes;
    DBConnection dbConnection;
    ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_costume_cusview);

        listView=findViewById(R.id.CostumeCusView);
        context=this;
        dbConnection= new DBConnection(context);
        costumes=dbConnection.getAllCostumes();
        costumeAdapter= new CostumeAdapter(context,R.layout.activity_costume_each,costumes);
        listView.setAdapter(costumeAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Costume costume= costumes.get(i);
                String title= costume.getTitle();
                String desc= costume.getDescription();

                AlertDialog.Builder builder= new AlertDialog.Builder(context);
                builder.setTitle(title);
                builder.setMessage(desc);

                builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent= new Intent(context,CostumeEach.class);
                        intent.putExtra("id",costume.getId());
                        startActivity(intent);
                    }
                });
                builder.show();
            }
        });

    }
}
