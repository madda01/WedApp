package com.example.dreamwedmadd;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import android.content.Context;

import com.example.dreamwedmadd.costumeAdmin.CostumeAdd;
import com.example.dreamwedmadd.costumeAdmin.TestCostumeMethods;
import com.example.dreamwedmadd.customer.TestCustomerMethods;
import com.example.dreamwedmadd.database.DBConnection;
import com.example.dreamwedmadd.models.User;

public class IT20232368{

    private TestCostumeMethods costumeAdd;
    private TestCustomerMethods userAdd;
    double price;
    boolean istrue;

    @BeforeClass
    public void createObjects(){
        costumeAdd= new TestCostumeMethods();
        userAdd= new TestCustomerMethods();
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
        assertEquals(21000.0,price,0.00); //after adding the service charge
    }

    @Test
    public void checkEmail(){
        istrue= costumeAdd.validateEmail("supplier@gmail.com"); //email pattern
        assertEquals(String.valueOf(true),istrue);
    }

    @Test
    public void checkUserEmail(){
        istrue= userAdd.validateEmail("user@gmail.com");
        assertEquals(String.valueOf(true),istrue); //check user email
    }

    @Test
    public void isPhoneCorrect(){
        istrue=costumeAdd.validateMobile("0812345678");
        assertTrue(String.valueOf(true),istrue); //validate mobile number
    }

    @Test
    public void isUserPhoneCorrect(){
        istrue=userAdd.validateMobile("0119876543");
        assertTrue(String.valueOf(true),istrue); //validate mobile number
    }

    @Test
    public void isUserNameCorrect(){
        istrue=userAdd.validateName("James Charles");
        assertTrue(String.valueOf(true),istrue); //validate name
    }

    @Test
    public void isUserPasswordCorrect(){
        istrue=userAdd.validatePassword("dreamwed");
        assertTrue(String.valueOf(true),istrue); //validate password
    }

    @After
    public void clearPrice(){
        price=0.0f;
    }

    @AfterClass
    public void clearObjects(){
        costumeAdd=null;
        userAdd=null;
    }

}
