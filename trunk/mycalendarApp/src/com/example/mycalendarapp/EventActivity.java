package com.example.mycalendarapp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class EventActivity extends Activity implements OnClickListener{
	private static final String tag = "EventActivity";
	public static String to_Date;
	public static String from_Date;
	public static String to_Time;
	public static String from_Time;
	
	 private String date;
	// private String to_date;
	public static Button toTodaysDate;
	public static Button fromTodaysDate;
	
	public static Button toCurrentTime;
	public static Button fromCurrentTime;
	
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
        
        saveButton = (Button) this.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);
        
        cancelButton = (Button) this.findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(this);
    }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		if(v== toTodaysDate )
		{
			Intent intent = new Intent(this, DatePickerActivity.class);

		    intent.putExtra("Caller", "To_Date");
			startActivityForResult(intent, 0);


			Log.d(tag,"Inside to todays date" );
		}
		else 	if(v== fromTodaysDate )
		{
			Intent intent = new Intent(this, DatePickerActivity.class);

		    intent.putExtra("Caller", "From_Date");
			startActivityForResult(intent, 0);
			
			Log.d(tag,"Inside from todays date");
		}
		else 	if(v== toCurrentTime )
		{
			Intent intent = new Intent(this, TimePickerActivity.class);

		    intent.putExtra("Caller", "To_Time");
			startActivityForResult(intent, 0);


			Log.d(tag,"Inside to current time");
		}
		else 	if(v== fromCurrentTime )
		{
			Intent intent = new Intent(this, TimePickerActivity.class);

		    intent.putExtra("Caller", "From_Time");
			startActivityForResult(intent, 0);
			
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
	/* Called when the second activity's finished */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		 Log.d(tag, " in parent " + to_Date);
	}

}
