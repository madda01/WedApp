package com.example.dreamwedmadd;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dreamwedmadd.database.DBConnection;
import com.example.dreamwedmadd.models.User;

public class RegisterActivity extends AppCompatActivity {

    Button btnreg;
    EditText etname,etemail,etmobile,etpassword;
    User newuser;
    DBConnection dbHandler;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //mapping the button and edit-texts
        btnreg=findViewById(R.id.btnregform);
        etname=findViewById(R.id.hintfname);
        etemail=findViewById(R.id.hintemail);
        etmobile=findViewById(R.id.hintmobile);
        etpassword=findViewById(R.id.hintpass);

        //create db connection
        context=this;
        dbHandler=new DBConnection(context);

        //get intents
        Intent receiveintent= getIntent();
        String message1=receiveintent.getStringExtra("Message1");
        Toast.makeText(getApplicationContext(),message1,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //event handling for register new user
        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent creation: Explicit
                Intent i = new Intent(RegisterActivity.this,MainActivity2.class);
                i.putExtra("MessageReg","Successfully created an account");
                startActivity(i);

                String name=etname.getText().toString();
                String email=etemail.getText().toString();
                String mobile=etmobile.getText().toString();
                String password=etpassword.getText().toString();

                newuser=new User(name,email,mobile,password);
                dbHandler.insertUser(newuser);
            }
        });
    }
}