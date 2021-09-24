package com.example.dreamwedmadd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.dreamwedmadd.VehicleAddmin.AddVehicle;
import com.example.dreamwedmadd.VehicleAddmin.AddminVehicleList;
import com.example.dreamwedmadd.VehicleAddmin.DeleteVehicle;
import com.example.dreamwedmadd.VehicleAddmin.ValidateInputs;
import com.example.dreamwedmadd.VehicleAddmin.VehicleAdapter;
import com.example.dreamwedmadd.database.VehicleDBHandler;
import com.example.dreamwedmadd.models.Rating;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class IT20235574 {

        private static Rating rating;
        private static ValidateInputs validateInputs;

        private static float totalRate;
        private static boolean validate;



        @BeforeClass
        public static void intMain(){

            rating = new Rating();
            validateInputs = new ValidateInputs();


        }

        @Before
        public void setUp(){

            totalRate=0.0f;
            validate=false;


        }

        @Test
        public void Validation_isCorrect(){

            validate= validateInputs.ValidateData("","","","","","","","");
            assertTrue(String.valueOf(true),validate);

        }

        @Test
        public void PhoneValidation_isCorrect(){

            validate= validateInputs.ValidatePhone("0767008955");
            assertFalse(String.valueOf(false),validate);

        }

        //assertEquals
         @Test
        public void calculateRate_isCorrect(){

            totalRate=rating.CalculateRate(5,25);
            assertEquals(5, totalRate,0.00);

        }

        //assertFalse
        @Test
        public void PriceValidation_isCorrect(){

            validate= validateInputs.ValidatePrice(25000);
            assertFalse(String.valueOf(false),validate);

        }

        //assertTrue
        @Test
        public void DateValidation_isCorrect(){

            validate= validateInputs.ValidateDate("20212021");
            assertTrue(String.valueOf(true),validate);

        }


        @After
        public void clear(){

            totalRate=0.0f;
            validate=false;


        }

        @AfterClass
         public static void clearAll(){

            rating=null;
            validateInputs=null;

        }
}