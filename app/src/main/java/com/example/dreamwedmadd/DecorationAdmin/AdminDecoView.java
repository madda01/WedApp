package com.example.dreamwedmadd.DecorationAdmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dreamwedmadd.R;
import com.example.dreamwedmadd.database.DBDecorator;
import com.example.dreamwedmadd.models.Decorator;

import java.util.List;

public class AdminDecoView extends AppCompatActivity {

    Button btn;
    TextView textView;
    Context context;
    DecoAdaptor decoAdaptor;
    List<Decorator> decorators;
    DBDecorator dbDecorator;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_deco_view);
        btn=findViewById(R.id.btnDecoAdd);
        textView=findViewById(R.id.tvDecCount);
        listView=findViewById(R.id.DecoAdminView);
        context=this;
        dbDecorator=new DBDecorator(context);
        decorators=dbDecorator.getAllDeco();
        decoAdaptor=new DecoAdaptor(context,R.layout.decosingleraw,decorators);
        listView.setAdapter(decoAdaptor);




        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                startActivity(new Intent(context,AddDeco.class));
            }
        });

    }
}