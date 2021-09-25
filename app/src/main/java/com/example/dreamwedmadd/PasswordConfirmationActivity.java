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
    //variable declaration
    Button btnresetpass;
    TextView username;
    EditText pass,repass;
    DBConnection db;

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

        Intent i= getIntent();
        username.setText(i.getStringExtra("username"));

        //event handling for reset password
        btnresetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=username.getText().toString();
                String password=pass.getText().toString();
                String repassword=repass.getText().toString();

                if(password.isEmpty() || repassword.isEmpty()){ //filling all fields
                    Toast.makeText(PasswordConfirmationActivity.this,"fill all fields",Toast.LENGTH_LONG).show();
                    return;
                }
                else if(!password.contentEquals(repassword)){ //checking entered passwords
                    Toast.makeText(PasswordConfirmationActivity.this,"password doesn't match",Toast.LENGTH_LONG).show();
                    return;
                }
                else{
                    db.updateUser(user,password); //update password
                    Toast.makeText(PasswordConfirmationActivity.this,"Password rest successfully",Toast.LENGTH_LONG).show();

                    Intent i = new Intent(PasswordConfirmationActivity.this, LoginActivity.class);
                    startActivity(i);
                }
            }
        });
    }
}