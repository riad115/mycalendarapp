package com.example.mycalendarapp;

import android.test.AndroidTestCase;
import junit.framework.TestCase;

public class SimpleCalendarViewTest extends AndroidTestCase {

	public void testfindHoliday(){
		boolean holiday = SimpleCalendarView.findHoliday("2013-11-28");
		assertEquals(true, holiday);
	}
}
