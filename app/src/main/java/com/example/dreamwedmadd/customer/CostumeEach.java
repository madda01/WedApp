package com.example.dreamwedmadd.customer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dreamwedmadd.MainActivity2;
import com.example.dreamwedmadd.R;
import com.example.dreamwedmadd.database.DBConnection;
import com.example.dreamwedmadd.models.Costume;

public class CostumeEach extends AppCompatActivity {
    //variable declaration
    Button btn;
    Context context;
    EditText price,description,email,title,shop,phone;
    DBConnection db;
    Costume costume;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singlecus_costume);

        //mapping the elements
        textView=findViewById(R.id.CostumeHeadName);
        btn = findViewById(R.id.btnCostumeBook);
        title=findViewById(R.id.viewtitle);
        price = findViewById(R.id.viewprice);
        email=findViewById(R.id.viewsizes);
        shop=findViewById(R.id.viewshop);
        phone=findViewById(R.id.viewmobile);
        description = findViewById(R.id.viewdesc);


        context = this;
        db = new DBConnection(context); //creating db connection
        costume = new Costume();
        Intent intent = getIntent();
        int i = intent.getIntExtra("id", 0);

        costume=db.getSingleCostume(i); //getting single costume

        textView.setText(costume.getTitle());
        title.setText(costume.getTitle());
        price.setText(String.valueOf(costume.getPrice()));
        email.setText(costume.getEmail());
        shop.setText(costume.getShop());
        phone.setText(costume.getPhone());
        description.setText(costume.getDescription());

        //showing costume data to the user
        textView.setKeyListener(null);
        title.setKeyListener(null);
        price.setKeyListener(null);
        email.setKeyListener(null);
        shop.setKeyListener(null);
        phone.setKeyListener(null);
        description.setKeyListener(null);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //taking data to the cart
                SharedPreferences sharedPreferences = getSharedPreferences("customercart",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("costumename",title.getText().toString());
                editor.putString("costumeprice",price.getText().toString());
                editor.putString("costumeshop",shop.getText().toString());
                editor.commit();
                startActivity(new Intent(context, customercart.class));
            }
        });
    }
}
