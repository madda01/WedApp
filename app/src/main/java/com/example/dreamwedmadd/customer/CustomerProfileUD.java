package com.example.dreamwedmadd.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dreamwedmadd.MainActivity2;
import com.example.dreamwedmadd.R;
import com.example.dreamwedmadd.database.DBConnection;

public class CustomerProfileUD extends AppCompatActivity {
    //variable declaration
    Button button;
    TextView etemail;
    EditText etmobile,etname;
    DBConnection db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile_ud);

        //mapping
        button=findViewById(R.id.userprofucbtn);
        etname=findViewById(R.id.userprofname);
        etemail=findViewById(R.id.userprofemail);
        etmobile=findViewById(R.id.userprofphone);

        db= new DBConnection(this);

        //get intents
        Intent i= getIntent();
        etname.setText(i.getStringExtra("Name"));
        etemail.setText(i.getStringExtra("Email"));
        etmobile.setText(i.getStringExtra("Mobile"));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=etname.getText().toString();
                String email=etemail.getText().toString();
                String mobile=etmobile.getText().toString();

                if(name.isEmpty() || mobile.isEmpty()){
                    Toast.makeText(CustomerProfileUD.this,"Fill all fields",Toast.LENGTH_LONG).show();
                    return;
                }

                Boolean check=db.Updateuser(name,email,mobile);

                if(check==true){
                    Toast.makeText(CustomerProfileUD.this,"Updated profile successfully",Toast.LENGTH_LONG).show();

                    Intent i = new Intent(CustomerProfileUD.this, MainActivity2.class);
                    startActivity(i);
                } else{
                    Toast.makeText(CustomerProfileUD.this,"Something went wrong",Toast.LENGTH_LONG).show();

                    Intent i = new Intent(CustomerProfileUD.this, MainActivity2.class);
                    startActivity(i);
                }

            }
        });

    }
}