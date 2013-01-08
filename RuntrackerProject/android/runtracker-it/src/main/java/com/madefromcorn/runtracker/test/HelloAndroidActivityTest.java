package com.madefromcorn.runtracker.test;

import android.test.ActivityInstrumentationTestCase2;
import com.madefromcorn.runtracker.MainActivity;

public class HelloAndroidActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public HelloAndroidActivityTest() {
        super(MainActivity.class);
    }

    public void testActivity() {
        MainActivity activity = getActivity();
        assertNotNull(activity);
    }
}

