package com.example.mycalendarapp;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;
public class EventList extends Activity implements OnClickListener{
	private GridView gridView;
//	private MySQLiteHelper db;
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
        gridView.setAdapter(new EventListAdapter(this));
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
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		//String title = (String) v.getTag();
	}

}
