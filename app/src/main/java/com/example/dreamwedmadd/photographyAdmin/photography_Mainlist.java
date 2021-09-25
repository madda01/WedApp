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
import com.example.dreamwedmadd.MainActivity;
import com.example.dreamwedmadd.R;
import com.example.dreamwedmadd.costumeAdmin.CostumeAdminHome;
import com.example.dreamwedmadd.database.photoDbHandler;
import com.example.dreamwedmadd.models.Photographermodel;

import java.util.ArrayList;
import java.util.List;

public class photography_Mainlist extends AppCompatActivity {

    //views
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

        //link views
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

        //get photographer count from Data base
        count.setText(+photographercount+" Photographers are avalable");

        //button for add photographer
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context,addPhotographer.class));
            }
        });

        //user logout button
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedpreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.remove("Email");
                editor.apply();
                startActivity(new Intent(photography_Mainlist.this, MainActivity.class));
            }
        });

        //button for if user click photographer
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //get user clicked photographer position
                final Photographermodel phtodetails = photogra.get(i);

                //alert box for update and delete photographer
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(phtodetails.getFnamee()+" "+phtodetails.getLnamee());
                builder.setMessage(phtodetails.getComanpnynamee());

                //photographer delete button
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        //intent for user navigate to the photographer delete page
                        Intent intent = new Intent(context,deletePhotographer.class);
                        intent.putExtra("id",String.valueOf(phtodetails.getId()));
                        startActivity(intent);
                    }
                });

                //photographer update button
                builder.setNegativeButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        //intent for user navigate to the photographer update page
                       Intent intent = new Intent(context,editPhotographer.class);
                       intent.putExtra("id",String.valueOf(phtodetails.getId()));
                       startActivity(intent);
                    }
                });
                builder.show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        this.moveTaskToBack(true);
    }

}