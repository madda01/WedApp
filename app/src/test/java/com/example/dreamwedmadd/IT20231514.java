package com.example.dreamwedmadd;




import static org.junit.Assert.assertEquals;

import com.example.dreamwedmadd.DecorationAdmin.TestDecoMethods;

import org.junit.*;


public class IT20231514 {

    private static TestDecoMethods testDecoMethods;
    private static double lPrice;


    @BeforeClass
    public static void objCreate(){
        testDecoMethods=new TestDecoMethods();
    }

    @Before
    public  void setUp(){
        lPrice=0.0;
    }

    @Test
    public void isCorrectPrice(){
        lPrice=testDecoMethods.getLastPrice(1000,5);
        assertEquals(1050.0,lPrice,0.0001);
    }
    @After
    public  void clear(){
        lPrice=0.0;
    }
    @AfterClass
    public static void clearAll(){
        testDecoMethods=null;
    }




}

