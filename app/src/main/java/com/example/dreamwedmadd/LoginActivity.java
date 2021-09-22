package com.example.dreamwedmadd;

import android.content.Intent;
import android.os.Bundle;
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

    Button btnreg, btnlog;
    EditText etusername,etpassword;
    TextView etforget;
    User newuser;
    SessionManagement sessionManagement;

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

                //saving the email for session
                newuser= new User(etusername.getText().toString());
                sessionManagement= new SessionManagement(LoginActivity.this);
                sessionManagement.saveSession(newuser);

                if((etusername.getText().toString().equals("c"))&&(etpassword.getText().toString().equals("c"))){
                    Toast.makeText(getApplicationContext(),"Redirecting to Costume admin",Toast.LENGTH_LONG).show();
                    Intent start= new Intent(LoginActivity.this, CostumeAdminHome.class);

                    startActivity(start);
                } else if((etusername.getText().toString().equals("d"))&&(etpassword.getText().toString().equals("d"))){
                    Toast.makeText(getApplicationContext(),"Redirecting to Decoration admin",Toast.LENGTH_LONG).show();
                    Intent start= new Intent(LoginActivity.this, AdminDecoView.class);

                    startActivity(start);
                } else if((etusername.getText().toString().equals("v"))&&(etpassword.getText().toString().equals("v"))){
                    Toast.makeText(getApplicationContext(),"Redirecting to Vehicle admin",Toast.LENGTH_LONG).show();
                    Intent start= new Intent(LoginActivity.this, AddminVehicleList.class);

                    startActivity(start);
                } else if((etusername.getText().toString().equals("p"))&&(etpassword.getText().toString().equals("p"))){
                    Toast.makeText(getApplicationContext(),"Redirecting to Photography admin",Toast.LENGTH_LONG).show();
                    Intent start= new Intent(LoginActivity.this, photography_Mainlist.class);

                    startActivity(start);
                }
                else if (databaseHelper.checkUser(etusername.getText().toString()
                        , etpassword.getText().toString())) {
                        //intent creation: Explicit
                        Intent i = new Intent(LoginActivity.this, MainActivity2.class);
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