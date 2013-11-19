package com.example.mycalendarapp;

import android.test.AndroidTestCase;
import junit.framework.TestCase;

// TODO: Auto-generated Javadoc
/**
 * The Class SimpleCalendarViewTest.
 */
public class SimpleCalendarViewTest extends AndroidTestCase {

	/**
	 * Testfind holiday.
	 */
	public void testfindHoliday(){
		boolean holiday = SimpleCalendarView.findHoliday("2013-11-28");
		assertEquals(true, holiday);
	}
}
