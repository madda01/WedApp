package com.example.dreamwedmadd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.dreamwedmadd.database.DBConnection;

public class PasswordConfirmationActivity extends AppCompatActivity {

    Button btnresetpass;
    TextView username;
    EditText pass,repass;
    DBConnection db;

    //AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_confirmation);

        //mapping the button
        btnresetpass=findViewById(R.id.btnreset);
        pass=findViewById(R.id.resetpass1text);
        repass=findViewById(R.id.resetpass2text);
        username=findViewById(R.id.checkedemail);

        db=new DBConnection(this);

        //initialize validation style
        //awesomeValidation= new AwesomeValidation(ValidationStyle.BASIC);

        Intent i= getIntent();
        username.setText(i.getStringExtra("username"));

        //event handling for reset password
        btnresetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=username.getText().toString();
                String password=pass.getText().toString();
                String repassword=repass.getText().toString();

               /*
                //password validation
                awesomeValidation.addValidation(PasswordConfirmationActivity.this, Integer.parseInt(password),
                        ".{6,}",R.string.invalid_password);*/

                if(password.isEmpty() || repassword.isEmpty()){
                    Toast.makeText(PasswordConfirmationActivity.this,"fill add fields",Toast.LENGTH_LONG).show();
                    return;
                }

                /*if(password.equals(repassword)){
                    Boolean checkpassupdate= db.updateUser(user,password);
                    if(checkpassupdate==true) {
                        //intent creation: Explicit
                        Intent i = new Intent(PasswordConfirmationActivity.this, LoginActivity.class);
                        startActivity(i);
                        Toast.makeText(PasswordConfirmationActivity.this,"Password update successfully",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(PasswordConfirmationActivity.this,"Password not updated",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(PasswordConfirmationActivity.this,"Passwords not matching",Toast.LENGTH_LONG).show();
                }*/

                if(!password.contentEquals(repassword)){
                    Toast.makeText(PasswordConfirmationActivity.this,"password doesn't match",Toast.LENGTH_LONG).show();
                    return;
                }
                else{
                    db.updateUser(user,password);
                    Toast.makeText(PasswordConfirmationActivity.this,"Password rest successfully",Toast.LENGTH_LONG).show();

                    Intent i = new Intent(PasswordConfirmationActivity.this, LoginActivity.class);
                    startActivity(i);
                }
            }
        });
    }
}