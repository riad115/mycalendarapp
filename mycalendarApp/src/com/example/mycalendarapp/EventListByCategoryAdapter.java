package com.example.mycalendarapp;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class EventListByCategoryAdapter extends BaseAdapter{
	/** The e context. */
	private Context eContext;
	//private MySQLiteHelper db;
	/** The txt event. */
	private TextView[] txtEvent; 
	Category ctg;
	/** The all events. */
	private final List<Event> catEvents;
	
	
	public EventListByCategoryAdapter(Context c, Category ctg){
		eContext =c;
		this.ctg = ctg;
	//	db = new  MySQLiteHelper(eContext);
		
		// Getting all Events
	    Log.d("Get Events", "Getting All Events");

	    catEvents = SimpleCalendarView.db.getAllEventsByCategory(ctg.getName());
	   
	    
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return catEvents.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return catEvents.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		txtEvent = new TextView[catEvents.size()];
	    
	    /*for(int i = 0;i<allEvents.size();i++){
	    	txtEvent[i] = new TextView(eContext);
	    	txtEvent[i].setText(allEvents.get(i).getTitle());
	    	txtEvent[i].setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.MATCH_PARENT, GridView.LayoutParams.WRAP_CONTENT));
	    	}*/
	    
		TextView txt1 = new TextView(eContext);
		txt1.setText(catEvents.get(position).getTitle());
		txt1.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.MATCH_PARENT, GridView.LayoutParams.WRAP_CONTENT));
		txt1.setBackgroundColor(Color.parseColor("#FFFFFF"));
		txt1.setTextColor(Color.parseColor("#000000"));
		txt1.setTag(Long.toString(catEvents.get(position).getId()));
		return txt1;
	}

}
