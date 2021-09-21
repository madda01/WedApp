package com.example.dreamwedmadd.costumeAdmin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
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
import com.example.dreamwedmadd.database.DBConnection;
import com.example.dreamwedmadd.models.Costume;

import java.util.ArrayList;
import java.util.List;


public class CostumeAdminHome extends AppCompatActivity {
    private Button add;
    private ListView coslistView;
    private TextView coscount;
    Context context;
    private DBConnection db;
    private List<Costume> costumes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_costume_admin_home);
        context = this;

        db = new DBConnection(getApplicationContext());
        add = findViewById(R.id.add);
        coslistView = findViewById(R.id.costumelist);
        coscount = findViewById(R.id.costumedesc);
        costumes = new ArrayList<>();

        costumes = db.getAllCostumes();

        CostumeAdapter adapter = new CostumeAdapter(context,R.layout.activity_costume_each,costumes);

        coslistView.setAdapter(adapter);

        int countcostumes = db.countCostumes();
        coscount.setText("You have "+countcostumes+" costumes");

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CostumeAdminHome.this,CostumeAdd.class));
            }
        });

        coslistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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
                        //db.deleteCostume(costume.getId());
                        //startActivity(new Intent(context,CostumeAdminHome.class));
                    }
                });
                builder.setNeutralButton("Update Costume", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(context,CostumeUpdate.class);
                        intent.putExtra("id",String.valueOf(costume.getId()));
                        startActivity(intent);
                    }
                });
                builder.show();
            }
        });
    }
}