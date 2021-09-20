package com.example.dreamwedmadd.DecorationAdmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dreamwedmadd.R;
import com.example.dreamwedmadd.database.DBDecorator;
import com.example.dreamwedmadd.models.Decorator;

public class DeleteDeco extends AppCompatActivity {

    Button btn;
    EditText et1,et2,et3,et4,et5,et6,et7,et8;
    Context context;
    DBDecorator dbDecorator;
    Decorator decorator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_deco);

        btn=findViewById(R.id.btnDecoSubDE);
        et1=findViewById(R.id.etDecoFnameDE);
        et2=findViewById(R.id.etDecoLnameDE);
        et3=findViewById(R.id.etDecoEmailDE);
        et4=findViewById(R.id.etDecoMobileDE);
        et5=findViewById(R.id.etDecoCnameDE);
        et6=findViewById(R.id.etDecoAddressDE);
        et7=findViewById(R.id.etDecoPRiceDE);
        et8=findViewById(R.id.etDecoDesDE);
        context=this;
        Intent intent=getIntent();
        decorator =new Decorator();
        dbDecorator=new DBDecorator(context);

        int i= intent.getIntExtra("idDel",0);

        decorator= dbDecorator.getSingleDeco(i);

        et1.setText(decorator.getfName());
        et2.setText(decorator.getlName());
        et3.setText(decorator.getEmail());
        et4.setText(decorator.getMobile());
        et5.setText(decorator.getcName());
        et6.setText(decorator.getAddress());
        et7.setText(String.valueOf(decorator.getPrice()));
        et8.setText(decorator.getDescription());

        et1.setKeyListener(null);
        et2.setKeyListener(null);
        et3.setKeyListener(null);
        et4.setKeyListener(null);
        et5.setKeyListener(null);
        et6.setKeyListener(null);
        et7.setKeyListener(null);
        et8.setKeyListener(null);


//        et1.setEnabled(false);
//        et2.setEnabled(false);
//        et3.setEnabled(false);
//        et4.setEnabled(false);
//        et5.setEnabled(false);
//        et6.setEnabled(false);
//        et7.setEnabled(false);
//        et8.setEnabled(false);





        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbDecorator.deleteDeco(decorator.getId());
                startActivity(new Intent(context, AdminDecoView.class));


            }
        });






    }
}