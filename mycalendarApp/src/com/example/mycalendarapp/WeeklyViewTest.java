package com.example.mycalendarapp;

import android.test.AndroidTestCase;
import junit.framework.TestCase;

public class WeeklyViewTest extends AndroidTestCase {

	//WeeklyView wkView ;
	public void testcalTop(){
		
		String[] stTime = {Integer.toString(2),Integer.toString(15)};
		//String[] eTime = {Integer.toString(20),Integer.toString(15)};
		int top = WeeklyView.calTop(stTime);
		assertEquals(270,top );
		
	}
	
	
	public void testcalHeight(){
		String[] stTime = {Integer.toString(2),Integer.toString(15)};
		String[] eTime = {Integer.toString(2),Integer.toString(15)};
		int height = WeeklyView.calHeight(stTime, eTime);
		assertEquals(0, height);
	}
}
