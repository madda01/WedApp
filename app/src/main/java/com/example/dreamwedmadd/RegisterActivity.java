package com.example.dreamwedmadd;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.dreamwedmadd.customer.TestCustomerMethods;
import com.example.dreamwedmadd.database.DBConnection;
import com.example.dreamwedmadd.models.User;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    Button btnreg;
    EditText etname,etemail,etmobile,etpassword;
    private User newuser;
    private DBConnection dbHandler;

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
        dbHandler= new DBConnection(getApplicationContext());
        newuser= new User();

        //get intents
        Intent receiveintent= getIntent();
        String message1=receiveintent.getStringExtra("Message1");
        Toast.makeText(getApplicationContext(),message1,Toast.LENGTH_LONG).show();

        //event handling for register new user
        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkUserData();
            }
        });

    }
            //registration form input validation
            private void checkUserData() {
                if (etname.getText().toString().isEmpty()|| !TestCustomerMethods.validateName(etname.getText().toString())) {
                    etname.setError("Username should be at least 3 characters");
                }
                else if (etemail.getText().toString().isEmpty()|| !TestCustomerMethods.validateEmail(etemail.getText().toString())) {
                    etemail.setError("Enter a valid email");
                }
                else if(etmobile.getText().toString().isEmpty()||!TestCustomerMethods.validateMobile(etmobile.getText().toString())) {
                    etmobile.setError("Enter a valid phone number");
                }
                else if (etpassword.getText().toString().isEmpty()|| !TestCustomerMethods.validatePassword(etpassword.getText().toString())) {
                    etpassword.setError("Password should be grater than 4 alphanumeric characters");
                }
                else
                    createUserAccount();
            }

            //if validation is successful this method will create an account
            private void createUserAccount() {
                //check of the user already exsists
                if (!dbHandler.checkUser(etemail.getText().toString().trim())) {

                    newuser.setName(etname.getText().toString().trim());
                    newuser.setEmail(etemail.getText().toString().trim());
                    newuser.setMobile(etmobile.getText().toString().trim());
                    newuser.setPassword(etpassword.getText().toString().trim());

                    dbHandler.insertUser(newuser);

                    // Snack Bar to show success message that record saved successfully
                    Toast.makeText(getApplicationContext(),"Successfully created an account",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));

                } else {
                    // Snack Bar to show error message that record already exists
                    Toast.makeText(getApplicationContext(),"You already have an account",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                }
            }

}