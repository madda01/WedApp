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

    //AwesomeValidation awesomeValidation;

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

        //initialize validation style
        //awesomeValidation= new AwesomeValidation(ValidationStyle.BASIC);

        //event handling for register new user
        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /*
                //name validation
                awesomeValidation.addValidation(RegisterActivity.this,R.id.hintfname,
                        RegexTemplate.NOT_EMPTY,R.string.invalid_name);

                //email validation
                awesomeValidation.addValidation(RegisterActivity.this,R.id.hintemail,
                        Patterns.EMAIL_ADDRESS,R.string.invalid_email);

                //mobile validation
                awesomeValidation.addValidation(RegisterActivity.this,R.id.hintmobile,
                        "[0-9]{10}$",R.string.invalid_mobile);

                //password validation
                awesomeValidation.addValidation(RegisterActivity.this,R.id.hintpass,
                        ".{6,}",R.string.invalid_password);*/

                if (etname.getText().toString().isEmpty()) {
                    etname.setError("Username can not be empty.");
                }
                if (etemail.getText().toString().isEmpty()) {
                    etemail.setError("Email field can not be empty.");
                }
                if (etpassword.getText().toString().isEmpty()) {
                    etpassword.setError("Password can not be empty.");
                }

                if (!dbHandler.checkUser(etemail.getText().toString().trim())) {

                    newuser.setName(etname.getText().toString().trim());
                    newuser.setEmail(etemail.getText().toString().trim());
                    newuser.setMobile(etmobile.getText().toString().trim());
                    newuser.setPassword(etpassword.getText().toString().trim());

                    dbHandler.insertUser(newuser);

                    //intent creation: Explicit
                    /*Intent i = new Intent(RegisterActivity.this,MainActivity2.class);
                    i.putExtra("MessageReg","Successfully created an account");
                    startActivity(i);*/

                    // Snack Bar to show success message that record saved successfully
                    Toast.makeText(getApplicationContext(),"Successfully created an account",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));

                } else {
                    // Snack Bar to show error message that record already exists
                    Toast.makeText(getApplicationContext(),"Registration was unsuccessfull..",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}