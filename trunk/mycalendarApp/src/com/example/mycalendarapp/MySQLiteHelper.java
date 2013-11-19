package com.example.mycalendarapp;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.example.mycalendarapp.Category;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

// TODO: Auto-generated Javadoc
/**
 * The Class MySQLiteHelper.
 */
public class MySQLiteHelper extends SQLiteOpenHelper{
	
	// Logcat tag
    /** The Constant LOG. */
	private static final String LOG = MySQLiteHelper.class.getName();;
 
    // Database Version
    /** The Constant DATABASE_VERSION. */
    private static final int DATABASE_VERSION = 1;
    // Database Name
    /** The Constant DATABASE_NAME. */
    private static final String DATABASE_NAME = "CategoryDB";
   
    //
    
    // Table Names
    /** The Constant TABLE_CATEGORY. */
    private static final String TABLE_CATEGORY = "categories";
    
    /** The Constant TABLE_EVENT. */
    private static final String TABLE_EVENT = "events";
    
    /** The Constant TABLE_CATEGORY_EVENT. */
    private static final String TABLE_CATEGORY_EVENT = "categories_events";
 
    // Common column names
    /** The Constant KEY_ID. */
    private static final String KEY_ID = "id";
   
    // categories Table - column names
    /** The Constant KEY_CATEGORY_NAME. */
    private static final String KEY_CATEGORY_NAME = "name";
    
    /** The Constant KEY_CATEGORY_COLOR. */
    private static final String KEY_CATEGORY_COLOR = "color";
 
    // events Table - column names
    /** The Constant KEY_EVENT_TITLE. */
    private static final String KEY_EVENT_TITLE = "title";
    
    /** The Constant KEY_EVENT_STARTDATE. */
    private static final String KEY_EVENT_STARTDATE = "startDate";
    
    /** The Constant KEY_EVENT_ENDDATE. */
    private static final String KEY_EVENT_ENDDATE = "endDate";
    
    /** The Constant KEY_EVENT_STARTTIME. */
    private static final String KEY_EVENT_STARTTIME = "startTime";
    
    /** The Constant KEY_EVENT_ENDTIME. */
    private static final String KEY_EVENT_ENDTIME = "endTime";
    
    /** The Constant KEY_EVENT_DESCRIPTION. */
    private static final String KEY_EVENT_DESCRIPTION = "description";
    
    /** The Constant KEY_EVENT_REPEAT. */
    private static final String KEY_EVENT_REPEAT = "repeat";
    
    // categories_events Table - column names
    /** The Constant KEY_CATEGORY_ID. */
    private static final String KEY_CATEGORY_ID = "category_id";
    
    /** The Constant KEY_EVENT_ID. */
    private static final String KEY_EVENT_ID = "event_id";
 
    // Table Create Statements
    // categories table create statement
    /** The Constant CREATE_TABLE_CATEGORY. */
    private static final String CREATE_TABLE_CATEGORY = "CREATE TABLE " + TABLE_CATEGORY 
    		+ "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
    		+ KEY_CATEGORY_NAME + " TEXT," 
            + KEY_CATEGORY_COLOR + " TEXT )";
 
    // events table create statement
    /** The Constant CREATE_TABLE_EVENT. */
    private static final String CREATE_TABLE_EVENT = "CREATE TABLE " + TABLE_EVENT
            + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," 
    		+ KEY_EVENT_TITLE     + " TEXT,"
    		+ KEY_EVENT_STARTDATE + " TEXT,"
    		+ KEY_EVENT_ENDDATE   + " TEXT,"
    		+ KEY_EVENT_STARTTIME + " TEXT,"
    		+ KEY_EVENT_ENDTIME   + " TEXT,"
    		+ KEY_EVENT_DESCRIPTION + " TEXT,"
    		+ KEY_EVENT_REPEAT   + " TEXT)";
 
