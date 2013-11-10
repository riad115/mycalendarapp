package com.example.mycalendarapp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class EditEventActivity extends Activity implements OnClickListener, OnItemSelectedListener{
	private static final String tag = "EditActivity";
	public static String to_Date;
	public static String from_Date;
	public static String to_Time;
	public static String from_Time;
	
	public static String categary_from_Spinner;
	public static String categary_color;
	public static  List<Category> allCategory;
	public static ArrayList<String> spinnerArray;
	

	public static Button toTodaysDate;
	public static Button fromTodaysDate;
	
	public static Button toCurrentTime;
	public static Button fromCurrentTime;
	
	public static long ctg1_id;
	
	
	private Spinner spinn;
	private Button saveButton;
	private Button cancelButton;
	
	private EditText edittext_eventTitle;
	private EditText edittext_description;
	
	private EditText category_name;
	private Spinner spinn_category_color;
	
	private Calendar _calendar;
	private final DateFormat dateFormatter = new DateFormat();
	private static final String dateTemplate = "yyyy-MM-dd";
	
	private Event event_1;
	public static Category ctg;
	private long categary;
	

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        
		_calendar = Calendar.getInstance(Locale.getDefault());
		
        TextView textview = new TextView(this);
        textview.setText("This is Event tab");
        setContentView(R.layout.calendar_event);
        


		Log.d(tag,"Inside edit on create: created event ID: "+ EventDetailsActivity.eventID);
		
        event_1 = EventActivity.db.getEvent(EventDetailsActivity.eventID);
        
     
        toTodaysDate = (Button) this.findViewById(R.id.toTodaysDate);
        toTodaysDate.setText(event_1.getStartDate());
        to_Date = event_1.getStartDate();
        toTodaysDate.setOnClickListener(this);
        
        fromTodaysDate = (Button) this.findViewById(R.id.fromTodaysDate);
        fromTodaysDate.setText(event_1.getEndDate());
        from_Date = event_1.getEndDate();
        fromTodaysDate.setOnClickListener(this);
        
        toCurrentTime = (Button) this.findViewById(R.id.toCurrentTime);
        toCurrentTime.setText(event_1.getStartTime());
        to_Time = event_1.getStartTime();
        toCurrentTime.setOnClickListener(this);
        
        fromCurrentTime = (Button) this.findViewById(R.id.fromCurrentTime);
        fromCurrentTime.setText(event_1.getEndTime());
        from_Time = event_1.getEndTime();
        fromCurrentTime.setOnClickListener(this);
        
        saveButton = (Button) this.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);
        saveButton.setText("Edit");
        
        spinn = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item,
                    EventActivity.spinnerArray);
        
     // Specify the layout to use when the list of choices appears
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinn.setAdapter(spinnerArrayAdapter);
        
        spinn.setOnItemSelectedListener(this);
        
        cancelButton = (Button) this.findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(this);
        
        edittext_eventTitle = (EditText) this.findViewById(R.id.editText1);
        edittext_eventTitle.setText(event_1.getTitle());
        
        edittext_description = (EditText) this.findViewById(R.id.editText6);
        edittext_description.setText(event_1.getDescription());
        
        category_name = (EditText) this.findViewById(R.id.editText12);
        category_name.setVisibility(View.INVISIBLE);
        category_name.setText("Name");
        spinn_category_color = (Spinner) this.findViewById(R.id.spinner2);
        spinn_category_color.setVisibility(View.INVISIBLE);
        spinn_category_color.setOnItemSelectedListener(this);
        
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

			this.finish();
			Log.d(tag,"Inside cancel buttun");
		}
		else 	if(v== saveButton )
		{
			event_1.setTitle(edittext_eventTitle.getText().toString());
			event_1.setDescription(edittext_description.getText().toString());
			
			event_1.setStartDate(to_Date);
			event_1.setStartTime(to_Time);
			event_1.setEndDate(from_Date);
			event_1.setEndTime(from_Time);
	        if(EventActivity.categary_from_Spinner.equalsIgnoreCase("Add Category"))
	        {
	            ctg = new Category(category_name.getText().toString(), EventActivity.categary_color);  
	            long ctg1_id = EventActivity.db.createCategory(ctg);
	            ctg.setId(ctg1_id);
	        	
	        }
	      //  event_1.seTCategory
			int update = EventActivity.db.updateEvent(event_1);
			Log.d(tag,"Inside edit button: created event ID: "+ update);
			
	        Intent list_new = new Intent(this, EventList.class);
	        startActivity(list_new);
	        
			
			this.finish();
		}
	}
	
	   public void onItemSelected(AdapterView<?> parent, View view, 
	            int pos, long id) {
	        // An item was selected. You can retrieve the selected item using
	    	int idnum = parent.getId();

	    	if(idnum == R.id.spinner1)
	    	{
	    	EventActivity.categary_from_Spinner =  parent.getItemAtPosition(pos).toString();
	    	Log.d("SpinnerListener :", EventActivity.categary_from_Spinner);
	    	ctg = EventActivity.db.getCategoryID(EventActivity.categary_from_Spinner); 
	    	
	    	if(EventActivity.categary_from_Spinner.equalsIgnoreCase("Add category"))
	    	{
	            category_name.setVisibility(View.VISIBLE);
	            Spinner spinner = (Spinner) findViewById(R.id.spinner2);
	         // Create an ArrayAdapter using the string array and a default spinner layout
	         ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
	                 R.array.categary_color, android.R.layout.simple_spinner_item);
	         // Specify the layout to use when the list of choices appears
	         adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	         // Apply the adapter to the spinner
	         	spinner.setAdapter(adapter);
	         	spinn_category_color.setVisibility(View.VISIBLE);
	            
	    	}
	    	}
	    	else     	if(idnum == R.id.spinner2)
	    	{
	        	EventActivity.categary_color =  parent.getItemAtPosition(pos).toString();
	        	Log.d("SpinnerListener2 :", EventActivity.categary_from_Spinner);
	    	}
	    	
	    	
	    }

	    public void onNothingSelected(AdapterView<?> parent) {
	        // Another interface callb{ack
	    	if(parent.getId() == R.id.spinner1){
	    	EventActivity.categary_from_Spinner = "Random";
	    	ctg = EventActivity.db.getCategoryID(EventActivity.categary_from_Spinner); 
	    	}
	    }

}