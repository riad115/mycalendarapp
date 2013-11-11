package com.example.mycalendarapp;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

public class TimePickerActivity extends Activity implements OnClickListener {
	private static final String tag = "Date picker Activity";
	
	private Button okButton;
	private Button cancelButton;
    private TimePicker tp;

    int min;
    int hour;
	
    String formatedTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calendar_time_picker);
		
		
        okButton = (Button) this.findViewById(R.id.okbutton_TimePicker);
        okButton.setOnClickListener(this);
        
        cancelButton = (Button) this.findViewById(R.id.cancel_timepicker);
        cancelButton.setOnClickListener(this);
        

		tp = (TimePicker) findViewById(R.id.timePicker);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.time_picker, menu);
		return true;
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		 
		if(v== okButton )
		{
			min = tp.getCurrentMinute();
			hour = tp.getCurrentHour();
			
			formatedTime = hour + ":" + min;
			
			
			 Log.d(tag, " in ok " + formatedTime);
			 if(getIntent().getStringExtra("Caller").equals("To_Time_event"))
			 {
				 EventActivity.to_Time = formatedTime;
				 Log.d(tag, " in to_time " + EventActivity.to_Time);
			     EventActivity.toCurrentTime.setText(EventActivity.to_Time);
			     finish();
				 
			 }
			 else 	 if(getIntent().getStringExtra("Caller").equals("From_Time_event"))
			 {
				 EventActivity.from_Time = formatedTime;
				 Log.d(tag, " in to_time " + EventActivity.from_Time);
			     EventActivity.fromCurrentTime.setText(EventActivity.from_Time);
			     finish();
				 
			 }
			 else 	if(getIntent().getStringExtra("Caller").equals("To_Time_edit"))
			 {
				 EditEventActivity.to_Time = formatedTime;
				 Log.d(tag, " in to_time " + EditEventActivity.to_Time);
				 EditEventActivity.toCurrentTime.setText(EditEventActivity.to_Time);
			     finish();
				 
			 }
			 else 	 if(getIntent().getStringExtra("Caller").equals("From_Time_edit"))
			 {
				 EditEventActivity.from_Time = formatedTime;
				 Log.d(tag, " in to_time " + EditEventActivity.from_Time);
				 EditEventActivity.fromCurrentTime.setText(EditEventActivity.from_Time);
			     finish();
				 
			 }
			
		}
		
		else if (v== cancelButton )
		{

			this.finish();
			Log.d(tag,"Inside Cancel Date Picker");
		} 
		
	}

}
