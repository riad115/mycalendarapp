package com.example.mycalendarapp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class EventActivity extends Activity implements OnClickListener{
	private static final String tag = "EventActivity";
	public static String to_Date;
	public static String from_Date;
	public static String to_Time;
	public static String from_Time;
	
	private String date;
	public static Button toTodaysDate;
	public static Button fromTodaysDate;
	
	public static Button toCurrentTime;
	public static Button fromCurrentTime;
	
	private Button saveButton;
	private Button cancelButton;
	
	private Calendar _calendar;
	private final DateFormat dateFormatter = new DateFormat();
	private static final String dateTemplate = "dd MMMM yyyy";
	
	///////////////////////////////////////////////////////////RossY Start
	
	 private MySQLiteHelper db;
	 
	////////////////////////////////////////////////////////// RossY End
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        
		_calendar = Calendar.getInstance(Locale.getDefault());
		
        TextView textview = new TextView(this);
        textview.setText("This is Event tab");
        setContentView(R.layout.calendar_event);
        
        //////////////////////////////////////////////////////////////////////////////////// RossY Start
        

        db = new  MySQLiteHelper(getApplicationContext());
           
        //  CRUD Operations
        // Creating categories

      
        Category ctg1 = new Category("Shopping", "WHITE");
        Category ctg2 = new Category("Important", "RED");
        Category ctg3 = new Category("Watchlist", "LTGRAY");
        
        // Inserting categories in db
        
        long ctg1_id = db.createCategory(ctg1);
        ctg1.setId(ctg1_id);
        long ctg2_id = db.createCategory(ctg2);
        ctg2.setId(ctg2_id);
        long ctg3_id = db.createCategory(ctg3);
        ctg3.setId(ctg3_id);
       
        Log.d("Category Count", "Category Count: " + db.getAllCategories().size());
        
     // Getting all category names
        Log.d("Categories", "Getting All categories");
 
        List<Category> allCategory = db.getAllCategories();
        for (Category category : allCategory) {
            Log.d("Category Name:"+category.getName(), "ID:"+category.getId()+"Color:"+category.getColor());
        }
       
        // Creating events
        Event event1 = new Event("Walmart","10/31/2013","10/31/2013","17:30","19:00","Need to bye some tortilla");
        Event event2 = new Event("RIM","11/02/2013","11/02/2013","17:00","19:00","Need to bye some Cosmetics");
        
        Event event3 = new Event("Phone","11/01/2013","11/01/2013","22:00","22:15","I will call my mom");
        Event event4 = new Event("Money","11/01/2013","11/01/2013","20:00","22:05","send money to fahad vai");
        Event event5 = new Event("Time Warner","11/01/2013","11/01/2013","22:05","22:10","Have to change internet line");
        
        Event event6 = new Event("Elysium","11/08/2013","11/08/2013","22:00","23:45","wanna watch this movie");
        Event event7 = new Event("HomeLand","11/09/2013","11/09/2013","19:00","22:00","can't wait to see this series!!!");
          
        // Inserting events in db
        // Inserting events under "Shopping" category
       
        long event1_id = db.createEvent(event1, new long[] { ctg1_id });
        event1.setId(event1_id);
        long event2_id = db.createEvent(event2, new long[] { ctg1_id });
        event2.setId(event2_id);    
     // Inserting events under "Important" category
        
        long event3_id = db.createEvent(event3, new long[] { ctg2_id });
        event3.setId(event3_id);
        long event4_id = db.createEvent(event4, new long[] { ctg2_id });
        event4.setId(event4_id);
        long event5_id = db.createEvent(event5, new long[] { ctg2_id });
        event5.setId(event5_id);
        
     // Inserting events under "Watchlist" category
        
        long event6_id = db.createEvent(event6, new long[] { ctg3_id });
        event6.setId(event6_id);
        long event7_id = db.createEvent(event7, new long[] { ctg3_id });
        event7.setId(event7_id);
 
        Log.e("Event Count", "Event count: " + db.getEventCount());
       
        db.createEventCategory(event7_id, ctg2_id);
        
         
     // Getting all Events
        Log.d("Get Events", "Getting All Events");
 
        List<Event> allEvents = db.getAllEvents();
        for (Event event : allEvents) {
            Log.d("Event:"+event.getTitle(),"ID:"+ event.getId()+"Description:"+event.getDescription());
        }
        
     // Getting events under "Watchlist" tag name
        Log.d("Event", "Get events under single category name");
 
        List<Event> categoryWatchlist = db.getAllEventsByCategory(ctg3.getName());
        for (Event event : categoryWatchlist) {
            Log.d("Events Watchlist", "title"+event.getTitle()+"ID:"+ event.getId()+"Description:"+event.getDescription());
        }
        
     // Deleting an Event
        Log.d("Delete Event6", "Deleting an Event");
        Log.d("Event Count", "Event Count Before Deleting: " + db.getEventCount());
 
        db.deleteEvent(event6_id);
 
        Log.d("Event Count", "Event Count After Deleting: " + db.getEventCount());
        
     // Deleting all Events under "Shopping" tag
        Log.d("Event Count", "Event Count Before Deleting Shoping Category: " + db.getEventCount());
 
        db.deleteCategory(ctg1, true);
 
        Log.d("Event Count", "Event Count after Deleting Shoping Category: " + db.getEventCount());
           
       
        Category ctg4 = new Category("Travel", "GREEN");
        long ctg4_id = db.createCategory(ctg4);
        ctg4.setId(ctg4_id);
        
     // Getting all category names
        Log.d("Get Categories before update", "Getting All categories before update");
 
        List<Category> allCategoryNew = db.getAllCategories();
        for (Category category : allCategoryNew) {
        	Log.d("Category Name:"+category.getName(), "ID:"+category.getId()+"Color:"+category.getColor());
      }
        
     // Updating category name
        ctg4.setName("Tour");
        db.updateCategory(ctg4);
        
     // Getting all category names
        Log.d(" after update", "Getting All categories after update");
 
        List<Category> allCategoryNew1 = db.getAllCategories();
        for (Category category : allCategoryNew1) {
        	Log.d("Category Name:"+category.getName(), "ID:"+category.getId()+"Color:"+category.getColor());
      }
      
        Event event8 = new Event("ROSS","11/02/2013","11/05/2013","17:00","19:00","Dhumaia beramu!!");
        long event8_id = db.createEvent(event8, new long[] { ctg4_id });
        event8.setId(event8_id);
      
        // Getting all Events
        Log.d("Get Events before", "Getting All Events before ");
 
        List<Event> allEvents2 = db.getAllEvents();
        for (Event event : allEvents2) {
        	 Log.d("Event:"+event.getTitle(),"ID:"+ event.getId()+"Description:"+event.getDescription());
        }  
        
        event8.setTitle("Corpus Cristi");
        db.updateEvent(event8);

     // Getting all Events
        Log.d("Events after", "Getting All Events after update ");
 
        List<Event> allEvents3 = db.getAllEvents();
        for (Event event : allEvents3) {
        	 Log.d("Event:"+event.getTitle(),"ID:"+ event.getId()+"Description:"+event.getDescription());
        } 
        
  

        /**Test purpose
         *  
        // Getting all Events
        Log.d("Events ", "Getting All Events ");
 
        List<Event> allEvents = db.getAllEvents();
        for (Event event : allEvents) {
        	 Log.d("Event"+event.getTitle(),""+ event.getId()+event.getDescription());
        } 
        
        
        // Getting all category names
        Log.d(" after update", "Getting All categories after update");
 
        List<Category> allCategoryNew = db.getAllCategories();
        for (Category category : allCategoryNew) {
        	 Log.d("Category Name:"+category.getName(), ""+category.getId()+category.getColor());
      }
        
       db.deleteCategoryByID(10);
       db.deleteCategoryByID(11);
       db.deleteCategoryByID(12);
       
       db.deleteEvent(17);
       db.deleteEvent(20);
       db.deleteEvent(21);
       db.deleteEvent(23);
       db.deleteEvent(24);
        
       // Getting all Events
       Log.d("Events ", "Getting All Events ");

       List<Event> allEvents3 = db.getAllEvents();
       for (Event event : allEvents3) {
       	 Log.d("Event"+event.getTitle(),""+ event.getId()+event.getDescription());
       } 
       
       // Getting all category names
       Log.d(" after update", "Getting All categories after update");

       List<Category> allCategoryNew1 = db.getAllCategories();
       for (Category category : allCategoryNew1) {
       	 Log.d("Category Name:"+category.getName(), ""+category.getId()+category.getColor());
     }
       
    */
        // Don't forget to close database connection
        db.closeDB();
        
        
        ////////////////////////////////////////////////////////////////////////////////////// RossY End
        
        toTodaysDate = (Button) this.findViewById(R.id.toTodaysDate);
        toTodaysDate.setText(dateFormatter.format(dateTemplate, _calendar.getTime()));
        toTodaysDate.setOnClickListener(this);
        
        fromTodaysDate = (Button) this.findViewById(R.id.fromTodaysDate);
        fromTodaysDate.setText(dateFormatter.format(dateTemplate, _calendar.getTime()));
        fromTodaysDate.setOnClickListener(this);
        
        toCurrentTime = (Button) this.findViewById(R.id.toCurrentTime);
        toCurrentTime.setText(sdf.format(_calendar.getTime()));
        toCurrentTime.setOnClickListener(this);
        
        fromCurrentTime = (Button) this.findViewById(R.id.fromCurrentTime);
        fromCurrentTime.setText(sdf.format(_calendar.getTime()));
        fromCurrentTime.setOnClickListener(this);
        
        saveButton = (Button) this.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);
        
        cancelButton = (Button) this.findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(this);
    }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		if(v== toTodaysDate )
		{
			Intent intent = new Intent(this, DatePickerActivity.class);
		    intent.putExtra("Caller", "To_Date");
			startActivityForResult(intent, 0);


			Log.d(tag,"Inside to todays date" );
		}
		else 	if(v== fromTodaysDate )
		{
			Intent intent = new Intent(this, DatePickerActivity.class);
		    intent.putExtra("Caller", "From_Date");
			startActivityForResult(intent, 0);
			
			Log.d(tag,"Inside from todays date");
		}
		else 	if(v== toCurrentTime )
		{
			Intent intent = new Intent(this, TimePickerActivity.class);
		    intent.putExtra("Caller", "To_Time");
			startActivityForResult(intent, 0);


			Log.d(tag,"Inside to current time");
		}
		else 	if(v== fromCurrentTime )
		{
			Intent intent = new Intent(this, TimePickerActivity.class);
		    intent.putExtra("Caller", "From_Time");
			startActivityForResult(intent, 0);
			
			Log.d(tag,"Inside from current time");
		}
		
		else 	if(v== cancelButton )
		{
			Log.d(tag,"Inside cancel buttun");
		}
		else 	if(v== saveButton )
		{
			Log.d(tag,"Inside save button");
		}
	}
	
	

}
