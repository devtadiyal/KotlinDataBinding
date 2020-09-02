package com.example.mvvmsampleapp;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.mvvmsampleapp.ui.auth.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class FirstTest {
    @RunWith(AndroidJUnit4.class)
    @LargeTest
    public class EspressoTest1 extends ActivityInstrumentationTestCase2<MainActivity>{

        public EspressoTest1() {
            super(MainActivity.class);
        }

        @Before
        public void setUp() throws Exception {
            super.setUp();
            injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        }

        @Test
        public void test1ChatId() {
            getActivity();
            onView(withText("Dev tadiyal")).check(matches(isDisplayed()));
        }

        @After
        public void tearDown() throws Exception {
            super.tearDown();
        }
    }
}
