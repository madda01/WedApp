package com.example.dreamwedmadd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dreamwedmadd.database.DBConnection;

public class LoginActivity extends AppCompatActivity {

    Button btnreg, btnlog;
    EditText etusername,etpassword;
    TextView etforget;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //mapping the button
        btnreg=findViewById(R.id.btnreg);
        btnlog=findViewById(R.id.btnlog);
        etusername=findViewById(R.id.etenteremail);
        etpassword=findViewById(R.id.etenterpassword);
        etforget=findViewById(R.id.forgetpasswordlink);

        DBConnection databaseHelper =new DBConnection(getApplicationContext());

        //event handling for going to home
        btnlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etusername.getText().toString().isEmpty()) {
                    etusername.setError("Username can not be empty.");
                }
                if (etpassword.getText().toString().isEmpty()) {
                    etpassword.setError("Password field can not be empty.");
                }
                if (databaseHelper.checkUser(etusername.getText().toString()
                        , etpassword.getText().toString())) {
                    //intent creation: Explicit
                    Intent i= new Intent(LoginActivity.this,MainActivity2.class);
                    i.putExtra("Message2","Directing To Home");
                    i.putExtra("email",etusername.getText().toString().trim());
                    i.putExtra("password",etpassword.getText().toString().trim());
                    startActivity(i);

                } else {
                    // toast to show success message that record is wrong
                    Toast.makeText(getApplicationContext(),"Username and password do not match.",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //event handling for register
        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent creation: Explicit
                Intent i= new Intent(LoginActivity.this,RegisterActivity.class);
                i.putExtra("Message1","New User");
                startActivity(i);
            }
        });

        //event handling for forget password
        etforget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent creation: Explicit
                Intent i= new Intent(LoginActivity.this,ForgetPasswordActivity.class);
                i.putExtra("Message3","Reset Password");
                startActivity(i);
            }
        });
    }
}