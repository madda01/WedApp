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

public class EditDeco extends AppCompatActivity {

    Button btn;
    EditText et1,et2,et3,et4,et5,et6,et7,et8;
    Context context;
    DBDecorator dbDecorator;
    Decorator decorator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_deco);

        btn=findViewById(R.id.btnDecoSubUP);
        et1=findViewById(R.id.etDecoFnameUP);
        et2=findViewById(R.id.etDecoLnameUP);
        et3=findViewById(R.id.etDecoEmailUP);
        et4=findViewById(R.id.etDecoMobileUP);
        et5=findViewById(R.id.etDecoCnameUP);
        et6=findViewById(R.id.etDecoAddressUP);
        et7=findViewById(R.id.etDecoPRiceUP);
        et8=findViewById(R.id.etDecoDesUP);

        Intent intent=getIntent();
        int i= intent.getIntExtra("id",0);


        context=this;
        dbDecorator=new DBDecorator(context);

        decorator= dbDecorator.getSingleDeco(i);

        et1.setText(decorator.getfName());
        et2.setText(decorator.getlName());
        et3.setText(decorator.getEmail());
        et4.setText(decorator.getMobile());
        et5.setText(decorator.getcName());
        et6.setText(decorator.getAddress());
        et7.setText(String.valueOf(decorator.getPrice()));
        et8.setText(decorator.getDescription());


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fName ,lName,Email,Mobile,cName,description,address;
                double price;
               fName= et1.getText().toString();
               lName= et2.getText().toString();
               Email=et3.getText().toString();
               Mobile=et4.getText().toString();
               cName=et5.getText().toString();
                address=et6.getText().toString();
                price=Double.parseDouble(et7.getText().toString());
               description=et8.getText().toString();

               decorator=new Decorator(i,fName,lName,Email,Mobile,cName,description,address,price);

                dbDecorator.UpdateDeco(decorator);

                startActivity(new Intent(context,AdminDecoView.class));


            }
        });






    }
}