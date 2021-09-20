package com.example.dreamwedmadd.customer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dreamwedmadd.DecorationAdmin.DecoAdaptor;
import com.example.dreamwedmadd.DecorationAdmin.DeleteDeco;
import com.example.dreamwedmadd.R;
import com.example.dreamwedmadd.database.DBDecorator;
import com.example.dreamwedmadd.models.Decorator;

import java.util.List;

public class DecoCusView extends AppCompatActivity {

    Context context;
    DecoAdaptor decoAdaptor;
    List<Decorator> decorators;
    DBDecorator dbDecorator;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deco_cus_view);

        listView=findViewById(R.id.DecoCusList);
        context=this;
        dbDecorator=new DBDecorator(context);
        decorators=dbDecorator.getAllDeco();
        decoAdaptor=new DecoAdaptor(context,R.layout.decosingleraw,decorators);
        listView.setAdapter(decoAdaptor);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Decorator decorator=decorators.get(i);
                String name=decorator.getfName();
                String des=decorator.getDescription();

                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle(name);
                builder.setMessage(des);

                builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent=new Intent(context, SngleDecoCus.class);
                        intent.putExtra("idDel",decorator.getId());
                        startActivity(intent);
                    }
                });


                builder.show();

            }
        });



    }
}