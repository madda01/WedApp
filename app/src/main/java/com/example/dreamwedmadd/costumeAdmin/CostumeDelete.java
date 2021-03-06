package com.example.dreamwedmadd.costumeAdmin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dreamwedmadd.R;
import com.example.dreamwedmadd.database.DBConnection;
import com.example.dreamwedmadd.models.Costume;

public class CostumeDelete extends AppCompatActivity {
    //variable declaration
    private EditText price,description,email,title,shop,phone;
    private Button btndel;
    private DBConnection dbHandler;
    private Context context;
    Costume costume;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_costume_delete);

        //mapping the elements
        title=findViewById(R.id.deletetitle);
        price = findViewById(R.id.deleteprice);
        email=findViewById(R.id.deletesizes);
        shop=findViewById(R.id.deleteshop);
        phone=findViewById(R.id.deletemobile);
        description = findViewById(R.id.deletedesc);
        btndel = findViewById(R.id.btndelte);

        context=this;
       // Intent intent=getIntent();
        costume =new Costume();
        dbHandler=new DBConnection(context);

        //int i = intent.getIntExtra("idDel",0);

        final String id= getIntent().getStringExtra("idDel");

        //getting costumes to relevant costume id
        costume=dbHandler.getSingleCostume(Integer.parseInt(id));

        title.setText(costume.getTitle());
        price.setText(String.valueOf(costume.getPrice()));
        email.setText(costume.getEmail());
        shop.setText(costume.getShop());
        phone.setText(costume.getPhone());
        description.setText(costume.getDescription());

        //disable editing while deleting
        title.setKeyListener(null);
        price.setKeyListener(null);
        email.setKeyListener(null);
        shop.setKeyListener(null);
        phone.setKeyListener(null);
        description.setKeyListener(null);

        btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //button delete
                dbHandler.deleteCostume(costume.getId());
                startActivity(new Intent(context, CostumeAdminHome.class));

            }
        });
    }
}
