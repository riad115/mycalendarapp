package com.example.mycalendarapp;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;
// TODO: Auto-generated Javadoc

/**
 * The Class EventList.
 */
public class EventList extends Activity implements OnClickListener{
	
	/** The grid view. */
	private GridView gridView;
	
	/** The adapter. */
	private EventListAdapter adapter;
//	private MySQLiteHelper db;
	/* (non-Javadoc)
 * @see android.app.Activity#onCreate(android.os.Bundle)
 */
public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        //TextView textview = new TextView(this);
        //textview.setText("This is Daily tab");
        setContentView(R.layout.event_list);
        
       // db = new  MySQLiteHelper(getApplicationContext());
        
     // Getting all Events
        Log.d("Get Events", "Getting All Events");
 
        List<Event> allEvents = SimpleCalendarView.db.getAllEvents();
        for (Event event : allEvents) {
            Log.d("Event:"+event.getTitle(),"ID:"+ event.getId()+"Description:"+event.getDescription());
        }
        gridView = (GridView) findViewById(R.id.grid_event);
        
        //((BaseAdapter) gridView.getAdapter()).notifyDataSetChanged(); 
        adapter = new EventListAdapter(this);
        adapter.notifyDataSetChanged();
       gridView.invalidateViews();
        //gridView.setAdapter(new EventListAdapter(this));
       gridView.setAdapter(adapter);
        //gridView.setOnClickListener(this);
        
        gridView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
 
                
                Intent i = new Intent(getApplicationContext(), EventDetailsActivity.class);
                i.putExtra("activity", (int)1);
                i.putExtra("id", position);
                startActivity(i);
            }
        });
        
	}
	
	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		//String title = (String) v.getTag();
	}
	
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	@Override
    public void onResume()
    {
         super.onResume();
         adapter.notifyDataSetChanged();
         gridView.invalidateViews();
          //gridView.setAdapter(new EventListAdapter(this));
         gridView.setAdapter(adapter);
         
    }

}
