package com.example.dreamwedmadd.photographyAdmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dreamwedmadd.R;
import com.example.dreamwedmadd.database.photoDbHandler;
import com.example.dreamwedmadd.models.Photographermodel;

public class deletePhotographer extends AppCompatActivity {

    //Views
    private EditText fnameeditr,lnameeditr,emaileditr,mobilenumeditr,companynameeditr,addresseditr,priceeditr,descriptioneditr ;
    private Button addbtneditr ;
    private photoDbHandler photoDbhandler;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_photographer);


        //link Views
        context=this;
        photoDbhandler = new photoDbHandler(context);
        fnameeditr = findViewById(R.id.etfirstnameeditr);
        lnameeditr = findViewById(R.id.etlastnameeditr);
        emaileditr = findViewById(R.id.etemaileditr);
        mobilenumeditr = findViewById(R.id.etphoneeditr);
        companynameeditr = findViewById(R.id.etcompanynameeditr);
        addresseditr = findViewById(R.id.etaddresseditr);
        priceeditr = findViewById(R.id.etpriceeditr);
        descriptioneditr = findViewById(R.id.etdescriptioneditr);

        addbtneditr = findViewById(R.id.btn5editr);

        //get Photographer ID
        final String id = getIntent().getStringExtra("id");

        //get photographer details and store the details in the photographer model
        Photographermodel photol = photoDbhandler.getSinglePhotographer(Integer.parseInt(id));

        //set stored data in to the views
        fnameeditr.setText(photol.getFnamee());
        lnameeditr.setText(photol.getLnamee());
        emaileditr.setText(photol.getEmaile());
        mobilenumeditr.setText(photol.getPhonee());
        companynameeditr.setText(photol.getComanpnynamee());
        addresseditr.setText(photol.getAddresse());
        priceeditr.setText(String.valueOf(photol.getPricee()));
        descriptioneditr.setText(photol.getDescriptione());

        //user input lock
        fnameeditr.setKeyListener(null);
        lnameeditr.setKeyListener(null);
        emaileditr.setKeyListener(null);
        mobilenumeditr.setKeyListener(null);
        companynameeditr.setKeyListener(null);
        addresseditr.setKeyListener(null);
        priceeditr.setKeyListener(null);
        descriptioneditr.setKeyListener(null);

        //delete button
        addbtneditr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    photoDbhandler.deletePhotographer(photol.getId());
                    startActivity(new Intent(context,photography_Mainlist.class));
            }
        });

    }
}