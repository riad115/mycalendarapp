package com.example.mycalendarapp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class EventActivity extends Activity implements OnClickListener{
	private static final String tag = "EventActivity";
	
	private Button toTodaysDate;
	private Button fromTodaysDate;
	
	private Button toCurrentTime;
	private Button fromCurrentTime;
	
	private Button saveButton;
	private Button cancelButton;
	
	private Calendar _calendar;
	private final DateFormat dateFormatter = new DateFormat();
	private static final String dateTemplate = "dd MMMM yyyy";
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        
		_calendar = Calendar.getInstance(Locale.getDefault());
		
        TextView textview = new TextView(this);
        textview.setText("This is Event tab");
        setContentView(R.layout.calendar_event);
        
        toTodaysDate = (Button) this.findViewById(R.id.toTodaysDate);
        toTodaysDate.setText(dateFormatter.format(dateTemplate, _calendar.getTime()));
        toTodaysDate.setOnClickListener(this);
        
        fromTodaysDate = (Button) this.findViewById(R.id.fromTodaysDate);
        fromTodaysDate.setText(dateFormatter.format(dateTemplate, _calendar.getTime()));
        fromTodaysDate.setOnClickListener(this);
        
        toCurrentTime = (Button) this.findViewById(R.id.toCurrentTime);
        toCurrentTime.setText(sdf.format(_calendar.getTime()));
        toCurrentTime.setOnClickListener(this);
        
        fromCurrentTime = (Button) this.findViewById(R.id.fromCurrentTime);
        fromCurrentTime.setText(sdf.format(_calendar.getTime()));
        fromCurrentTime.setOnClickListener(this);
        
        saveButton = (Button) this.findViewById(R.id.fromTodaysDate);
        saveButton.setOnClickListener(this);
        
        cancelButton = (Button) this.findViewById(R.id.fromTodaysDate);
        cancelButton.setOnClickListener(this);
    }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		if(v== toTodaysDate )
		{
			Log.d(tag,"Inside to todays date");
		}
		else 	if(v== fromTodaysDate )
		{
			Log.d(tag,"Inside from todays date");
		}
		else 	if(v== toCurrentTime )
		{
			Log.d(tag,"Inside to current time");
		}
		else 	if(v== fromCurrentTime )
		{
			Log.d(tag,"Inside from current time");
		}
		
		else 	if(v== cancelButton )
		{
			Log.d(tag,"Inside cancel buttun");
		}
		else 	if(v== saveButton )
		{
			Log.d(tag,"Inside save button");
		}
	}

}
