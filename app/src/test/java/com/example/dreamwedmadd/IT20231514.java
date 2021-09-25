package com.example.dreamwedmadd;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.dreamwedmadd.DecorationAdmin.TestDecoMethods;

import org.junit.*;


public class IT20231514 {

    private static TestDecoMethods testDecoMethods;
    private static double lPrice;
    private static boolean istrue;
    private static boolean isValidate;
    private static boolean isNull;
    private static boolean nameValidate;
    private static boolean isPriceValid;

    @BeforeClass
    public static void objCreate(){
        testDecoMethods=new TestDecoMethods();
    }

    @Before
    public  void setUp(){
        lPrice=0.0;
        istrue=false;
        isValidate=false;
        isNull=false;
        nameValidate=false;
        isPriceValid=false;
    }
    @Test
    public void checkEmail(){
        istrue=testDecoMethods.validateEmail("thameera@gmail.com");
        assertTrue(String.valueOf(true),istrue);

    }

    @Test
    public void isCorrectPrice(){
        lPrice=testDecoMethods.getLastPrice(1000,5);
        assertEquals(1050.0,lPrice,0.0001);
    }

    @Test
    public void NameValidate(){
        nameValidate=testDecoMethods.vlidateName("Kamal","Fernando");
        assertTrue(String.valueOf(true),nameValidate);

    }

    @Test
    public void isValidMobile(){
        isValidate=testDecoMethods.validatePhone("0712023151");
        assertTrue(String.valueOf(true),isValidate);

    }

    @Test
    public void NullDetails(){
        isNull=testDecoMethods.nullDetails("sdsd","sdsd","sd","sd","sd","sd","sd","sd","") ;
        assertFalse(String.valueOf(false),isNull);

    }
    @Test
    public void CheckPrice(){
        isPriceValid=testDecoMethods.priceValid(80.0);
        assertFalse(String.valueOf(false),isPriceValid);
    }

    @After
    public  void clear(){
        lPrice=0.0;
        istrue= Boolean.parseBoolean(null);
        isValidate= Boolean.parseBoolean(null);
        isNull= Boolean.parseBoolean(null);
        nameValidate= Boolean.parseBoolean(null);
        isPriceValid= Boolean.parseBoolean(null);
    }
    @AfterClass
    public static void clearAll(){
        testDecoMethods=null;

    }




}

