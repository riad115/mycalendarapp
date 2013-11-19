package com.example.mycalendarapp;

import junit.framework.TestCase;

// TODO: Auto-generated Javadoc
/**
 * The Class TestEvent.
 */
public class TestEvent extends TestCase {
	

	/** The Test event. */
	Event TestEvent = new Event("Event_Title","9-Nov-2013","10-nov-2013","10:00am","11:30am","Event description","");

	/**
	 * Test to string.
	 */
	public void testToString() {
		assertEquals(TestEvent.toString(),"Event [id=0, title=Event_Title, startDate=9-Nov-2013, endDate=10-nov-2013, startTime=10:00am, endTime=11:30am, description=Event description]");
	}

	//Event TestEvent = new Event("Event_Title","9-Nov-2013","10-nov-2013","10:00am","11:30am","Event description","OFF");
	

	/**
	 * Test get set id.
	 */
	public void testGetSetId() {
		TestEvent.setId(12345);
		assertEquals(TestEvent.getId(),12345);
	}

	/**
	 * Test get set title.
	 */
	public void testGetSetTitle() {
		TestEvent.setTitle("SW Engg Exam");
		assertEquals(TestEvent.getTitle(),"SW Engg Exam");
	}

	/**
	 * Test get set start date.
	 */
	public void testGetSetStartDate() {
		TestEvent.setStartDate("11/3/2013");
		assertEquals(TestEvent.getStartDate(),"11/3/2013");
	}

	/**
	 * Test get set end date.
	 */
	public void testGetSetEndDate() {
		TestEvent.setEndDate("15/3/2013");
		assertEquals(TestEvent.getEndDate(),"15/3/2013");
	}

	/**
	 * Test get set start time.
	 */
	public void testGetSetStartTime() {
		TestEvent.setStartTime("11:15am");
		assertEquals(TestEvent.getStartTime(),"11:15am");
	}

	/**
	 * Test get set end time.
	 */
	public void testGetSetEndTime() {
		TestEvent.setEndTime("12:30pm");
		assertEquals(TestEvent.getEndTime(),"12:30pm");
	}

	/**
	 * Test get set description.
	 */
	public void testGetSetDescription() {
		TestEvent.setDescription("SW Engg final examination");
		assertEquals(TestEvent.getDescription(),"SW Engg final examination");
	}

}
