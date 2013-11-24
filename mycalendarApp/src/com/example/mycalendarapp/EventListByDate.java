package com.example.mycalendarapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.view.View;

import android.widget.AdapterView.OnItemClickListener;

public class EventListByDate extends Activity implements android.view.View.OnClickListener {
	
	private GridView gridView;
	
	/** The to todays date. */
	public static Button toTodaysDate;
	
	/** The from todays date. */
	public static Button fromTodaysDate;
	
	/** The to_ date. */
	public static String to_Date;
	
	/** The from_ date. */
	public static String from_Date;
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private Calendar _calendar;
	private Date startDate;
	private Date endDate;
	/** The adapter. */
	private EventListByDateAdapter adapter;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        //TextView textview = new TextView(this);
        //textview.setText("This is Daily tab");
        setContentView(R.layout.calendar_search_date);
        
       // db = new  MySQLiteHelper(getApplicationContext());
        _calendar = Calendar.getInstance(Locale.getDefault());
        toTodaysDate = (Button) this.findViewById(R.id.toTodaysDate);
        toTodaysDate.setText(dateFormat.format(_calendar.getTime()));
        to_Date = dateFormat.format(_calendar.getTime()).toString();
        toTodaysDate.setOnClickListener(this);
        
        fromTodaysDate = (Button) this.findViewById(R.id.fromTodaysDate);
        fromTodaysDate.setText(dateFormat.format(_calendar.getTime()));
        from_Date = dateFormat.format(_calendar.getTime()).toString();
        fromTodaysDate.setOnClickListener(this);
        
        gridView = (GridView) findViewById(R.id.grid_search_date);
        
        adapter = new EventListByDateAdapter(getApplicationContext(), from_Date, to_Date);
    	adapter.notifyDataSetChanged();
        gridView.invalidateViews();
         //gridView.setAdapter(new EventListAdapter(this));
        gridView.setAdapter(adapter);
         //gridView.setOnClickListener(this);
         
         gridView.setOnItemClickListener(new OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
  
            	 Long clickedID = Long.parseLong((String)v.getTag());
                 Intent i = new Intent(getApplicationContext(), EventDetailsActivity.class);
                 i.putExtra("activity", (int)12);
                 i.putExtra("id", clickedID);
                 startActivity(i);
             }
         });
      
        
	}
	
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v== toTodaysDate )
		{
			Intent intent = new Intent(this, DatePickerActivity.class);
		    intent.putExtra("Caller", "To_Date_search");
			startActivityForResult(intent, 0);
			
			

			/*Log.d("EventListByDate","Inside to todays date" );
			
			
			adapter = new EventListByDateAdapter(getApplicationContext(), from_Date, to_Date);
	    	adapter.notifyDataSetChanged();
	        gridView.invalidateViews();
	         //gridView.setAdapter(new EventListAdapter(this));
	        gridView.setAdapter(adapter);
	         //gridView.setOnClickListener(this);
	         
	         gridView.setOnItemClickListener(new OnItemClickListener() {
	             @Override
	             public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	  
	            	 Long clickedID = Long.parseLong((String)v.getTag());
	                 Intent i = new Intent(getApplicationContext(), EventDetailsActivity.class);
	                 i.putExtra("activity", (int)12);
	                 i.putExtra("id", clickedID);
	                 startActivity(i);
	             }
	         });*/
		}
		else 	if(v== fromTodaysDate )
		{
			Intent intent = new Intent(this, DatePickerActivity.class);
		    intent.putExtra("Caller", "From_Date_search");
			startActivityForResult(intent, 0);
			
			/*Log.d("EveEventListByDate","Inside from todays date");
			
			
			adapter = new EventListByDateAdapter(getApplicationContext(), from_Date, to_Date);
	    	adapter.notifyDataSetChanged();
	        gridView.invalidateViews();
	         //gridView.setAdapter(new EventListAdapter(this));
	        gridView.setAdapter(adapter);
	         //gridView.setOnClickListener(this);
	         
	         gridView.setOnItemClickListener(new OnItemClickListener() {
	             @Override
	             public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	  
	            	 Long clickedID = Long.parseLong((String)v.getTag());
	                 Intent i = new Intent(getApplicationContext(), EventDetailsActivity.class);
	                 i.putExtra("activity", (int)12);
	                 i.putExtra("id", clickedID);
	                 startActivity(i);
	             }
	         });*/
		}
		
	}
	
	@Override
		    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		        // TODO Auto-generated method stub
		        super.onActivityResult(requestCode, resultCode, data);
		        if (requestCode == 0 && resultCode == RESULT_OK) {
					//Display the modified values
		        	Log.d("EventListByDate","Inside to todays date" );
					
					try {
						startDate = dateFormat.parse(from_Date);
						endDate  = dateFormat.parse(to_Date);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
		        	if(startDate.compareTo(endDate)>0){
		        		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
			    				this);
			     
			    			// set title
			    			alertDialogBuilder.setTitle("Date Selection Error");
			     
			    			// set dialog message
			    			alertDialogBuilder
			    				.setMessage("Start Date must be less than or equal to End Date")
			    				.setCancelable(false)
			    				
			    				.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
			    					public void onClick(DialogInterface dialog,int id) {
			    						// if this button is clicked, just close
			    						// the dialog box and do nothing
			    						dialog.cancel();
			    						
			    					}
			    				});
			     
			    				// create alert dialog
			    				AlertDialog alertDialog = alertDialogBuilder.create();
			     
			    				// show it
			    				alertDialog.show();
		        	}
		        	
		        	else{
					adapter = new EventListByDateAdapter(getApplicationContext(), from_Date, to_Date);
			    	adapter.notifyDataSetChanged();
			        gridView.invalidateViews();
			         //gridView.setAdapter(new EventListAdapter(this));
			        gridView.setAdapter(adapter);
			         //gridView.setOnClickListener(this);
			         
			         gridView.setOnItemClickListener(new OnItemClickListener() {
			             @Override
			             public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
			  
			            	 Long clickedID = Long.parseLong((String)v.getTag());
			                 Intent i = new Intent(getApplicationContext(), EventDetailsActivity.class);
			                 i.putExtra("activity", (int)12);
			                 i.putExtra("id", clickedID);
			                 startActivity(i);
			                 //EventListByDate.this.finish();
			             }
			         });
			         
		        	}
		        }
		    }
}
