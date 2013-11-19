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

// TODO: Auto-generated Javadoc
/**
 * The Class TimePickerActivity for selecting time for adding or updating event
 */
public class TimePickerActivity extends Activity implements OnClickListener {
	
	/** The Constant tag. */
	private static final String tag = "Date picker Activity";
	
	/** The ok button. */
	private Button okButton;
	
	/** The cancel button. */
	private Button cancelButton;
    
    /** The tp. */
    private TimePicker tp;

    /** The min. */
    int min;
    
    /** The hour. */
    int hour;
	
    /** The formated time. */
    String formatedTime;

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
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

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.time_picker, menu);
		return true;
	}
	
	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		 
		if(v== okButton )
		{
			min = tp.getCurrentMinute();
			hour = tp.getCurrentHour();
			
			formatedTime = padding_str(hour) + ":" + padding_str(min);
			
			
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
	
	/**
	 * Padding_str.
	 *
	 * @param c the c
	 * @return the padded string
	 */
	public static String padding_str(int c) {
	        if (c >= 10){
		           return String.valueOf(c);
	        }
	        else{
			           return "0" + String.valueOf(c);
	        }
			    
	}

}
