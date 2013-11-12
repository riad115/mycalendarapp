package com.example.mycalendarapp;

import android.app.Activity;
import android.content.Context;
import android.test.RenamingDelegatingContext;
import junit.framework.TestCase;

public class TestMySQLiteHelper extends TestCase {
	
	private MySQLiteHelper dbTest;
	
//	@Before
	public void setUp(){
		Context c = new Activity();
        RenamingDelegatingContext context = new RenamingDelegatingContext(c, "test_");
        dbTest = new MySQLiteHelper(context);        
    }
	
	public long testCreateGetCategory(){
		
		int categoryCount = dbTest.getCategoryCount();
		
		Category ctg1 = new Category("Shopping", "WHITE");
        long ctg1_id = dbTest.createCategory(ctg1);
        ctg1.setId(ctg1_id);
        
        assertEquals(dbTest.getCategoryID(ctg1.getName()),ctg1);
        assertEquals(categoryCount+1,dbTest.getCategoryCount());
        
        return ctg1_id;
	}
	
	public void testCreateGetEvent() {
		
		int eventCount = dbTest.getEventCount();
		
		Event testEvent = new Event("Walmart","10/31/2013","10/31/2013","17:30","19:00","Need to bye some tortilla","Repeat-OFF");		
		long[] testCategoryID = {testCreateGetCategory()};
		long testEvent_ID = dbTest.createEvent(testEvent,testCategoryID);
		testEvent.setId(testEvent_ID);
		
		assertEquals(dbTest.getEvent(testEvent_ID),testEvent);		
		assertEquals(eventCount+1,dbTest.getEventCount());
		
	}
	
	public void testDelete(){
		
		int eventCount = dbTest.getEventCount();
		int categoryCount = dbTest.getCategoryCount();
		
		dbTest.deleteEvent(1);
		assertEquals(eventCount+1,dbTest.getEventCount());
		
		
	}
	
	//public void onCreateTest(S)
	
	public void tearDown() throws Exception{
		dbTest.close(); 
        super.tearDown();
    }

}
