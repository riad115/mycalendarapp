package com.example.mycalendarapp;

import android.test.AndroidTestCase;
import junit.framework.TestCase;

// TODO: Auto-generated Javadoc
/**
 * The Class WeeklyViewTest.
 */
public class WeeklyViewTest extends AndroidTestCase {

	//WeeklyView wkView ;
	/**
	 * Testcal top.
	 */
	public void testcalTop(){
		
		String[] stTime = {Integer.toString(2),Integer.toString(15)};
		//String[] eTime = {Integer.toString(20),Integer.toString(15)};
		int top = WeeklyView.calTop(stTime);
		assertEquals(270,top );
		
	}
	
	
	/**
	 * Testcal height.
	 */
	public void testcalHeight(){
		String[] stTime = {Integer.toString(2),Integer.toString(15)};
		String[] eTime = {Integer.toString(2),Integer.toString(15)};
		int height = WeeklyView.calHeight(stTime, eTime);
		assertEquals(0, height);
	}
}
