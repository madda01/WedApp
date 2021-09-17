package com.example.dreamwedmadd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dreamwedmadd.database.DBConnection;

public class PasswordConfirmationActivity extends AppCompatActivity {

    Button btnresetpass;
    TextView username;
    EditText repassword,password;
    DBConnection db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_confirmation);

        //mapping the button
        btnresetpass=findViewById(R.id.btnreset);
        username=findViewById(R.id.resetemail);
        password=findViewById(R.id.resetpass1text);
        repassword=findViewById(R.id.resetpass2text);

        db=new DBConnection(this);

        Intent i= getIntent();
        username.setText(i.getStringExtra("username"));

        super.onResume();
        //event handling for login
        btnresetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=username.getText().toString();
                String pass=password.getText().toString();
                String repass=repassword.getText().toString();

                if(pass.isEmpty() || repass.isEmpty()){
                    Toast.makeText(PasswordConfirmationActivity.this,"Fill all fields",Toast.LENGTH_LONG).show();
                    return;
                }
                if(pass.contentEquals(repass)){
                    Boolean checkpassupdate= db.updatePassword(user,pass);

                    if(checkpassupdate==true){
                        //intent creation: Explicit
                        Intent i = new Intent(PasswordConfirmationActivity.this,LoginActivity.class);
                        Toast.makeText(PasswordConfirmationActivity.this,"Password updated",Toast.LENGTH_SHORT).show();
                        startActivity(i);
                    }else{
                        Toast.makeText(PasswordConfirmationActivity.this,"Update not successful",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(PasswordConfirmationActivity.this,"Passwords not matching",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}