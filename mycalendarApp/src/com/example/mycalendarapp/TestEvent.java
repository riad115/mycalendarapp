package com.example.mycalendarapp;

import junit.framework.TestCase;

public class TestEvent extends TestCase {
	
	Event TestEvent = new Event("Event_Title","9-Nov-2013","10-nov-2013","10:00am","11:30am","Event description");

	public void testToString() {
		assertEquals(TestEvent.toString(),"Event [id=0, title=Event_Title, startDate=9-Nov-2013, endDate=10-nov-2013, startTime=10:00am, endTime=11:30am, description=Event description]");
	}

	public void testGetSetId() {
		TestEvent.setId(12345);
		assertEquals(TestEvent.getId(),12345);
	}

	public void testGetSetTitle() {
		TestEvent.setTitle("SW Engg Exam");
		assertEquals(TestEvent.getTitle(),"SW Engg Exam");
	}

	public void testGetSetStartDate() {
		TestEvent.setStartDate("11/3/2013");
		assertEquals(TestEvent.getStartDate(),"11/3/2013");
	}

	public void testGetSetEndDate() {
		TestEvent.setEndDate("15/3/2013");
		assertEquals(TestEvent.getEndDate(),"15/3/2013");
	}

	public void testGetSetStartTime() {
		TestEvent.setStartTime("11:15am");
		assertEquals(TestEvent.getStartTime(),"11:15am");
	}

	public void testGetSetEndTime() {
		TestEvent.setEndTime("12:30pm");
		assertEquals(TestEvent.getEndTime(),"12:30pm");
	}

	public void testGetSetDescription() {
		TestEvent.setDescription("SW Engg final examination");
		assertEquals(TestEvent.getDescription(),"SW Engg final examination");
	}

}
