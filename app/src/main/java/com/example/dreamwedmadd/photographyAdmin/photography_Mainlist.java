package com.example.dreamwedmadd.photographyAdmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dreamwedmadd.R;

public class photography_Mainlist extends AppCompatActivity {

    private Button add ;
    private ListView listView;
    private TextView count;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photography_mainlist);

        add = findViewById(R.id.btn1);
        listView = findViewById(R.id.photolist);
        count = findViewById(R.id.tvtext3);
        context = this;

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context,addPhotographer.class));
            }
        });
    }
}