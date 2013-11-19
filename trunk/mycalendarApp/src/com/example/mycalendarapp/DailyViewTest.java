package com.example.mycalendarapp;

import android.test.AndroidTestCase;
import junit.framework.TestCase;

// TODO: Auto-generated Javadoc
/**
 * The Class DailyViewTest.
 */
public class DailyViewTest extends AndroidTestCase {

	
	/**
	 * Testfind color.
	 */
	public void testfindColor(){
		
		String color = DailyView.findColor("Red");
		assertEquals("#FF0000", color);
	}
}
