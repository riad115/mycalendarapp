package com.example.mycalendarapp;

import android.test.AndroidTestCase;
import junit.framework.TestCase;

public class DailyViewTest extends AndroidTestCase {

	
	public void testfindColor(){
		
		String color = DailyView.findColor("Red");
		assertEquals("#FF0000", color);
	}
}