    // categories_events table create statement
    /** The Constant CREATE_TABLE_CATEGORY_EVENT. */
    private static final String CREATE_TABLE_CATEGORY_EVENT = "CREATE TABLE " + TABLE_CATEGORY_EVENT
    		+ "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_CATEGORY_ID + " INTEGER," 
    		+ KEY_EVENT_ID + " INTEGER )";   
      
 
    /**
     * Instantiates a new my sq lite helper.
     *
     * @param context the context
     */
    public MySQLiteHelper(Context context) {
    	
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //context.deleteDatabase(DATABASE_NAME);
    }
    
    /* (non-Javadoc)
     * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
 
        // creating required tables
    	db.execSQL(CREATE_TABLE_EVENT);
        db.execSQL(CREATE_TABLE_CATEGORY);
      //  db.execSQL(CREATE_TABLE_EVENT);
        db.execSQL(CREATE_TABLE_CATEGORY_EVENT);
    }
    
    /* (non-Javadoc)
     * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY_EVENT);
 
        // create new tables
        onCreate(db);
    }
    /*
     * Creating an event
     */
    /**
     * Creates the event.
     *
     * @param event the event
     * @param category_ids the category_ids
     * @return the long
     */
    public long createEvent(Event event, long[] category_ids) {
    	
        SQLiteDatabase db = this.getWritableDatabase();
     
        ContentValues values = new ContentValues();
        values.put(KEY_EVENT_TITLE, event.getTitle());
        values.put(KEY_EVENT_STARTDATE, event.getStartDate());
        values.put(KEY_EVENT_ENDDATE, event.getEndDate());
        values.put(KEY_EVENT_STARTTIME, event.getStartTime());
        values.put(KEY_EVENT_ENDTIME, event.getEndTime());
        values.put(KEY_EVENT_DESCRIPTION, event.getDescription());
        values.put(KEY_EVENT_REPEAT, event.getRepeat());
        
        // insert row
        long event_id = db.insert(TABLE_EVENT, null, values);
     
        // assigning categories to events
        for (long category_id : category_ids) {
            createEventCategory(event_id, category_id);
        }
      //for logging
      //	Log.e("createEvent", event.toString()); 
      //   db.close();
      
        return event_id;
   
    }
    /*
     * get single event
     */
    /**
     * Gets the event.
     *
     * @param event_id the event_id
     * @return the event
     */
    public Event getEvent(long event_id) {
    	
        SQLiteDatabase db = this.getReadableDatabase();
     
        String selectQuery = "SELECT  * FROM " + TABLE_EVENT + " WHERE "
                + KEY_ID + " = " + event_id;
     
        Log.e(LOG, selectQuery);
     
        Cursor c = db.rawQuery(selectQuery, null);
     
        if (c != null)
            c.moveToFirst();
     
        Event ev = new Event();
        ev.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        ev.setTitle((c.getString(c.getColumnIndex(KEY_EVENT_TITLE))));
        ev.setStartDate((c.getString(c.getColumnIndex(KEY_EVENT_STARTDATE))));
        ev.setEndDate((c.getString(c.getColumnIndex(KEY_EVENT_ENDDATE))));
        ev.setStartTime((c.getString(c.getColumnIndex(KEY_EVENT_STARTTIME))));
        ev.setEndTime((c.getString(c.getColumnIndex(KEY_EVENT_ENDTIME))));
        ev.setDescription((c.getString(c.getColumnIndex(KEY_EVENT_DESCRIPTION))));
        ev.setRepeat((c.getString(c.getColumnIndex(KEY_EVENT_REPEAT))));
        
        return ev;
    }
    
