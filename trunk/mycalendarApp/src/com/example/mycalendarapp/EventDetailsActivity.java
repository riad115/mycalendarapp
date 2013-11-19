package com.example.mycalendarapp;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TabHost;
import android.widget.TextView;

// TODO: Auto-generated Javadoc
/**
 * The Class EventDetailsActivity.
 */
public class EventDetailsActivity extends Activity implements OnClickListener{
	
	/** The list adapter. */
	private EventListAdapter listAdapter;
	//private MySQLiteHelper db;
	/** The txt event. */
	private TextView[] txtEvent; 
	
	/** The all events. */
	private List<Event> allEvents;
	
	/** The cat events. */
	private List<Event> catEvents;
	
	/** The sel event. */
	private Event selEvent;
	
	/** The event title. */
	private String eventTitle;
	
	/** The event id. */
	public static long eventID;
	
	/** The del category. */
	public Category delCategory;
	
	/** The position. */
	private int position;
	
	/** The title. */
	private TextView title;
	
	/** The description. */
	private TextView description;
	
	/** The date. */
	private TextView date;
	
	/** The time. */
	private TextView time;
	
	/** The repeat. */
	private TextView repeat;
	
	/** The category. */
	private TextView category;
	
	/** The status. */
	private TextView status;
	
	/** The repeat event. */
	private String repeatEvent;
	
	/** The from date. */
	private String fromDate;
	
	/** The to date. */
	private String toDate;
	
	/** The from time. */
	private String fromTime;
	
	/** The to time. */
	private String toTime;
	
	/** The Id. */
	private long Id;
	
	/** The category name. */
	private String categoryName;
	
	/** The days of month. */
	private final int[] daysOfMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        //TextView textview = new TextView(this);
        //textview.setText("This is Daily tab");
        setContentView(R.layout.event_details_view);
      //  db = new  MySQLiteHelper(this);
		
