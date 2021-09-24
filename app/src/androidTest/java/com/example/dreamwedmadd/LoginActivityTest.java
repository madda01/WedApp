package com.example.dreamwedmadd;

import static org.junit.Assert.*;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> mactivity= new ActivityTestRule<LoginActivity>(LoginActivity.class);

    private LoginActivity loginActivity= null;

    @Before
    public void setUp() throws Exception {
        loginActivity=mactivity.getActivity();
    }

    @Test
    public void testLaunch(){
        View view1 = loginActivity.findViewById(R.id.etenteremail);
        View view2 = loginActivity.findViewById(R.id.etenterpassword);
        assertNotNull(view1);
        assertNotNull(view2);
    }

    @After
    public void tearDown() throws Exception {
        loginActivity=null;
    }
}