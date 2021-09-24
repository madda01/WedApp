package com.example.dreamwedmadd;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import android.content.Context;

import com.example.dreamwedmadd.costumeAdmin.CostumeAdd;
import com.example.dreamwedmadd.database.DBConnection;
import com.example.dreamwedmadd.models.User;

public class IT20232368{

    private User user;
    private DBConnection db;
    private CostumeAdd costumeAdd;
    private LoginActivity loginActivity;
    double price;
    Context context;

    //db= new DBConnection(context);

    @BeforeClass
    public void createObjects(){
        costumeAdd= new CostumeAdd();
    }

    @Before
    public void setPrice(){
        price=0.0f;
    }

    @Test
    public void checkNewPrice(){
        //arrange
        price=costumeAdd.getNewPrice(20000.0,5.0);
        //act
        assertEquals(21000.0,price,0.00);
    }

    @After
    public void clearPrice(){
        price=0.0f;
    }

    @Test
    public void registerNewUser(){
        //arrange
        user= new User("Madhumini","madumini@gmail.com","0767780098","madda");
        //act
        boolean check = db.insertUser(user);
        //assert

    }

    @AfterClass
    public void clearObjects(){
        costumeAdd=null;
    }

}
