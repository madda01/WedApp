package com.example.dreamwedmadd.photographyAdmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dreamwedmadd.R;
import com.example.dreamwedmadd.database.photoDbHandler;
import com.example.dreamwedmadd.models.Photographermodel;

public class editPhotographer extends AppCompatActivity {


    private EditText fnameedit,lnameedit,emailedit,mobilenumedit,companynameedit,addressedit,priceedit,descriptionedit ;
    private Button addbtnedit ;
    private photoDbHandler photoDbhandler;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_photographer);

        context=this;
        photoDbhandler = new photoDbHandler(context);
        fnameedit = findViewById(R.id.etfirstnameedit);
        lnameedit = findViewById(R.id.etlastnameedit);
        emailedit = findViewById(R.id.etemailedit);
        mobilenumedit = findViewById(R.id.etphoneedit);
        companynameedit = findViewById(R.id.etcompanynameedit);
        addressedit = findViewById(R.id.etaddressedit);
        priceedit = findViewById(R.id.etpriceedit);
        descriptionedit = findViewById(R.id.etdescriptionedit);

        addbtnedit = findViewById(R.id.btn5edit);

        final String id = getIntent().getStringExtra("id");

       Photographermodel photohan = photoDbhandler.getSinglePhotographer(Integer.parseInt(id));

       fnameedit.setText(photohan.getFnamee());
       lnameedit.setText(photohan.getLnamee());
       emailedit.setText(photohan.getEmaile());
       mobilenumedit.setText(photohan.getPhonee());
       companynameedit.setText(photohan.getComanpnynamee());
       addressedit.setText(photohan.getAddresse());
       priceedit.setText(String.valueOf(photohan.getPricee()));
       descriptionedit.setText(photohan.getDescriptione());

       addbtnedit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String finame = fnameedit.getText().toString();
               String liname = lnameedit.getText().toString();
               String emli = emailedit.getText().toString();
               String mobnum = mobilenumedit.getText().toString();
               String compnaynm = companynameedit.getText().toString();
               String addr = addressedit.getText().toString();
               double prdb = Double.parseDouble(priceedit.getText().toString());
               String decpt = descriptionedit.getText().toString();

               Photographermodel phtodetal = new Photographermodel(Integer.parseInt(id),finame,liname,emli,mobnum,compnaynm
               ,addr,prdb,decpt);

               int state = photoDbhandler.updatePhotographer(phtodetal);
               startActivity(new Intent(context,photography_Mainlist.class));
                System.out.println(state);
               Toast.makeText(context, "Update Completed", Toast.LENGTH_SHORT).show();
           }
       });
    }
}