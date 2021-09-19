package com.example.dreamwedmadd.DecorationAdmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dreamwedmadd.R;
import com.example.dreamwedmadd.database.DBDecorator;
import com.example.dreamwedmadd.models.Decorator;

public class AddDeco extends AppCompatActivity {

    Button btn;
    EditText et1,et2,et3,et4,et5,et6,et7,et8;
    Context context;
    DBDecorator dbDecorator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_deco);
        btn=findViewById(R.id.btnDecoSub);
        et1=findViewById(R.id.etDecoFname);
        et2=findViewById(R.id.etDecoLname);
        et3=findViewById(R.id.etDecoEmail);
        et4=findViewById(R.id.etDecoMobile);
        et5=findViewById(R.id.etDecoCname);
        et6=findViewById(R.id.etDecoAddress);
        et7=findViewById(R.id.etDecoPRice);
        et8=findViewById(R.id.etDecoDes);
        context =this;
        dbDecorator=new DBDecorator(context);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               String fName=et1.getText().toString();
               String lName=et2.getText().toString();
               String Email=et3.getText().toString();
               String Mobile=et4.getText().toString();
               String cName=et5.getText().toString();
               String address=et6.getText().toString();
               String description=et8.getText().toString();
               double price=Double.parseDouble(et7.getText().toString());
               Decorator decorator=new Decorator(fName,lName,Email,Mobile,cName,description,address,price);
               dbDecorator.addDeco(decorator);
                startActivity(new Intent(context,AdminDecoView.class));

            }
        });






    }
}