package com.example.dreamwedmadd;

import static org.junit.Assert.assertEquals;

import com.example.dreamwedmadd.VehicleAddmin.AddminVehicleList;
import com.example.dreamwedmadd.VehicleAddmin.DeleteVehicle;
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

        private static float totalRate;




        @BeforeClass
        public static void intMain(){

            rating = new Rating();



        }

        @Before
        public void setUp(){

            totalRate=0.0f;


        }

        @Test
        public void calculateRate_isCorrect(){

            totalRate=rating.CalculateRate(5,25);
            assertEquals(5, totalRate,0.00);

        }

        //..........................................



       //............................................

        @After
        public void clear(){

            totalRate=0.0f;


        }

        @AfterClass
         public static void clearAll(){

            rating=null;

        }
}
