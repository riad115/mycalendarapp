package com.example.mycalendarapp;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class EventListAdapter extends BaseAdapter{
	
	private Context eContext;
	//private MySQLiteHelper db;
	private TextView[] txtEvent; 
	private final List<Event> allEvents;
	
	
	static final String[] numbers = new String[] { 
			"A", "B", "C", "D", "E",
			"F", "G", "H", "I", "J",
			"K", "L", "M", "N", "O",
			"P", "Q", "R", "S", "T",
			"U", "V", "W", "X", "Y", "Z"};
	
	
	public EventListAdapter (Context c){
		eContext =c;
	//	db = new  MySQLiteHelper(eContext);
		
		// Getting all Events
	    Log.d("Get Events", "Getting All Events");

	    allEvents = SimpleCalendarView.db.getAllEvents();
	    for (Event event : allEvents) {
	        Log.d("Event:"+event.getTitle(),"ID:"+ event.getId()+"Description:"+event.getDescription());
	    }
	    
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return allEvents.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return allEvents.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
	    
	    txtEvent = new TextView[allEvents.size()];
	    
	    /*for(int i = 0;i<allEvents.size();i++){
	    	txtEvent[i] = new TextView(eContext);
	    	txtEvent[i].setText(allEvents.get(i).getTitle());
	    	txtEvent[i].setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.MATCH_PARENT, GridView.LayoutParams.WRAP_CONTENT));
	    	}*/
	    
		TextView txt1 = new TextView(eContext);
		txt1.setText(allEvents.get(position).getTitle());
		txt1.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.MATCH_PARENT, GridView.LayoutParams.WRAP_CONTENT));
		return txt1;
	}

}
