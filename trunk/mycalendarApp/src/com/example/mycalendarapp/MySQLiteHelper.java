package com.example.mycalendarapp;


import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper{
	
	// Logcat tag
    private static final String LOG = MySQLiteHelper.class.getName();;
 
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "CategoryDB";
   
    //
    
    // Table Names
    private static final String TABLE_CATEGORY = "categories";
    private static final String TABLE_EVENT = "events";
    private static final String TABLE_CATEGORY_EVENT = "categories_events";
 
    // Common column names
    private static final String KEY_ID = "id";
   
    // categories Table - column names
    private static final String KEY_CATEGORY_NAME = "name";
    private static final String KEY_CATEGORY_COLOR = "color";
 
    // events Table - column names
    private static final String KEY_EVENT_TITLE = "title";
    private static final String KEY_EVENT_STARTDATE = "startDate";
    private static final String KEY_EVENT_ENDDATE = "endDate";
    private static final String KEY_EVENT_STARTTIME = "startTime";
    private static final String KEY_EVENT_ENDTIME = "endTime";
    private static final String KEY_EVENT_DESCRIPTION = "description";
    
    // categories_events Table - column names
    private static final String KEY_CATEGORY_ID = "category_id";
    private static final String KEY_EVENT_ID = "event_id";
 
    // Table Create Statements
    // categories table create statement
    private static final String CREATE_TABLE_CATEGORY = "CREATE TABLE " + TABLE_CATEGORY 
    		+ "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
    		+ KEY_CATEGORY_NAME + " TEXT," 
            + KEY_CATEGORY_COLOR + " TEXT )";
 
    // events table create statement
    private static final String CREATE_TABLE_EVENT = "CREATE TABLE " + TABLE_EVENT
            + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," 
    		+ KEY_EVENT_TITLE + " TEXT,"
    		+ KEY_EVENT_STARTDATE + " TEXT,"
    		+ KEY_EVENT_ENDDATE + " TEXT,"
    		+ KEY_EVENT_STARTTIME + " TEXT,"
    		+ KEY_EVENT_ENDTIME + " TEXT,"
            + KEY_EVENT_DESCRIPTION + " TEXT )";
 
    // categories_events table create statement
    private static final String CREATE_TABLE_CATEGORY_EVENT = "CREATE TABLE " + TABLE_CATEGORY_EVENT
    		+ "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_CATEGORY_ID + " INTEGER," //forign key???????????????????
    		+ KEY_EVENT_ID + " INTEGER )";    //forign key???????????????????
      
 
    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
 
        // creating required tables
    	db.execSQL(CREATE_TABLE_EVENT);
        db.execSQL(CREATE_TABLE_CATEGORY);
      //  db.execSQL(CREATE_TABLE_EVENT);
        db.execSQL(CREATE_TABLE_CATEGORY_EVENT);
    }
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
    public long createEvent(Event event, long[] category_ids) {
    	
        SQLiteDatabase db = this.getWritableDatabase();
     
        ContentValues values = new ContentValues();
        values.put(KEY_EVENT_TITLE, event.getTitle());
        values.put(KEY_EVENT_STARTDATE, event.getStartDate());
        values.put(KEY_EVENT_ENDDATE, event.getEndDate());
        values.put(KEY_EVENT_STARTTIME, event.getStartTime());
        values.put(KEY_EVENT_ENDTIME, event.getEndTime());
        values.put(KEY_EVENT_DESCRIPTION, event.getDescription());
        
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
            
        return ev;
    }
    
    /*
     * getting all events
     * */
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
     
                // adding to event list
                events.add(ev);
            } while (c.moveToNext());
        }
     
        return events;
    }
 
    /*
     * getting all events under single category
     * */
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
     
                // adding to event list
                events.add(ev);
                } while (c.moveToNext());
        }
     
        return events;
    }
    
    /**
     * getting event count
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
    
    /*
     * Updating an event
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
       
     //   Log.e("check", values.getAsString(KEY_EVENT_TITLE));
    //    Log.e("check", ""+event.getId());
        
        // updating row
        return db.update(TABLE_EVENT, values, KEY_ID + " = ?",
                new String[] { String.valueOf(event.getId()) });
    }
    
    /*
     * Deleting an event
     */
    public void deleteEvent(long event_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EVENT, KEY_ID + " = ?",
                new String[] { String.valueOf(event_id) });
    }
    
    /*
     * Creating category
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
     * Deleting a category
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
     * Creating event_category
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
    
    /*
     * Updating an event category
     */
    public int updateEventCategory(long id, long category_id) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_CATEGORY_ID, category_id);
 
        // updating row
        return db.update(TABLE_EVENT, values, KEY_ID + " = ?", /// confusion whether it is TABLE_EVENT or TABLE_EVENT_CATEGORY and others
                new String[] { String.valueOf(id) });
    }
  
    /*
     * Deleting an event category
     */
    public void deleteEventCategory(long id) {
        SQLiteDatabase db = this.getWritableDatabase();   /// confusion whether it is TABLE_EVENT or TABLE_EVENT_CATEGORY and others
        db.delete(TABLE_EVENT, KEY_ID + " = ?",           
                new String[] { String.valueOf(id) });
    }
	public void deleteCategoryByID(int i) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = this.getWritableDatabase();
		// now delete the category
        db.delete(TABLE_CATEGORY, KEY_ID + " = ?",
                new String[] { String.valueOf(i) });
      }
  
	
	////get event by name
	public Event getEvent(String title) {
    	
        SQLiteDatabase db = this.getReadableDatabase();
     
        String selectQuery = "SELECT  * FROM " + TABLE_EVENT + " WHERE "
                + KEY_EVENT_TITLE + " = " + title;
     
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
            
        return ev;
    }
/////////////////////////// Not Sure....
}
