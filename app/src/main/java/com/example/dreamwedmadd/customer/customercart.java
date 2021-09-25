package com.example.dreamwedmadd.customer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dreamwedmadd.MainActivity2;
import com.example.dreamwedmadd.R;
import com.example.dreamwedmadd.models.User;
import com.example.dreamwedmadd.photographyAdmin.PhotoUnitTests;

import java.util.List;

public class customercart extends AppCompatActivity {

    //views
    EditText etext, etext2,etext3,etext4,etext5,etext6,etext7,etext8,etext9 ;
    TextView tview1,tview2,tview3,tview4,tview5,tview6,tview7,tview8,tview9;
    Button   btn1,btn2,btn3,btn4,btn5;
    ImageView imagel;
    Context context ;
    PhotoUnitTests photoUnitTests;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customercart);

        context = this;
        photoUnitTests=new PhotoUnitTests();

        //link views
        etext = findViewById(R.id.etcart1);
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
        btn5 = findViewById(R.id.btnlbook);

        imagel = findViewById(R.id.imagecart);


        //sharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("customercart", MODE_PRIVATE);

        //photography
        String pname = sharedPreferences.getString("name", "No Photographer");
        String pprice = sharedPreferences.getString("price", "0.00");
        String pemail = sharedPreferences.getString("email", "No email");

        //deco
        String deconame = sharedPreferences.getString("namedeco", "No Decorator");
        String decoprice = sharedPreferences.getString("pricedeco", "0.00");
        String demail = sharedPreferences.getString("emaildeco", "No email");

        //vehicle
        String vhname = sharedPreferences.getString("vename", "No Vehicle");
        String vhprice = sharedPreferences.getString("veprice", "0.00");
        String vhowner = sharedPreferences.getString("vowner", "No Owner");

        //costume
        String costumename  = sharedPreferences.getString("costumename", "No Costume");
        String costumeprice = sharedPreferences.getString("costumeprice", "0.00");
        String costmeshop = sharedPreferences.getString("costumeshop", "No Shop");


        //String to Double for price
        double prise1 = 0, dprice = 0, vhlprice = 0,ctmprioce=0, totalprice = 0;
        prise1 = Double.parseDouble(pprice);
        dprice = Double.parseDouble(decoprice);
        vhlprice = Double.parseDouble(vhprice);
        ctmprioce = Double.parseDouble(costumeprice);

        //photography
        etext.setText(pname);
        etext2.setText(String.valueOf(prise1));

        //deco
        etext3.setText(deconame);
        etext4.setText(String.valueOf(dprice));


        //vehicle
        etext5.setText(vhname);
        etext6.setText(String.valueOf(vhlprice));

        //costume
        etext7.setText(costumename);
        etext8.setText(String.valueOf(ctmprioce));


        //totalprice

