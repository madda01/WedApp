package com.example.dreamwedmadd.costumeAdmin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
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
import com.example.dreamwedmadd.database.DBConnection;
import com.example.dreamwedmadd.models.Costume;

import java.util.ArrayList;
import java.util.List;


public class CostumeAdminHome extends AppCompatActivity {
    //declaring variables
    private Button add,logout;
    private ListView coslistView;
    private TextView coscount;
    Context context;
    private DBConnection db; //db connection object
    private List<Costume> costumes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_costume_admin_home);
        context = this;

        //mapping elements
        db = new DBConnection(getApplicationContext());
        add = findViewById(R.id.add);
        logout=findViewById(R.id.btnlogout);
        coslistView = findViewById(R.id.costumelist);
        coscount = findViewById(R.id.costumedesc);
        costumes = new ArrayList<>();

        costumes = db.getAllCostumes(); //getting all the costume lists

        //getting data
        SharedPreferences sharedPreferences= getSharedPreferences("login",MODE_PRIVATE);
        String adminemail= sharedPreferences.getString("Email","no email");


        CostumeAdapter adapter = new CostumeAdapter(context,R.layout.activity_costume_each,costumes);

        coslistView.setAdapter(adapter);

        int countcostumes = db.countCostumes(); //displaying the costume count in the list
        coscount.setText("You have "+countcostumes+" costumes");

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //button to add new costumes
                startActivity(new Intent(CostumeAdminHome.this,CostumeAdd.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //button logout
                SharedPreferences sharedpreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.remove("Email");
                editor.apply();
                startActivity(new Intent(CostumeAdminHome.this, MainActivity.class));
            }
        });

        coslistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //alert box to chose delete or update option
                final Costume costume = costumes.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(costume.getTitle());
                builder.setMessage(costume.getDescription());

                builder.setNegativeButton("Delete Costume", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(context,CostumeDelete.class);
                        intent.putExtra("idDel",String.valueOf(costume.getId()));
                        startActivity(intent);
                        //this will redirect to deleteing the costume
                    }
                });
                builder.setNeutralButton("Update Costume", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(context,CostumeUpdate.class);
                        intent.putExtra("id",String.valueOf(costume.getId()));
                        startActivity(intent);
                        //this will redirect to updating the costume
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