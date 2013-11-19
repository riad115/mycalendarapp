package com.example.mycalendarapp;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

// TODO: Auto-generated Javadoc
/**
 * The Class DatePickerActivity for selecting date for adding or updating an event
 */
public class DatePickerActivity extends Activity  implements OnClickListener{
	
	/** The Constant tag. */
	private static final String tag = "Date picker Activity";
	
	/** The ok button. */
	private Button okButton;
	
	/** The cancel button. */
	private Button cancelButton;
    
    /** The dp. */
    private DatePicker dp;

	
    /** The formated date. */
    String formatedDate;
    
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        TextView textview = new TextView(this);
        textview.setText("Select Date");
		setContentView(R.layout.calendar_date_picker);
		
        okButton = (Button) this.findViewById(R.id.okbutton_DatePicker);
        okButton.setOnClickListener(this);
        
        cancelButton = (Button) this.findViewById(R.id.cancelButton_Datepicker);
        cancelButton.setOnClickListener(this);
        

		dp = (DatePicker) findViewById(R.id.datePicker1);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.date_picker, menu);
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
			

			 Date selectedDate = new Date((dp.getYear()-1900), dp.getMonth(), dp.getDayOfMonth());
             SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
             formatedDate = dateFormatter.format(selectedDate);


			 Log.d(tag, " in ok " + formatedDate);
			 if(getIntent().getStringExtra("Caller").equals("To_Date_event"))
			 {
				 EventActivity.to_Date = formatedDate;
				 Log.d(tag, " in to_Date " + EventActivity.to_Date);
			    EventActivity.toTodaysDate.setText(EventActivity.to_Date);
			     finish();
				 
			 }
			 else 	 if(getIntent().getStringExtra("Caller").equals("From_Date_event"))
			 {
				 EventActivity.from_Date = formatedDate;
				 Log.d(tag, " in to_Date " + EventActivity.to_Date);
			     EventActivity.fromTodaysDate.setText(EventActivity.from_Date);
			     finish();
				 
			 }
			 else 	if(getIntent().getStringExtra("Caller").equals("To_Date_edit"))
			 {
				 EditEventActivity.to_Date = formatedDate;
				 Log.d(tag, " in to_Date " + EditEventActivity.to_Date);
				 EditEventActivity.toTodaysDate.setText(EditEventActivity.to_Date);
			     finish();
				 
			 }
			 else 	 if(getIntent().getStringExtra("Caller").equals("From_Date_edit"))
			 {
				 EditEventActivity.from_Date = formatedDate;
				 Log.d(tag, " in to_Date " + EditEventActivity.to_Date);
				 EditEventActivity.fromTodaysDate.setText(EditEventActivity.from_Date);
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
