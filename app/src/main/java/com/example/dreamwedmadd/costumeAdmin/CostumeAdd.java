package com.example.dreamwedmadd.costumeAdmin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dreamwedmadd.ForgetPasswordActivity;
import com.example.dreamwedmadd.PasswordConfirmationActivity;
import com.example.dreamwedmadd.R;
import com.example.dreamwedmadd.database.DBConnection;
import com.example.dreamwedmadd.models.Costume;

public class CostumeAdd extends AppCompatActivity {
    private EditText title, price, sizes, shop, mobile, desc;
    private Button add;
    private DBConnection dbHandler;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_costume_add);

        title = findViewById(R.id.addtitle);
        price = findViewById(R.id.addprice);
        sizes = findViewById(R.id.addsizes);
        shop = findViewById(R.id.addshop);
        mobile = findViewById(R.id.addmobile);
        desc = findViewById(R.id.adddesc);
        add = findViewById(R.id.btnaddcostume);

        context =this;
        dbHandler=new DBConnection(context);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Title = title.getText().toString();
                String Price = price.getText().toString();
                String Size = sizes.getText().toString();
                String Shop= shop.getText().toString();
                String Phone= mobile.getText().toString();
                String Decs= desc.getText().toString();
                //image

                if (Title.equals("")||Price.equals("")||Size.equals("")||Shop.equals("")||Phone.equals("")||Decs.equals("")){

                    Toast.makeText(context, "Please enter all details", Toast.LENGTH_SHORT).show();
                }

                else {
                    Costume newcostume = new Costume(Title, Price, Size, Shop, Phone, Decs);
                    Boolean checkcostumeadding = dbHandler.insertCostume(newcostume);

                    if (checkcostumeadding == true) {
                        Intent i = new Intent(CostumeAdd.this, CostumeAdminHome.class);
                        Toast.makeText(CostumeAdd.this, "Costume added", Toast.LENGTH_LONG).show();
                        startActivity(i);
                    } else {
                        Toast.makeText(CostumeAdd.this, "Insertion was not successful", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
