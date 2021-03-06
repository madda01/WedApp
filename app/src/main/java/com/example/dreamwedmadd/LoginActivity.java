package com.example.dreamwedmadd;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dreamwedmadd.DecorationAdmin.AdminDecoView;
import com.example.dreamwedmadd.VehicleAddmin.AddminVehicleList;
import com.example.dreamwedmadd.models.User;
import com.example.dreamwedmadd.photographyAdmin.photography_Mainlist;
import com.example.dreamwedmadd.costumeAdmin.CostumeAdminHome;
import com.example.dreamwedmadd.database.DBConnection;

public class LoginActivity extends AppCompatActivity {
    //variable declaration
    Button btnreg, btnlog;
    EditText etusername,etpassword;
    TextView etforget;

    SharedPreferences sharedPreferences;


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

        sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);

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

                //admin logins
                if((etusername.getText().toString().equals("costume@gmail.com"))&&(etpassword.getText().toString().equals("costume"))){
                    Toast.makeText(getApplicationContext(),"Redirecting to Costume admin",Toast.LENGTH_LONG).show();

                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString("Email", etusername.getText().toString());
                    editor.commit();

                    Intent start= new Intent(LoginActivity.this, CostumeAdminHome.class);
                    startActivity(start);
                }
                else if((etusername.getText().toString().equals("decoration@gmail.com"))&&(etpassword.getText().toString().equals("decoration"))){
                    Toast.makeText(getApplicationContext(),"Redirecting to Decoration admin",Toast.LENGTH_LONG).show();

                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString("Email", etusername.getText().toString());
                    editor.commit();

                    Intent start= new Intent(LoginActivity.this, AdminDecoView.class);
                    startActivity(start);
                }
                else if((etusername.getText().toString().equals("vehicle@gmail.com"))&&(etpassword.getText().toString().equals("vehicle"))){
                    Toast.makeText(getApplicationContext(),"Redirecting to Vehicle admin",Toast.LENGTH_LONG).show();

                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString("Email", etusername.getText().toString());
                    editor.commit();

                    Intent start= new Intent(LoginActivity.this, AddminVehicleList.class);
                    startActivity(start);
                }
                else if((etusername.getText().toString().equals("photography@gmail.com"))&&(etpassword.getText().toString().equals("photography"))){
                    Toast.makeText(getApplicationContext(),"Redirecting to Photography admin",Toast.LENGTH_LONG).show();

                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString("Email", etusername.getText().toString());
                    editor.commit();

                    Intent start= new Intent(LoginActivity.this, photography_Mainlist.class);
                    startActivity(start);
                }
                else if (databaseHelper.checkUser(etusername.getText().toString()
                        , etpassword.getText().toString())) { //other users login

                        //save the data to the session
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString("Email", etusername.getText().toString());
                        editor.commit();

                    //intent creation: Explicit
                    Intent i = new Intent(LoginActivity.this, MainActivity2.class);
                    Toast.makeText(getApplicationContext(),"Welcome!",Toast.LENGTH_LONG).show();
                    i.putExtra("Message2", "Directing To Customer Home");
                    i.putExtra("email", etusername.getText().toString().trim());
                    i.putExtra("password", etpassword.getText().toString().trim());
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