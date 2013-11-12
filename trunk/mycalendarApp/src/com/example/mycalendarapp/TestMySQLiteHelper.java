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
    
    public void testGetCategoryByEvent(){
    	
    	Category test = dbTest.getCategoryByEvent(1);
    	assertEquals("Shopping",test.getName());
    }
    
    public void testGetEventbyID(){
    	
    	Event test = dbTest.getEvent(1);
    	assertEquals("Walmart",test.getTitle());		
    	
    }
    
    public void testGetEventbyName(){
    	
    	Event test = dbTest.getEvent("Walmart");
    	assertEquals(1,test.getId());		
    	
    }
   
    public void	testUpdateCategory(){
    	
   	    Category ctg2 = new Category("Important", "BLUE");
        long ctg2_id = dbTest.createCategory(ctg2);
        ctg2.setId(ctg2_id);
        
        ctg2.setName("Most Important");
        ctg2.setColor("RED");
        dbTest.updateCategory(ctg2);         
        
        assertEquals(ctg2,dbTest.getCategoryID("Most Important"));         
        
   }
    
    public void testUpdateEvent(){
    	
    	Event event2 = new Event("Phone","11/01/2013","11/01/2013","22:00","22:15","I will call my mom","Repeat-OFF");
        long event2_id = dbTest.createEvent(event2, new long[] { 2 });
        event2.setId(event2_id);
        
        event2.setTitle("Call");
        event2.setDescription("I will call my mom & Dad");
        event2.setEndTime("22:30");
        
        dbTest.updateEvent(event2);
        
        assertEquals(event2,dbTest.getEvent("Call"));         
           
    }
    
	public void testDeleteEvent(){
		
		int eventCount = dbTest.getEventCount();	
		dbTest.deleteEvent(1);
		assertEquals(eventCount+1,dbTest.getEventCount());		
	}
	
	public void testDeleteCategory(){
		
		int categoryCount = dbTest.getCategoryCount();
		
		dbTest.deleteCategoryByID(1);
		assertEquals(categoryCount+1,dbTest.getCategoryCount());
		
	}
	
	//public void onCreateTest(S)
	
	public void tearDown() throws Exception{
		dbTest.close(); 
        super.tearDown();
    }

}
