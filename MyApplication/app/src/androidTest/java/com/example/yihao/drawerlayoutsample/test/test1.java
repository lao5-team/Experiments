package com.example.yihao.drawerlayoutsample.test;

import com.example.yihao.drawerlayoutsample.MainActivity;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;


public class test1 extends ActivityInstrumentationTestCase2<MainActivity> {
  	private Solo solo;
  	
  	public test1() {
		super(MainActivity.class);
  	}

  	public void setUp() throws Exception {
        super.setUp();
		solo = new Solo(getInstrumentation());
		getActivity();
  	}
  
   	@Override
   	public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
  	}
  
	public void testRun() {
        //Wait for activity: 'com.example.yihao.drawerlayoutsample.MainActivity'
		solo.waitForActivity(com.example.yihao.drawerlayoutsample.MainActivity.class, 2000);
        //Sleep for 9832 milliseconds
		solo.sleep(832);
        //Click on (48.0, 108.542984)
		solo.clickOnScreen(48.0F, 108.542984F);
        //Sleep for 8153 milliseconds
		solo.sleep(153);
        //Click on (48.0, 106.0)
		solo.clickOnScreen(48.0F, 106.0F);
	}
}