//        totalprice = prise1 + dprice + vhlprice + ctmprioce ;
        totalprice= photoUnitTests.TotalPrice(prise1 , dprice , vhlprice , ctmprioce);
        String toprice = totalprice + "";
        etext9.setText(toprice);

        //set edit text not editable to user
        etext.setKeyListener(null);
        etext2.setKeyListener(null);
        etext3.setKeyListener(null);
        etext4.setKeyListener(null);
        etext5.setKeyListener(null);
        etext6.setKeyListener(null);
        etext7.setKeyListener(null);
        etext8.setKeyListener(null);


        //check total price is 0
        if (toprice.equals("0.0")) {
            tview9.setVisibility(View.INVISIBLE);
            etext9.setVisibility(View.INVISIBLE);


        } else {
            tview9.setVisibility(View.VISIBLE);
            etext9.setVisibility(View.VISIBLE);

        }


        //Home button
        imagel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context,MainActivity2.class));
            }
        });


        //photographer empty check
        if (pname.length() > 0) {

            //set button visible
            etext.setVisibility(View.VISIBLE);
            etext2.setVisibility(View.VISIBLE);
            btn1.setVisibility(View.VISIBLE);
            tview1.setVisibility(View.VISIBLE);
            tview2.setVisibility(View.VISIBLE);

            //check value empty
            if (pname.equals("No Photographer")) {
                btn1.setVisibility(View.INVISIBLE);
            }
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //user remove item from cart
                    SharedPreferences sharedPreferences = getSharedPreferences("customercart", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    //remove
                    editor.remove("name");
                    editor.remove("price");
                    editor.apply();

                    //update total price
                    double prise1 = 0, dprice = 0, totalprice = 0;
                    prise1 = Double.parseDouble(pprice);
                    totalprice = 0 + dprice;
                    String toprice = totalprice + "";

                    //navigation to home
                    startActivity(new Intent(context, MainActivity2.class));

                }
            });


        }


        //deco
        if (deconame.length() > 0) {
            etext3.setVisibility(View.VISIBLE);
            etext4.setVisibility(View.VISIBLE);
            btn2.setVisibility(View.VISIBLE);
            tview3.setVisibility(View.VISIBLE);
            tview4.setVisibility(View.VISIBLE);

            // if user not selected vehicle package
            if (deconame.equals("No Decorator")) {
                btn2.setVisibility(View.INVISIBLE);
            } else {
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        //user remove item from cart
                        SharedPreferences sharedPreferences = getSharedPreferences("customercart", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        //remove
                        editor.remove("namedeco");
                        editor.remove("pricedeco");
                        editor.apply();

                        //navigation to home
                        startActivity(new Intent(context, MainActivity2.class));


                    }
                });
            }
        }

        //vehicle
        if (vhname.length() > 0) {

            etext5.setVisibility(View.VISIBLE);
            etext6.setVisibility(View.VISIBLE);
            btn3.setVisibility(View.VISIBLE);
            tview5.setVisibility(View.VISIBLE);
            tview6.setVisibility(View.VISIBLE);

            //if user not selected vehicle package
            if (vhname.equals("No Vehicle")) {
                btn3.setVisibility(View.INVISIBLE);
            } else {
                btn3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        //user remove item from cart
                        SharedPreferences sharedPreferences = getSharedPreferences("customercart", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        //remove
                        editor.remove("vename");
                        editor.remove("veprice");
                        editor.apply();

                        //navigation to home
                        startActivity(new Intent(context, MainActivity2.class));


                    }
                });


            }

        }


        //costume
        if (costumename.length() > 0) {

            etext7.setVisibility(View.VISIBLE);
            etext8.setVisibility(View.VISIBLE);
            btn4.setVisibility(View.VISIBLE);
            tview7.setVisibility(View.VISIBLE);
            tview8.setVisibility(View.VISIBLE);


            //if user not selected costume package
            if (costumename.equals("No Costume")) {
                btn4.setVisibility(View.INVISIBLE);
            } else {
                btn4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        //user remove item from cart
                        SharedPreferences sharedPreferences = getSharedPreferences("customercart", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        //remove
                        editor.remove("costumename");
                        editor.remove("costumeprice");
                        editor.apply();

                        //navigation to home
                        startActivity(new Intent(context, MainActivity2.class));


                    }
                });


            }

        }

        //if total price is 0 book button invisible
        if (toprice.equals("0.0")) {
            btn5.setVisibility(View.INVISIBLE);

        //send user selected package information to email
        }else{

                //Intent for email activity
                Intent intent = new Intent(Intent.ACTION_SEND);
                final Intent chooser;
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"poornaranasinghe55@gmail.com"});  //email
                intent.putExtra(Intent.EXTRA_SUBJECT, "Package Book"); //email subject
                intent.putExtra
                        (Intent.EXTRA_TEXT,
                                "---Package Information--" +
                                        "\n\nPhotographer Name : "+pname+
                                        "\nPhotographer price :Rs."+pprice+
                                        "\n\nDecorator Name : "+deconame+
                                        "\nDecorator Price :Rs."+decoprice+
                                        "\n\nCostume Name : "+costumename+
                                        "\nCostume Shop : "+costmeshop+
                                        "\n\nVehicle Name : "+vhname+
                                        "\nVehicle Owner : "+vhowner+
                                        "\n\nTotal price :Rs."+toprice);



                intent.setType("message/rfc822");
                chooser = Intent.createChooser(intent, "send booking details to package provider");

                //book button
                btn5.setVisibility(View.VISIBLE);
                btn5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(chooser);
                    }
                });



        }

    }
}