    /*
     * getting all events
     * */
    /**
     * Gets the all events.
     *
     * @return the all events
     */
    public List<Event> getAllEvents() {
    	
        List<Event> events = new ArrayList<Event>();
        String selectQuery = "SELECT  * FROM " + TABLE_EVENT;
     
        Log.e(LOG, selectQuery);
     
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
     
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
            	Event ev = new Event();
            	ev.setId(c.getInt((c.getColumnIndex(KEY_ID))));
            	ev.setTitle((c.getString(c.getColumnIndex(KEY_EVENT_TITLE))));
                ev.setStartDate((c.getString(c.getColumnIndex(KEY_EVENT_STARTDATE))));
                ev.setEndDate((c.getString(c.getColumnIndex(KEY_EVENT_ENDDATE))));
                ev.setStartTime((c.getString(c.getColumnIndex(KEY_EVENT_STARTTIME))));
                ev.setEndTime((c.getString(c.getColumnIndex(KEY_EVENT_ENDTIME))));
                ev.setDescription((c.getString(c.getColumnIndex(KEY_EVENT_DESCRIPTION))));
                ev.setRepeat((c.getString(c.getColumnIndex(KEY_EVENT_REPEAT))));
     
                // adding to event list
                events.add(ev);
            } while (c.moveToNext());
        }
     
        return events;
    }
 
    /*
     * getting all events under single category
     * */
    /**
     * Gets the all events by category.
     *
     * @param category_name the category_name
     * @return the all events by category
     */
    public List<Event> getAllEventsByCategory(String category_name) {
      
    	List<Event> events = new ArrayList<Event>();
     
        String selectQuery = "SELECT  * FROM " + TABLE_EVENT + " te, "
                + TABLE_CATEGORY + " tc, " 
        		+ TABLE_CATEGORY_EVENT + " ce WHERE tc." + KEY_CATEGORY_NAME + " = '" + category_name + "'" 
        		+ " AND tc." + KEY_ID + " = " + "ce." + KEY_CATEGORY_ID 
                + " AND te." + KEY_ID + " = " + "ce." + KEY_EVENT_ID;
     
        Log.e(LOG, selectQuery);
     
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
     
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
            	Event ev = new Event();
            	ev.setId(c.getInt((c.getColumnIndex(KEY_ID))));
            	ev.setTitle((c.getString(c.getColumnIndex(KEY_EVENT_TITLE))));
                ev.setStartDate((c.getString(c.getColumnIndex(KEY_EVENT_STARTDATE))));
                ev.setEndDate((c.getString(c.getColumnIndex(KEY_EVENT_ENDDATE))));
                ev.setStartTime((c.getString(c.getColumnIndex(KEY_EVENT_STARTTIME))));
                ev.setEndTime((c.getString(c.getColumnIndex(KEY_EVENT_ENDTIME))));
                ev.setDescription((c.getString(c.getColumnIndex(KEY_EVENT_DESCRIPTION))));
                ev.setRepeat((c.getString(c.getColumnIndex(KEY_EVENT_REPEAT))));
     
                // adding to event list
                events.add(ev);
                } while (c.moveToNext());
        }
     
        return events;
    }
    
    /**
     * getting event count.
     *
     * @return the event count
     */
    public int getEventCount() {
        String countQuery = "SELECT  * FROM " + TABLE_EVENT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(countQuery, null);
 
        int count = c.getCount();
        c.close();
 
        // return count
        return count;
    }
    
    /**
     * Gets the category count.
     *
     * @return the category count
     */
    public int getCategoryCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CATEGORY;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(countQuery, null);
 
        int count = c.getCount();
        c.close();
 
        // return count
        return count;
    }
    
    /*
     * Updating an event
     */
    /**
     * Update event.
     *
     * @param event the event
     * @return the int
     */
    public int updateEvent(Event event) {
       
    	SQLiteDatabase db = this.getWritableDatabase();
     
        ContentValues values = new ContentValues();
        values.put(KEY_EVENT_TITLE, event.getTitle());
        values.put(KEY_EVENT_STARTDATE, event.getStartDate());
        values.put(KEY_EVENT_ENDDATE, event.getEndDate());
        values.put(KEY_EVENT_STARTTIME, event.getStartTime());
        values.put(KEY_EVENT_ENDTIME, event.getEndTime());
        values.put(KEY_EVENT_DESCRIPTION, event.getDescription());
        values.put(KEY_EVENT_REPEAT, event.getRepeat());
       
     //   Log.e("check", values.getAsString(KEY_EVENT_TITLE));
    //    Log.e("check", ""+event.getId());
        
        // updating row
        return db.update(TABLE_EVENT, values, KEY_ID + " = ?",
                new String[] { String.valueOf(event.getId()) });
    }
    
    /*
     * Deleting an event
     */
    /**
     * Delete event.
     *
     * @param event_id the event_id
     */
    public void deleteEvent(long event_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EVENT, KEY_ID + " = ?",
                new String[] { String.valueOf(event_id) });
    }
    
    /*
     * Creating category
     */
    /**
     * Creates the category.
     *
     * @param category the category
     * @return the long
     */
    public long createCategory(Category category) {
    	
        SQLiteDatabase db = this.getWritableDatabase();
     
        ContentValues values = new ContentValues();
        values.put(KEY_CATEGORY_NAME, category.getName());
        values.put(KEY_CATEGORY_COLOR, category.getColor());
    
        // insert row
        long category_id = db.insert(TABLE_CATEGORY, null, values);
     
        return category_id;
    }
    
    /*
     * getting all categories 
     * */
    /**
     * Gets the all categories.
     *
     * @return the all categories
     */
    public List<Category> getAllCategories() {
      
    	List<Category> categories = new ArrayList<Category>();
        String selectQuery = "SELECT  * FROM " + TABLE_CATEGORY;
     
        Log.e(LOG, selectQuery);
     
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
     
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
            	Category ct = new Category();
                ct.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                ct.setName(c.getString(c.getColumnIndex(KEY_CATEGORY_NAME)));
                ct.setColor(c.getString(c.getColumnIndex(KEY_CATEGORY_COLOR)));
     
                // adding to tags list
                categories.add(ct);
            } while (c.moveToNext());
        }
        return categories;
    }
    

    /*
     * Updating a category
     */
    /**
     * Update category.
     *
     * @param category the category
     * @return the int
     */
    public int updateCategory(Category category) {
        SQLiteDatabase db = this.getWritableDatabase();
     
        ContentValues values = new ContentValues();
        values.put(KEY_CATEGORY_NAME, category.getName());
      //  Log.e("check", category.getName());
        values.put(KEY_CATEGORY_COLOR, category.getColor());
     
        // updating row
        return db.update(TABLE_CATEGORY, values, KEY_ID + " = ?",
                new String[] { String.valueOf(category.getId()) });
    }
  /*
   * Get Category by Category Name
   */
    
    /**
   * Gets the category id.
   *
   * @param categoryName the category name
   * @return the category id
   */
  public Category getCategoryID(String categoryName){
    	
    	Category ct = new Category();
    	String selectQuery = "SELECT  * FROM " + TABLE_CATEGORY + " tc WHERE tc." + KEY_CATEGORY_NAME + " = '" + categoryName+ "'" ;
    	
    	 Log.e(LOG, selectQuery);
         
         SQLiteDatabase db = this.getReadableDatabase();
         Cursor c = db.rawQuery(selectQuery, null);
         
         if (c.moveToFirst()) {          
             	
                 ct.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                 ct.setName(c.getString(c.getColumnIndex(KEY_CATEGORY_NAME)));
                 ct.setColor(c.getString(c.getColumnIndex(KEY_CATEGORY_COLOR)));         
           
         }
    	
    	 return  ct;
    }
    
    /*
     * For a given event id it will provide corresponding category
     * 
     */

    /**
     * Gets the category by event.
     *
     * @param event_id the event_id
     * @return the category by event
     */
    public Category  getCategoryByEvent(long event_id) {
       
   	Category ct = new Category();     
       String selectQuery = "SELECT  * FROM " + TABLE_CATEGORY + " tc, "
       		+ TABLE_EVENT + " te, "                 
       		+ TABLE_CATEGORY_EVENT + " ce WHERE te." + KEY_ID + " = " + event_id 
       		+ " AND tc." + KEY_ID + " = " + "ce." + KEY_CATEGORY_ID 
               + " AND te." + KEY_ID + " = " + "ce." + KEY_EVENT_ID;            
    
       Log.e("In getCategoryByEvent" , selectQuery);
       SQLiteDatabase db = this.getReadableDatabase();
       Cursor c = db.rawQuery(selectQuery, null);
       
       if (c.moveToFirst()) {          
           	
    	       ct.setId(c.getLong((c.getColumnIndex(KEY_CATEGORY_ID))));
               ct.setName(c.getString(c.getColumnIndex(KEY_CATEGORY_NAME)));
               ct.setColor(c.getString(c.getColumnIndex(KEY_CATEGORY_COLOR)));             
       }
  	
  	 return  ct;     
      
   }
    /*
     * Deleting a category
     */
    /**
     * Delete category.
     *
     * @param category the category
     * @param should_delete_all_category_events the should_delete_all_category_events
     */
    public void deleteCategory(Category category, boolean should_delete_all_category_events) {
        SQLiteDatabase db = this.getWritableDatabase();
     
        // before deleting category
        // check if events under this category should also be deleted
        if (should_delete_all_category_events) {
            // get all events under this category
            List<Event> allCategoryEvent = getAllEventsByCategory(category.getName());
     
            // delete all events
            for (Event event : allCategoryEvent) {
                // delete event
                deleteEvent(event.getId());
            }
        }
     
        // now delete the category
        db.delete(TABLE_CATEGORY, KEY_ID + " = ?",
                new String[] { String.valueOf(category.getId()) });
    } 
    

    
    
    /*
     * getting all events under single category
     * */
  
    
    /*
     * Creating event_category
     */
    /**
     * Creates the event category.
     *
     * @param event_id the event_id
     * @param category_id the category_id
     * @return the long
     */
    public long  createEventCategory(long event_id, long category_id) {
        
    	SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_EVENT_ID, event_id);
        values.put(KEY_CATEGORY_ID, category_id);
       
        long id = db.insert(TABLE_CATEGORY_EVENT, null, values);
 
        return id;
    }
  
 // closing database
    /**
  * Close db.
  */
 public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
 
    /*
     * get date time
  
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
    
     * */  
  ///////////////////////////// Not Sure....
    
    //calling method-> int ck = db.updateEventCategory(db.getCatEvByEvent(56), 14); // here 56 is event id and 14 is category id  
    
    /*
     * Updating an event category
     */
    /**
     * Update event category.
     *
     * @param id the id
     * @param category_id the category_id
     * @return the int
     */
    public int updateEventCategory(long id, long category_id) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_CATEGORY_ID, category_id);
 
        // updating row
        return db.update(TABLE_CATEGORY_EVENT, values, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
    }
    
    /*
     *  It will provide the third table id by event id
     */
    /**
     * Gets the cat ev by event.
     *
     * @param event_id the event_id
     * @return the cat ev by event
     */
    public long  getCatEvByEvent(long event_id) {        
        
        String selectQuery = "SELECT  * FROM " + TABLE_CATEGORY_EVENT + " ce WHERE ce." + KEY_EVENT_ID + " = " + event_id ;      		
              
     
       // Log.e("In getCatEvByEvent" , selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        
       long catEvId = 0 ;
       long ev = 0 ;
       long cg = 0 ;
        if (c.moveToFirst()) {            	
        	catEvId =c.getLong((c.getColumnIndex(KEY_ID)));   
        	ev =c.getLong((c.getColumnIndex(KEY_EVENT_ID)));   
        	cg =c.getLong((c.getColumnIndex(KEY_CATEGORY_ID)));   
        	
        	
        }
        Log.d("CATEV id: ", " " +catEvId);
        Log.d("EVENT id: ", " " +ev);
        Log.d("CATEGORY id: ", " " +cg);
   	 return  catEvId;     
       
    }
  
    /*
     * Deleting an event category
     */
    /**
     * Delete event category.
     *
     * @param id the id
     */
    public void deleteEventCategory(long id) {
        SQLiteDatabase db = this.getWritableDatabase();   /// confusion whether it is TABLE_EVENT or TABLE_EVENT_CATEGORY and others
        db.delete(TABLE_EVENT, KEY_ID + " = ?",           
                new String[] { String.valueOf(id) });
    }
	
	/**
	 * Delete category by id.
	 *
	 * @param i the i
	 */
	public void deleteCategoryByID(int i) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = this.getWritableDatabase();
		// now delete the category
        db.delete(TABLE_CATEGORY, KEY_ID + " = ?",
                new String[] { String.valueOf(i) });
      }
  
	
	////get event by name
	/**
	 * Gets the event.
	 *
	 * @param title the title
	 * @return the event
	 */
	public Event getEvent(String title) {
    	
        SQLiteDatabase db = this.getReadableDatabase();
     
        String selectQuery = "SELECT  * FROM " + TABLE_EVENT + " WHERE "
                + KEY_EVENT_TITLE + " = '" + title+"'";
     
        Log.e(LOG, selectQuery);
     
        Cursor c = db.rawQuery(selectQuery, null);
     
        if (c != null)
            c.moveToFirst();
     
        Event ev = new Event();
        ev.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        ev.setTitle((c.getString(c.getColumnIndex(KEY_EVENT_TITLE))));
        ev.setStartDate((c.getString(c.getColumnIndex(KEY_EVENT_STARTDATE))));
        ev.setEndDate((c.getString(c.getColumnIndex(KEY_EVENT_ENDDATE))));
        ev.setStartTime((c.getString(c.getColumnIndex(KEY_EVENT_STARTTIME))));
        ev.setEndTime((c.getString(c.getColumnIndex(KEY_EVENT_ENDTIME))));
        ev.setDescription((c.getString(c.getColumnIndex(KEY_EVENT_DESCRIPTION))));
        ev.setRepeat((c.getString(c.getColumnIndex(KEY_EVENT_REPEAT))));
        
        return ev;
    }
	
	
	/**
	 * Gets the event.
	 *
	 * @param startDate the start date
	 * @param endDate the end date
	 * @return the event
	 */
	public List<Event> getEvent(String startDate, String endDate) {
		List<Event> events = new ArrayList<Event>();
        SQLiteDatabase db = this.getReadableDatabase();
     
        String selectQuery = "SELECT  * FROM " + TABLE_EVENT + " WHERE "
                + KEY_EVENT_STARTDATE + " = '" + startDate+"' AND " +KEY_EVENT_ENDDATE+" = '"+endDate+"'";
     
        Log.e(LOG, selectQuery);
     
        Cursor c = db.rawQuery(selectQuery, null);
        
         if (c.moveToFirst()) {
            do {
            	Event ev = new Event();
            	ev.setId(c.getInt((c.getColumnIndex(KEY_ID))));
            	ev.setTitle((c.getString(c.getColumnIndex(KEY_EVENT_TITLE))));
                ev.setStartDate((c.getString(c.getColumnIndex(KEY_EVENT_STARTDATE))));
                ev.setEndDate((c.getString(c.getColumnIndex(KEY_EVENT_ENDDATE))));
                ev.setStartTime((c.getString(c.getColumnIndex(KEY_EVENT_STARTTIME))));
                ev.setEndTime((c.getString(c.getColumnIndex(KEY_EVENT_ENDTIME))));
                ev.setDescription((c.getString(c.getColumnIndex(KEY_EVENT_DESCRIPTION))));
                ev.setRepeat((c.getString(c.getColumnIndex(KEY_EVENT_REPEAT))));
                
                // adding to event list
                events.add(ev);
            } while (c.moveToNext());
        }
        
       return events;
    }
	/*
	 * 
	 * check conflict before event insert
	 */

	/**
	 * Check conflictin events.
	 *
	 * @param newEvent the new event
	 * @return true, if successful
	 * @throws ParseException the parse exception
	 */
	public boolean checkConflictinEvents(Event newEvent ) throws ParseException{
		
		SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm");
		
		String sTime = newEvent.getStartTime();
		String sDate = newEvent.getStartDate();
		String eTime = newEvent.getEndTime();
		String eDate = newEvent.getEndDate();			
    	
    	Date parsedStartTime = timeFormat.parse(sTime);
    	Timestamp newStartTime = new Timestamp(parsedStartTime.getTime());    	
    	Date parsedEndTime = timeFormat.parse(eTime);
    	Timestamp newEndTime = new Timestamp(parsedEndTime.getTime());
    	
    	if(sDate.equalsIgnoreCase(eDate)){
    		
    		if(newStartTime.after(newEndTime)){
    		
    			Log.e("Illogiacl input","StartTime is greater then EndTime");        			
    			return false;    		
    		}
    	}
	
		List<Event> allEvents = getEvent(sDate,eDate);
        for (Event prevEvent : allEvents) {
        	 
        	Log.d("Event: "+prevEvent.getTitle(),""+ prevEvent.getId()+"  "+prevEvent.getDescription());
        	
        	Date parsedPreviousStartTime = timeFormat.parse(prevEvent.getStartTime());
        	Timestamp previousStartTime = new Timestamp(parsedPreviousStartTime.getTime());    	
        	Date parsedPreviousEndTime = timeFormat.parse(prevEvent.getEndTime());
        	Timestamp previousEndTime = new Timestamp(parsedPreviousEndTime.getTime());
        	
        	if( newStartTime.after(previousStartTime)  &&  newStartTime.before(previousEndTime)  ||
        		newEndTime.after(previousStartTime)    &&  newEndTime.before(previousEndTime)    ||
        		previousStartTime.after(newStartTime)  &&  previousStartTime.before(newEndTime)  ||
        		previousEndTime.after(newStartTime)    &&  previousEndTime.before(newEndTime) ) {       		   		
        		
        			Log.e("Conflict with existent event","Can not insert new event");        			
        			return false;
        	}
        	
        	/* D:10   11
        	 * newTime  : 23   02
        	 * already  : 5     8        	  
        	 */   	 
        }         
        return true;   
				
	}
