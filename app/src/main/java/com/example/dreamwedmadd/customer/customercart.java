package com.example.dreamwedmadd.customer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dreamwedmadd.MainActivity2;
import com.example.dreamwedmadd.R;

public class customercart extends AppCompatActivity {

    EditText etext, etext2,etext3,etext4,etext5,etext6,etext7,etext8,etext9 ;
    TextView tview1,tview2,tview3,tview4,tview5,tview6,tview7,tview8,tview9;
    Button   btn1,btn2,btn3,btn4;
    Context context ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customercart);

        context = this;

        etext  = findViewById(R.id.etcart1);
        etext2 = findViewById(R.id.etcart2);
        etext3 = findViewById(R.id.etcart3);
        etext4 = findViewById(R.id.etcart4);
        etext5 = findViewById(R.id.etcart5);
        etext6 = findViewById(R.id.etcart6);
        etext7 = findViewById(R.id.etcart7);
        etext8 = findViewById(R.id.etcart8);
        etext9 = findViewById(R.id.etcart9);

        tview1 = findViewById(R.id.tvl1);
        tview2 = findViewById(R.id.tvl12);
        tview3 = findViewById(R.id.tvl3);
        tview4 = findViewById(R.id.tvl4);
        tview5 = findViewById(R.id.tvl5);
        tview6 = findViewById(R.id.tvl6);
        tview7 = findViewById(R.id.tvl17);
        tview8 = findViewById(R.id.tvl8);
        tview9 = findViewById(R.id.texvl);


        btn1 = findViewById(R.id.btnl1);
        btn2 = findViewById(R.id.btnl2);
        btn3 = findViewById(R.id.btnl3);
        btn4 = findViewById(R.id.btnl4);





        SharedPreferences sharedPreferences = getSharedPreferences("customercart",MODE_PRIVATE);

        //photography
        String pname = sharedPreferences.getString("name","No Photographer");
        String pprice = sharedPreferences.getString("price","0.00");

        //deco
        String deconame = sharedPreferences.getString("namedeco","No Decorator");
        String decoprice = sharedPreferences.getString("pricedeco","0.00");




        //vehicle
        String vhname = sharedPreferences.getString("vename","No Vehicle");
        String vhprice = sharedPreferences.getString("veprice","0.00");


        double prise1=0,dprice=0,vhlprice=0,totalprice=0;
        prise1 =Double.parseDouble(pprice);
        dprice = Double.parseDouble(decoprice);
        vhlprice = Double.parseDouble(vhprice);

        //photography
        etext.setText(pname);
        etext2.setText(String.valueOf(prise1));

        //deco
        etext3.setText(deconame);
        etext4.setText(String.valueOf(dprice));


        //vehicale
        etext5.setText(vhname);
        etext6.setText(String.valueOf(vhlprice));

        //totalprice
        totalprice =prise1 + dprice + vhlprice ;
        String toprice = totalprice+"";
        etext9.setText(toprice);




        //check total price is 0
        if(toprice.length()>0){
            tview9.setVisibility(View.VISIBLE);
            etext9.setVisibility(View.VISIBLE);
            //etext9.setText(toprice);
        }
        else{
            tview9.setVisibility(View.INVISIBLE);
            etext9.setVisibility(View.INVISIBLE);
        }


        //photographer empty check
        if(pname.length()>0){

            etext.setVisibility(View.VISIBLE);
            etext2.setVisibility(View.VISIBLE);
            btn1.setVisibility(View.VISIBLE);
            tview1.setVisibility(View.VISIBLE);
            tview2.setVisibility(View.VISIBLE);

            //check value empty
            if(pname.equals("No Photographer")){
               btn1.setVisibility(View.INVISIBLE);
           }
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        SharedPreferences sharedPreferences = getSharedPreferences("customercart", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.remove("name");
                        editor.remove("price");
                        editor.apply();


                        double prise1 = 0, dprice = 0, totalprice = 0;
                        prise1 = Double.parseDouble(pprice);
                        totalprice = 0 + dprice;
                        String toprice = totalprice + "";

                        startActivity(new Intent(context, MainActivity2.class));

                    }
                });




        }


        //deco
        if(deconame.length()>0) {
            etext3.setVisibility(View.VISIBLE);
            etext4.setVisibility(View.VISIBLE);
            btn2.setVisibility(View.VISIBLE);
            tview3.setVisibility(View.VISIBLE);
            tview4.setVisibility(View.VISIBLE);

            if (deconame.equals("No Decorator")) {
                btn2.setVisibility(View.INVISIBLE);
            } else {
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        SharedPreferences sharedPreferences = getSharedPreferences("customercart", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.remove("namedeco");
                        editor.remove("pricedeco");
                        editor.apply();

                        startActivity(new Intent(context, MainActivity2.class));


                    }
                });
            }
        }

        //vehicle
        if(vhname.length()>0){

            etext5.setVisibility(View.VISIBLE);
            etext6.setVisibility(View.VISIBLE);
            btn3.setVisibility(View.VISIBLE);
            tview5.setVisibility(View.VISIBLE);
            tview6.setVisibility(View.VISIBLE);


            if (vhname.equals("No Vehicle")) {
                btn3.setVisibility(View.INVISIBLE);
            } else{
                btn3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        SharedPreferences sharedPreferences = getSharedPreferences("customercart", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.remove("vename");
                        editor.remove("veprice");
                        editor.apply();

                        startActivity(new Intent(context, MainActivity2.class));


                    }
                });


            }

        }



    }
}