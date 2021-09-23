package com.example.dreamwedmadd;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.dreamwedmadd.database.DBConnection;
import com.example.dreamwedmadd.models.User;

public class RegisterActivity extends AppCompatActivity {

    Button btnreg;
    EditText etname,etemail,etmobile,etpassword;
    private User newuser;
    private DBConnection dbHandler;
    Boolean valid= true;

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
                if (etname.getText().toString().isEmpty()|| etname.getText().toString().length()<3) {
                    etname.setError("Username should be at least 3 characters");
                }
                else if (etemail.getText().toString().isEmpty()|| !android.util.Patterns.EMAIL_ADDRESS.matcher(etemail.getText().toString()).matches()) {
                    etemail.setError("Enter a valid email");
                }
                else if(etmobile.getText().toString().isEmpty()||!isPhoneNumberValid(etmobile.getText().toString())) {
                    etmobile.setError("Enter a valid phone number");
                    valid = false;
                }
                else if (etpassword.getText().toString().isEmpty()|| etpassword.getText().toString().length() < 4 || etpassword.getText().toString().length() > 10) {
                    etpassword.setError("Password should be 4 and 10 alphanumeric characters");
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

            //method to validate the 10 digit phone number
            public boolean isPhoneNumberValid(String phoneNumber) {

                boolean valid = true;
                String regex = "^[0-9]{10}$";

                if (!phoneNumber.matches(regex)) {
                    valid = false;
                }
                return valid;
            }

}