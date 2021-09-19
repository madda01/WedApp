package com.example.dreamwedmadd.DecorationAdmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dreamwedmadd.R;

public class AdminDecoView extends AppCompatActivity {

    Button btn;
    TextView textView;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_deco_view);
        btn=findViewById(R.id.btnDecoAdd);
        textView=findViewById(R.id.tvDecCount);
        context=this;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                startActivity(new Intent(context,AddDeco.class));
            }
        });

    }
}