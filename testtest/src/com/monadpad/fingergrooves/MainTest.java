package com.monadpad.fingergrooves;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;

/**
 * This is a simple framework for a test of an Application.  See
 * {@link android.test.ApplicationTestCase ApplicationTestCase} for more information on
 * how to write and extend Application tests.
 * <p/>
 * To run this test, you can type:
 * adb shell am instrument -w \
 * -e class com.monadpad.fingergrooves.MainTest \
 * com.monadpad.fingergrooves.tests/android.test.InstrumentationTestRunner
 */
public class MainTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public MainTest() {
        super("com.monadpad.fingergrooves", MainActivity.class);
    }

    public void testFire(){
        assertEquals(0, 0);
        assertEquals(0, 0);
        assertEquals(1, 0);
    
    }
    
    public void testColor() {
        // Start the main activity of the application under test
        Activity mActivity = getActivity();

        // Assert that the current position is the same as the starting position
        assertNotNull(mActivity);

    }

}
