package com.example.dreamwedmadd.costumeAdmin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dreamwedmadd.R;
import com.example.dreamwedmadd.database.DBConnection;
import com.example.dreamwedmadd.models.Costume;

public class CostumeUpdate extends AppCompatActivity {
    private EditText price,description,email,title,shop,phone;
    private Button btnedit;
    private DBConnection dbHandler;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_costume_update);

        context = this;
        dbHandler = new DBConnection(context);

        price = findViewById(R.id.addprice);
        description = findViewById(R.id.adddesc);
        email=findViewById(R.id.addsizes);
        title=findViewById(R.id.addtitle);
        shop=findViewById(R.id.addshop);
        phone=findViewById(R.id.addmobile);
        btnedit = findViewById(R.id.btnupdate);

        final String id = getIntent().getStringExtra("id");
        Costume costume = dbHandler.getSingleCostume(Integer.parseInt(id));

        price.setText(String.valueOf(costume.getPrice()));
        description.setText(costume.getDescription());
        email.setText(costume.getEmail());
        title.setText(costume.getTitle());
        shop.setText(costume.getShop());
        phone.setText(costume.getPhone());

        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double titleprice =Double.parseDouble(price.getText().toString()) ;
                String decText = description.getText().toString();
                String Size = email.getText().toString();
                String Title= title.getText().toString();
                String Shop= shop.getText().toString();
                String Phone=phone.getText().toString();

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (Title.equals("")||Size.equals("")||Shop.equals("")||Phone.equals("")||decText.equals("")){

                    Toast.makeText(context, "Please enter all details", Toast.LENGTH_SHORT).show();
                }
                else if(!Size.trim().matches(emailPattern)) {
                    Toast.makeText(getApplicationContext(),"invalid email address",Toast.LENGTH_SHORT).show();
                }

                else{
                    Costume costume1 = new Costume(Integer.parseInt(id),Title,titleprice,Size,Shop,Phone,decText);
                    int state = dbHandler.updateSingleCostume(costume1);
                    System.out.println(state);
                    startActivity(new Intent(context, CostumeAdminHome.class));
                }
            }
        });
    }
}
