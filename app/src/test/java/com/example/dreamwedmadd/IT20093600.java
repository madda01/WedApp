package com.example.dreamwedmadd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.dreamwedmadd.DecorationAdmin.TestDecoMethods;
import com.example.dreamwedmadd.photographyAdmin.PhotoUnitTests;

import org.junit.*;

public class IT20093600 {

    private static PhotoUnitTests photoUnitTests;
    private static boolean isTrueMnum;
    private static boolean isNull;
    private static boolean isEmailValid;
    private static boolean isValidPrice;
    private static double totalPrice;

    @BeforeClass
    public static void ObjectCreate(){
        photoUnitTests=new PhotoUnitTests();

    }

    @Before
    public void setUp(){
        isTrueMnum=false;
        isNull=false;
        isEmailValid=false;
        isValidPrice=false;

    }
    //---------------------------------------------------> Unit tests
    @Test
    public void MobileValidate(){
        isTrueMnum=photoUnitTests.MnumberValidate("1212121212");
        assertTrue(String.valueOf(true),isTrueMnum);
    }
    @Test
    public void CheckNull(){
        isNull= photoUnitTests.checkNull("","","","","","","","");
        assertTrue(String.valueOf(true),isNull);

    }
    @Test
    public void CheckEmail(){
        isEmailValid= photoUnitTests.EmailValid("poorna@gmail.com");
        assertTrue(String.valueOf(true),isEmailValid);
    }
    @Test
    public void CheckPrice(){
        isValidPrice= photoUnitTests.priceValid(89.0);
        assertFalse(String.valueOf(false),isValidPrice);
    }@Test
    public void CheckTotal(){
        totalPrice=photoUnitTests.TotalPrice(1000,1000,1000,1000);
        assertEquals(4000,totalPrice,0.001);

    }

    //---------------------------------------------------> Unit tests
    @After
    public void AddNull(){
        isTrueMnum= Boolean.parseBoolean(null);
        isEmailValid= Boolean.parseBoolean(null);
        isNull= Boolean.parseBoolean(null);
        isValidPrice= Boolean.parseBoolean(null);
    }
    @AfterClass
    public static void clear(){

    }

}
