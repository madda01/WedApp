package com.example.dreamwedmadd.DecorationAdmin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dreamwedmadd.LoginActivity;
import com.example.dreamwedmadd.MainActivity;
import com.example.dreamwedmadd.R;
import com.example.dreamwedmadd.database.DBDecorator;
import com.example.dreamwedmadd.models.Decorator;

import java.util.List;

public class AdminDecoView extends AppCompatActivity {

    //variables

    Button btn,logout;
    TextView textView;
    Context context;
    DecoAdaptor decoAdaptor;
    List<Decorator> decorators;
    DBDecorator dbDecorator;
    ListView listView;
    //onCreate method

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_deco_view);
        //bind variables with views
        btn=findViewById(R.id.btnDecoAdd);
        textView=findViewById(R.id.tvDecCount);
        logout=findViewById(R.id.btnlogoutdeco);
        listView=findViewById(R.id.DecoAdminView);
        context=this;
        //create db connections
        dbDecorator=new DBDecorator(context);
        //get all decorators
        decorators=dbDecorator.getAllDeco();
        //create adaptor object
        decoAdaptor=new DecoAdaptor(context,R.layout.decosingleraw,decorators);
        //set adaptor
        listView.setAdapter(decoAdaptor);
        textView.setText("Decorators "+dbDecorator.DecoCount());

        //getting data
        SharedPreferences sharedPreferences= getSharedPreferences("login",MODE_PRIVATE);
        String adminemail= sharedPreferences.getString("Email","no email");

        //set onclick listener for list view
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Decorator decorator=decorators.get(i);
                String name=decorator.getfName();
                String des=decorator.getDescription();

                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle(name);
                builder.setMessage(des);

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent=new Intent(context,DeleteDeco.class);
                        intent.putExtra("idDel",decorator.getId());
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent=new Intent(context,EditDeco.class);
                        intent.putExtra("id",decorator.getId());
                        startActivity(intent);

                    }
                });

                builder.show();

            }
        });




        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                startActivity(new Intent(context,AddDeco.class));
            }
        });
        //logout button
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedpreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.remove("Email");
                editor.apply();
                startActivity(new Intent(AdminDecoView.this, MainActivity.class));
            }
        });


    }

    @Override
    public void onBackPressed() {
        this.moveTaskToBack(true);
    }
}