		// Getting all Events
	   /* Log.d("Get Events", "Getting All Events");

	    allEvents = EventActivity.db.getAllEvents();
	    for (Event event : allEvents) {
	        Log.d("Event:"+event.getTitle(),"ID:"+ event.getId()+"Description:"+event.getDescription());
	    }*/
        //Intent i = getIntent();
        Bundle extras = getIntent().getExtras();
        // Selected image id
        if(extras!=null){
        	int activity = extras.getInt("activity");
        	if(activity ==1){
        		position = extras.getInt("id");
        		printEventDetails(position);
        	}
        	
        	if(activity==2){
        		//eventTitle = extras.getString("EVENT_TITLE");
        		Id = extras.getLong("EVENT_ID");
        		printEventDetails(Id);
        	}
        	
        	if(activity==3){
        		//eventTitle = extras.getString("EVENT_TITLE");
        		Id = extras.getLong("EVENT_ID");
        		printEventDetails(Id);
        	}
        	
        }
        /*Log.d("Event:"+ position, "");
        eventID = allEvents.get(position).getId();
     
       
        title = (TextView) findViewById(R.id.textView1);
        title.setText(allEvents.get(position).getTitle());
        
        description = (TextView) findViewById(R.id.eventDescription);
        description.setText(allEvents.get(position).getDescription());
        
        date = (TextView) findViewById(R.id.textView2);
        date.setText(allEvents.get(position).getStartDate()+" - "+ allEvents.get(position).getEndDate());
        
        time = (TextView) findViewById(R.id.textView3);
        time.setText(allEvents.get(position).getStartTime()+" - "+allEvents.get(position).getEndTime());
        
        repeat = (TextView) findViewById(R.id.textView5);
        repeat.setText("No");
        
        category = (TextView) findViewById(R.id.textView7);
        category.setText(SimpleCalendarView.db.getCategoryByEvent(allEvents.get(position).getId()).getName().toString());
        
        status = (TextView) findViewById(R.id.textView9);
        status.setText("Busy");*/
	}
	
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.event_details, menu);
		return true;
	}
	
	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    case R.id.delete_event:
	    	
	    	if(repeatEvent.equals("Repeat-ON")){
	    		String[] getmonth = fromDate.split("-");
        		int daysInMonth = daysOfMonth[Integer.parseInt(getmonth[1])-1];
        		int selectedDate = Integer.parseInt(getmonth[2]);
        		//boolean checkConflict=true;
        		String startDt= fromDate;
        		long id =eventID;
        		for(int i=selectedDate;i<=daysInMonth;i=i+7){
        			   SimpleCalendarView.db.deleteEvent(id);
        				
        			   String[] getDate = startDt.split("-");
        			   startDt = getDate[0]+"-"+getDate[1]+"-"+Integer.toString(Integer.parseInt(getDate[2])+7);
        			   id=SimpleCalendarView.db.getRepeatEvent(startDt, startDt, fromTime, toTime);
        			   Log.d("Inside Repeat event:"+ i, "");
        		}
        		
        		catEvents = SimpleCalendarView.db.getAllEventsByCategory(categoryName);
		    	delCategory = SimpleCalendarView.db.getCategoryID(categoryName);
		    	if(catEvents.size()==0){
		    		SimpleCalendarView.db.deleteCategoryByID((int)delCategory.getId());
		    		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
		    				this);
		     
		    			// set title
		    			alertDialogBuilder.setTitle("Deleting Category");
		     
		    			// set dialog message
		    			alertDialogBuilder
		    				.setMessage("Repeat Event as well as Category is deleted as there is no Event under this category")
		    				.setCancelable(false)
		    				
		    				.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
		    					public void onClick(DialogInterface dialog,int id) {
		    						// if this button is clicked, just close
		    						// the dialog box and do nothing
		    						//dialog.cancel();
		    						MainCalenadarActivity.tabHost.setCurrentTab(0);
		    				        EventDetailsActivity.this.finish();
		    					}
		    				});
		     
		    				// create alert dialog
		    				AlertDialog alertDialog = alertDialogBuilder.create();
		     
		    				// show it
		    				alertDialog.show();
		    	}
		        //Intent del = new Intent(this, EventList.class);
		        //del.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
		        //startActivity(del);
		        //TabHost tabHost = (TabHost) findViewById(R.id.tabhost);
		        //tabHost.setCurrentTab(3);
		        
		       // ((TabActivity) getParent()).getTabHost().setCurrentTab(3);
		    	//MainCalenadarActivity ta = (MainCalenadarActivity) this.getParent();
		    	//TabHost th = ta.getMyTabHost();
		    	else{
		    	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
	    				this);
	     
	    			// set title
	    			alertDialogBuilder.setTitle("Deleting Event");
	     
	    			// set dialog message
	    			alertDialogBuilder
	    				.setMessage("Repeat Event is deleted!!")
	    				.setCancelable(false)
	    				
	    				.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
	    					public void onClick(DialogInterface dialog,int id) {
	    						// if this button is clicked, just close
	    						// the dialog box and do nothing
	    						//dialog.cancel();
	    						MainCalenadarActivity.tabHost.setCurrentTab(0);
	    				        EventDetailsActivity.this.finish();
	    					}
	    				});
	     
	    				// create alert dialog
	    				AlertDialog alertDialog = alertDialogBuilder.create();
	     
	    				// show it
	    				alertDialog.show();
		    	//MainCalenadarActivity.tabHost.setCurrentTab(0);
		        //this.finish();
		    	}
        		//Intent del = new Intent(this, EventList.class);
		        //startActivity(del);
		        //TabHost tabHost = (TabHost) findViewById(R.id.tabhost);
		        //tabHost.setCurrentTab(3);
        		//MainCalenadarActivity.tabHost.setCurrentTab(0);
		       // this.finish();
		        return true;
	    	}
	    	else{
		    	SimpleCalendarView.db.deleteEvent(eventID);
		    	catEvents = SimpleCalendarView.db.getAllEventsByCategory(categoryName);
		    	delCategory = SimpleCalendarView.db.getCategoryID(categoryName);
		    	if(catEvents.size()==0){
		    		SimpleCalendarView.db.deleteCategoryByID((int)delCategory.getId());
		    		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
		    				this);
		     
		    			// set title
		    			alertDialogBuilder.setTitle("Deleting Category");
		     
		    			// set dialog message
		    			alertDialogBuilder
		    				.setMessage("Event as well as Category is deleted as there is no Event under this category")
		    				.setCancelable(false)
		    				
		    				.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
		    					public void onClick(DialogInterface dialog,int id) {
		    						// if this button is clicked, just close
		    						// the dialog box and do nothing
		    						//dialog.cancel();
		    						MainCalenadarActivity.tabHost.setCurrentTab(0);
		    				        EventDetailsActivity.this.finish();
		    					}
		    				});
		     
		    				// create alert dialog
		    				AlertDialog alertDialog = alertDialogBuilder.create();
		     
		    				// show it
		    				alertDialog.show();
		    	}
		        //Intent del = new Intent(this, EventList.class);
		        //del.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
		        //startActivity(del);
		        //TabHost tabHost = (TabHost) findViewById(R.id.tabhost);
		        //tabHost.setCurrentTab(3);
		        
		       // ((TabActivity) getParent()).getTabHost().setCurrentTab(3);
		    	//MainCalenadarActivity ta = (MainCalenadarActivity) this.getParent();
		    	//TabHost th = ta.getMyTabHost();
		    	else{
		    	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
	    				this);
	     
	    			// set title
	    			alertDialogBuilder.setTitle("Deleting Event");
	     
	    			// set dialog message
	    			alertDialogBuilder
	    				.setMessage("Event is deleted!!")
	    				.setCancelable(false)
	    				
	    				.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
	    					public void onClick(DialogInterface dialog,int id) {
	    						// if this button is clicked, just close
	    						// the dialog box and do nothing
	    						//dialog.cancel();
	    						MainCalenadarActivity.tabHost.setCurrentTab(0);
	    				        EventDetailsActivity.this.finish();
	    					}
	    				});
	     
	    				// create alert dialog
	    				AlertDialog alertDialog = alertDialogBuilder.create();
	     
	    				// show it
	    				alertDialog.show();
		    	//MainCalenadarActivity.tabHost.setCurrentTab(0);
		        //this.finish();
		    	}
		        return true;
		    }
	    case R.id.edit_event:
			Intent intent = new Intent(this, EditEventActivity.class);
			//intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
			startActivity(intent);
			
			this.finish();
			return true;
			
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}
	
	
	/**
	 * Prints the event details.
	 *
	 * @param id the id
	 */
	public void printEventDetails(Long id){
		Log.d("Get ID", id.toString());
		selEvent = SimpleCalendarView.db.getEvent(id);
	    //for (Event event : allEvents) {
	        //Log.d("Event:"+event.getTitle(),"ID:"+ event.getId()+"Description:"+event.getDescription());
	    //}
		

	    //Log.d("Event:"+ , "");
        eventID = selEvent.getId();
        

        title = (TextView) findViewById(R.id.textView1);
        title.setText(selEvent.getTitle());
        
        description = (TextView) findViewById(R.id.eventDescription);
        description.setText(selEvent.getDescription());
        
        date = (TextView) findViewById(R.id.textView2);
        date.setText(selEvent.getStartDate()+" - "+ selEvent.getEndDate());
        fromDate= selEvent.getStartDate();
        toDate = selEvent.getEndDate();
        
        time = (TextView) findViewById(R.id.textView3);
        time.setText(selEvent.getStartTime()+" - "+selEvent.getEndTime());
        fromTime= selEvent.getStartTime();
        toTime = selEvent.getEndTime();
        
        
        repeat = (TextView) findViewById(R.id.textView5);
        repeat.setText(selEvent.getRepeat());
        repeatEvent = selEvent.getRepeat();
        category = (TextView) findViewById(R.id.textView7);
        category.setText(SimpleCalendarView.db.getCategoryByEvent(eventID).getName().toString());
        categoryName= SimpleCalendarView.db.getCategoryByEvent(eventID).getName().toString();
        
        
        status = (TextView) findViewById(R.id.textView9);
        status.setText("Busy");
	}
	
	
	/**
	 * Prints the event details.
	 *
	 * @param id the id
	 */
	public void printEventDetails(Integer id){
		Log.d("Get Events", "Getting All Events");

	    allEvents = SimpleCalendarView.db.getAllEvents();
	    for (Event event : allEvents) {
	        Log.d("Event:"+event.getTitle(),"ID:"+ event.getId()+"Description:"+event.getDescription());
	    }
	    
	    Log.d("Event:"+ position, "");
        eventID = allEvents.get(position).getId();
        

        title = (TextView) findViewById(R.id.textView1);
        title.setText(allEvents.get(position).getTitle());
        
        description = (TextView) findViewById(R.id.eventDescription);
        description.setText(allEvents.get(position).getDescription());
        
        date = (TextView) findViewById(R.id.textView2);
        date.setText(allEvents.get(position).getStartDate()+" - "+ allEvents.get(position).getEndDate());
        fromDate = allEvents.get(position).getStartDate();
        toDate =allEvents.get(position).getEndDate();
        
        
        time = (TextView) findViewById(R.id.textView3);
        time.setText(allEvents.get(position).getStartTime()+" - "+allEvents.get(position).getEndTime());
        fromTime= allEvents.get(position).getStartTime();
        toTime = allEvents.get(position).getEndTime();
        
        
        repeat = (TextView) findViewById(R.id.textView5);
        repeat.setText(allEvents.get(position).getRepeat());
        repeatEvent = allEvents.get(position).getRepeat();
        
        category = (TextView) findViewById(R.id.textView7);
        category.setText(SimpleCalendarView.db.getCategoryByEvent(allEvents.get(position).getId()).getName().toString());
        categoryName=SimpleCalendarView.db.getCategoryByEvent(allEvents.get(position).getId()).getName().toString();
        
        status = (TextView) findViewById(R.id.textView9);
        status.setText("Busy");
	}
}
