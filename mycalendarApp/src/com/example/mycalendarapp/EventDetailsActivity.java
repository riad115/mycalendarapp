package com.example.mycalendarapp;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class EventDetailsActivity extends Activity implements OnClickListener{
	private EventListAdapter listAdapter;
	//private MySQLiteHelper db;
	private TextView[] txtEvent; 
	private List<Event> allEvents;
	private Event selEvent;
	private String eventTitle;
	public static long eventID;
	private int position;
	private TextView title;
	private TextView description;
	private TextView date;
	private TextView time;
	private TextView repeat;
	private TextView category;
	private TextView status;
	private long Id;
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
        category.setText("Random");
        
        status = (TextView) findViewById(R.id.textView9);
        status.setText("Busy");*/
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.event_details, menu);
		return true;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    case R.id.delete_event:
	        EventActivity.db.deleteEvent(eventID);
	        Intent del = new Intent(this, EventList.class);
	        startActivity(del);
	        return true;
	    
	    case R.id.edit_event:
			Intent intent = new Intent(this, EditEventActivity.class);
			startActivity(intent);
			

			return true;
			
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}
	
	
	public void printEventDetails(Long id){
		Log.d("Get ID", id.toString());
		selEvent = EventActivity.db.getEvent(id);
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
        
        time = (TextView) findViewById(R.id.textView3);
        time.setText(selEvent.getStartTime()+" - "+selEvent.getEndTime());
        
        repeat = (TextView) findViewById(R.id.textView5);
        repeat.setText("No");
        
        category = (TextView) findViewById(R.id.textView7);
        category.setText("Random");
        
        status = (TextView) findViewById(R.id.textView9);
        status.setText("Busy");
	}
	
	
	public void printEventDetails(Integer id){
		Log.d("Get Events", "Getting All Events");

	    allEvents = EventActivity.db.getAllEvents();
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
        
        time = (TextView) findViewById(R.id.textView3);
        time.setText(allEvents.get(position).getStartTime()+" - "+allEvents.get(position).getEndTime());
        
        repeat = (TextView) findViewById(R.id.textView5);
        repeat.setText("No");
        
        category = (TextView) findViewById(R.id.textView7);
        category.setText("Random");
        
        status = (TextView) findViewById(R.id.textView9);
        status.setText("Busy");
	}
}