/*
 *Check before event update
 */
	
/**
 * Check conflictin update events.
 *
 * @param newEvent the new event
 * @return true, if successful
 * @throws ParseException the parse exception
 */
public boolean checkConflictinUpdateEvents(Event newEvent ) throws ParseException{
		
		SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm");
		
		String sTime = newEvent.getStartTime();
		String sDate = newEvent.getStartDate();
		String eTime = newEvent.getEndTime();
		String eDate = newEvent.getEndDate();			
    	
    	Date parsedStartTime = timeFormat.parse(sTime);
    	Timestamp newStartTime = new Timestamp(parsedStartTime.getTime());    	
    	Date parsedEndTime = timeFormat.parse(eTime);
    	Timestamp newEndTime = new Timestamp(parsedEndTime.getTime());
    	
    	if(sDate.equalsIgnoreCase(eDate)){
    		
    		if(newStartTime.after(newEndTime)){
    		
    			Log.e("Illogiacl Update","StartTime is greater then EndTime");        			
    			return false;    		
    		}
    	}
    	
		List<Event> allEvents = getEvent(sDate,eDate);
        for (Event prevEvent : allEvents) {
        	 
        	Log.d("Event: "+prevEvent.getTitle(),""+ prevEvent.getId()+"  "+prevEvent.getDescription());
        	
        	Date parsedPreviousStartTime = timeFormat.parse(prevEvent.getStartTime());
        	Timestamp previousStartTime = new Timestamp(parsedPreviousStartTime.getTime());    	
        	Date parsedPreviousEndTime = timeFormat.parse(prevEvent.getEndTime());
        	Timestamp previousEndTime = new Timestamp(parsedPreviousEndTime.getTime());
        	
        	if( newStartTime.after(previousStartTime)  &&  newStartTime.before(previousEndTime)  ||
        		newEndTime.after(previousStartTime)    &&  newEndTime.before(previousEndTime)    ||
        		previousStartTime.after(newStartTime)  &&  previousStartTime.before(newEndTime)  ||
        		previousEndTime.after(newStartTime)  &&  previousEndTime.before(newEndTime) ) {
        		
        		if(newEvent.getId()!=prevEvent.getId()){
        			Log.e("Conflict with existent event","Can not update this event");      		
        			return false;
        		}
        	}        	
           	 
        }         
        return true;   
				
	}
