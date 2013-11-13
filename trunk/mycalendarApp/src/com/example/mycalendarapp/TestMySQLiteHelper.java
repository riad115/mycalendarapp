package com.example.mycalendarapp;

import android.app.Activity;
import android.content.Context;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;
import junit.framework.TestCase;

public class TestMySQLiteHelper extends AndroidTestCase {
	
	private MySQLiteHelper dbTest;
	
//	@Before
	public void setUp() throws Exception{
		super.setUp();
		//Context c = new Activity();
        //RenamingDelegatingContext context = new RenamingDelegatingContext(c, "test_");
        
        
		//dbTest = new MySQLiteHelper(getContext(c,"_test", null, 2)); 
		//dbTest.clearDatebase();
    }
	
	public void testCreateGetCategory(){
		
		int categoryCount = dbTest.getCategoryCount();
		
		Category ctg1 = new Category("Shopping", "WHITE");
        long ctg1_id = dbTest.createCategory(ctg1);
        ctg1.setId(ctg1_id);
        
        //assertEquals(dbTest.getCategoryID(ctg1.getName()),ctg1);
        assertEquals(dbTest.getCategoryID(ctg1.getName()).getId(),ctg1.getId());
        assertEquals(dbTest.getCategoryID(ctg1.getName()).getName(),ctg1.getName());
        assertEquals(dbTest.getCategoryID(ctg1.getName()).getColor(),ctg1.getColor());
        assertEquals(categoryCount+1,dbTest.getCategoryCount());     
        assertEquals(59,ctg1_id);  
       
	}
	
	public void testCreateGetEvent() {
		
		int eventCount = dbTest.getEventCount();
		
		Event testEvent = new Event("Walmart","10/31/2013","10/31/2013","17:30","19:00","Need to bye some tortilla","Repeat-OFF");		
		long[] testCategoryID = {1};
		long testEvent_ID = dbTest.createEvent(testEvent,testCategoryID);
		testEvent.setId(testEvent_ID);
		
		//assertSame(dbTest.getEvent(testEvent_ID),testEvent);
		assertEquals(dbTest.getEvent(testEvent_ID).getId(),testEvent.getId());
		assertEquals(eventCount+1,dbTest.getEventCount());
		assertEquals(83,testEvent_ID);
		
	}		   
    
    public void testGetCategoryByEvent(){
    	
    	 Event event7 = new Event("Proloy","2013-11-08","2013-11-08","22:00","23:45","wanna watch this movie","OFF");	
    	 long event7_id = dbTest.createEvent(event7, new long[] { 59 });
         event7.setId(event7_id);
    	
    	Category test = dbTest.getCategoryByEvent(event7_id);
    	assertEquals("Shopping",test.getName());
    }
    
    public void testGetEventbyID(){
    	
    	 Event event6 = new Event("Elysium","2013-11-08","2013-11-08","22:00","23:45","wanna watch this movie","OFF");	
    	 long event6_id = dbTest.createEvent(event6, new long[] { 1 });
         event6.setId(event6_id);
    	
    	Event test = dbTest.getEvent(event6_id);
    	assertEquals("Elysium",test.getTitle());		
    	
    }
    
    public void testGetEventbyName(){
    	
    	 Event event6 = new Event("Thor","2013-11-08","2013-11-08","22:00","23:45","wanna watch this movie","OFF");	
    	 long event6_id = dbTest.createEvent(event6, new long[] { 1 });
         event6.setId(event6_id);
    	
    	Event test = dbTest.getEvent("Thor");
    	assertEquals(88,test.getId());	// id+2+3	
    	
    }
   
    public void	testUpdateCategory(){
    	
   	    Category ctg2 = new Category("Important", "BLUE");
        long ctg2_id = dbTest.createCategory(ctg2);
        ctg2.setId(ctg2_id);
        
        ctg2.setName("Most Important");
        ctg2.setColor("RED");
        dbTest.updateCategory(ctg2);         
        
        //assertSame(ctg2,dbTest.getCategoryID("Most Important"));    
        assertEquals(ctg2.getId(),dbTest.getCategoryID("Most Important").getId());    
        
   }
    
    public void testUpdateEvent(){
    	
    	Event event2 = new Event("Phone","11/01/2013","11/01/2013","22:00","22:15","I will call my mom","Repeat-OFF");
        long event2_id = dbTest.createEvent(event2, new long[] { 1 });
        event2.setId(event2_id);
        
        event2.setTitle("Call");
        event2.setDescription("I will call my mom & Dad");
        event2.setEndTime("22:30");
        
        dbTest.updateEvent(event2);
        
        //assertSame(event2,dbTest.getEvent("Call"));         
        assertEquals(event2.getId(),dbTest.getEvent("Call").getId());   
    }
    
	public void testDeleteEvent(){
		
		int eventCount = dbTest.getEventCount();	
		dbTest.deleteEvent(1);
		assertEquals(eventCount,dbTest.getEventCount());		
	}
	
	public void testDeleteCategory(){
		
		int categoryCount = dbTest.getCategoryCount();
		
		dbTest.deleteCategoryByID(1);
		assertEquals(categoryCount,dbTest.getCategoryCount());
		
	}
	
	//public void onCreateTest(S)
	
	public void tearDown() throws Exception{
		dbTest.close(); 
        super.tearDown();
    }

}
