package com.example.mycalendarapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;
public class EventList extends Activity implements OnClickListener{
	private GridView gridView;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        //TextView textview = new TextView(this);
        //textview.setText("This is Daily tab");
        setContentView(R.layout.event_list);
        gridView = (GridView) findViewById(R.id.grid_event);
        gridView.setAdapter(new EventListAdapter(this));
        
        gridView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
 
                // Sending image id to FullScreenActivity
                Intent i = new Intent(getApplicationContext(), EventDetailsActivity.class);
                // passing array index
                //i.putExtra("id", position);
                startActivity(i);
            }
        });
        
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
