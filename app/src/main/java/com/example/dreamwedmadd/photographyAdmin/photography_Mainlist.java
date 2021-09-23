package com.example.dreamwedmadd.photographyAdmin;

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
import com.example.dreamwedmadd.R;
import com.example.dreamwedmadd.costumeAdmin.CostumeAdminHome;
import com.example.dreamwedmadd.database.photoDbHandler;
import com.example.dreamwedmadd.models.Photographermodel;

import java.util.ArrayList;
import java.util.List;

public class photography_Mainlist extends AppCompatActivity {

    private Button add,logout ;
    private ListView listView;
    private TextView count;
    private Context context;
    private photoDbHandler photoDbhandler;
    private List<Photographermodel> photogra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photography_mainlist);

        add = findViewById(R.id.btn1);
        listView = findViewById(R.id.photolist);
        count = findViewById(R.id.tvtext3);
        logout=findViewById(R.id.btnlogoutphoto);
        context = this;
        photoDbhandler = new photoDbHandler(context);
        photogra = new ArrayList<>();

        //get photographer details
        photogra = photoDbhandler.getAllPhotographers();

        //getting data
        SharedPreferences sharedPreferences= getSharedPreferences("login",MODE_PRIVATE);
        String adminemail= sharedPreferences.getString("Email","no email");

        //set photographers list to view
        photographerAdapter adapter = new photographerAdapter(context,R.layout.photography_single_row,photogra);
        listView.setAdapter(adapter);

        //get photographer count
        int photographercount = photoDbhandler.countPhotographer();

        count.setText(+photographercount+" Photographers are avalable");

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context,addPhotographer.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedpreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.remove("Email");
                editor.apply();
                startActivity(new Intent(photography_Mainlist.this, LoginActivity.class));
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                final Photographermodel phtodetails = photogra.get(i);

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(phtodetails.getFnamee()+" "+phtodetails.getLnamee());
                builder.setMessage(phtodetails.getComanpnynamee());

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent = new Intent(context,deletePhotographer.class);
                        intent.putExtra("id",String.valueOf(phtodetails.getId()));
                        startActivity(intent);
                    }
                });

                builder.setNegativeButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       Intent intent = new Intent(context,editPhotographer.class);
                       intent.putExtra("id",String.valueOf(phtodetails.getId()));
                       startActivity(intent);
                    }
                });
                builder.show();
            }
        });
    }
}