/*
 * printing the 3rd table
 */
		
	  /**
 * Prints the catea vtable.
 */
public void printCATEAVtable(){    	
	    	
	    	String selectQuery = "SELECT  * FROM " + TABLE_CATEGORY_EVENT ;
	    	
	    	 Log.e(LOG, selectQuery);
	         
	         SQLiteDatabase db = this.getReadableDatabase();
	         Cursor c = db.rawQuery(selectQuery, null);
	         
	         if (c.moveToFirst()) { 
	        	 do {
	             	
	        	 Log.d("id", ""+c.getInt((c.getColumnIndex(KEY_ID))));
	        	 Log.e("cid", c.getString(c.getColumnIndex(KEY_CATEGORY_ID)));
	        	 Log.d("eid",  c.getString(c.getColumnIndex(KEY_EVENT_ID)));  
	        	 } while (c.moveToNext());
	         }   	
	    	
	    }
	  
	  /**
  	 * Clear datebase.
  	 */
  	public void clearDatebase(){
		   
		  SQLiteDatabase db = this.getWritableDatabase();
		  db.delete(TABLE_EVENT, "1",null);	 
		  db.delete(TABLE_CATEGORY, "1",null);	
		  db.delete(TABLE_CATEGORY_EVENT, "1",null);	
		    	
		    }
	  
	  
	  /**
  	 * Gets the repeat event.
  	 *
  	 * @param startDate the start date
  	 * @param endDate the end date
  	 * @param startTime the start time
  	 * @param endTime the end time
  	 * @return the repeat event
  	 */
  	public long getRepeatEvent(String startDate, String endDate, String startTime, String endTime) {
			
	        SQLiteDatabase db = this.getReadableDatabase();
	     
	        String selectQuery = "SELECT  * FROM " + TABLE_EVENT + " WHERE "
	                + KEY_EVENT_STARTDATE + " = '" + startDate+"' AND " +KEY_EVENT_ENDDATE+" = '"+endDate+"' AND "
	        		+KEY_EVENT_STARTTIME+ " = '"+startTime+"' AND "+KEY_EVENT_ENDTIME+" = '"+endTime+"'";
	     
	        Log.e(LOG, selectQuery);
	        long eventID=0;
	        Cursor c = db.rawQuery(selectQuery, null);
	        
	         if (c.moveToFirst()) {
	            //do {
	            	Event ev = new Event();
	            	eventID=(long)c.getInt((c.getColumnIndex(KEY_ID)));
	            	
	                
	                // adding to event list
	                //events.add(ev);
	            //} while (c.moveToNext());
	        }
	        
	       return eventID;
	    }
}
