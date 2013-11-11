package com.example.mycalendarapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.TextView;

public class EventActivity extends Activity implements OnClickListener, OnItemSelectedListener {
	private static final String tag = "EventActivity";
	public static String to_Date;
	public static String from_Date;
	public static String to_Time;
	public static String from_Time;
	private static String categary_from_Spinner;
	private static String categary_color;
	private static  List<Category> allCategory;
	private static ArrayList<String> spinnerArray;
	
	private Spinner spinn_repeat;
	private String repeat;
	
	public static Button toTodaysDate;
	public static Button fromTodaysDate;
	
	public static Button toCurrentTime;
	public static Button fromCurrentTime;
	
	private static Category ctg;
	public static Event event2; 
	
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
	
	///////////////////////////////////////////////////////////RossY Start
	
	 public static MySQLiteHelper db;
	 
	////////////////////////////////////////////////////////// RossY End
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        
		_calendar = Calendar.getInstance(Locale.getDefault());
		
        TextView textview = new TextView(this);
        textview.setText("This is Event tab");
        setContentView(R.layout.calendar_event);
        
        //////////////////////////////////////////////////////////////////////////////////// RossY Start
        

        db = new  MySQLiteHelper(getApplicationContext());
           
       // db.clearDatebase();
        db.closeDB();
        
        
        ////////////////////////////////////////////////////////////////////////////////////// RossY End
        
        toTodaysDate = (Button) this.findViewById(R.id.toTodaysDate);
        toTodaysDate.setText(dateFormatter.format(dateTemplate, _calendar.getTime()));
        to_Date = dateFormatter.format(dateTemplate, _calendar.getTime()).toString();
        toTodaysDate.setOnClickListener(this);
        
        fromTodaysDate = (Button) this.findViewById(R.id.fromTodaysDate);
        fromTodaysDate.setText(dateFormatter.format(dateTemplate, _calendar.getTime()));
        from_Date = dateFormatter.format(dateTemplate, _calendar.getTime()).toString();
        fromTodaysDate.setOnClickListener(this);
        
        toCurrentTime = (Button) this.findViewById(R.id.toCurrentTime);
        toCurrentTime.setText(sdf.format(_calendar.getTime()));
        to_Time = sdf.format(_calendar.getTime()).toString();
        toCurrentTime.setOnClickListener(this);
        
        fromCurrentTime = (Button) this.findViewById(R.id.fromCurrentTime);
        fromCurrentTime.setText(sdf.format(_calendar.getTime()));
        from_Time = sdf.format(_calendar.getTime()).toString();
        fromCurrentTime.setOnClickListener(this);
        
        spinn_repeat = (Spinner) findViewById(R.id.spinner_repeat);
        
       ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.repeat, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinn_repeat.setAdapter(adapter);
        spinn_repeat.setOnItemSelectedListener(this);
        
        
        spinn = (Spinner) findViewById(R.id.spinner1);
        
        allCategory = db.getAllCategories();
        spinnerArray = new ArrayList<String>();
        for (Category category : allCategory) {
            //Log.d("Category Name:"+category.getName(), "ID:"+category.getId()+"Color:"+category.getColor());
        	spinnerArray.add(category.getName());
        }
    	spinnerArray.add("Add Category");
        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item,
                    spinnerArray);

     // Specify the layout to use when the list of choices appears
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinn.setAdapter(spinnerArrayAdapter);
        spinn.setOnItemSelectedListener(this);
        
        saveButton = (Button) this.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);
        
        cancelButton = (Button) this.findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(this);
        
        edittext_eventTitle = (EditText) this.findViewById(R.id.editText1);
        edittext_description = (EditText) this.findViewById(R.id.editText6);
        
        category_name = (EditText) this.findViewById(R.id.editText12);
        category_name.setVisibility(View.INVISIBLE);
        category_name.setText("Name");
        spinn_category_color = (Spinner) this.findViewById(R.id.spinner2);
        spinn_category_color.setVisibility(View.INVISIBLE);
        spinn_category_color.setOnItemSelectedListener(this);
       // category_color.setText("Color");
    }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		if(v== toTodaysDate )
		{
			Intent intent = new Intent(this, DatePickerActivity.class);
		    intent.putExtra("Caller", "To_Date_event");
			startActivityForResult(intent, 0);


			Log.d(tag,"Inside to todays date" );
		}
		else 	if(v== fromTodaysDate )
		{
			Intent intent = new Intent(this, DatePickerActivity.class);
		    intent.putExtra("Caller", "From_Date_event");
			startActivityForResult(intent, 0);
			
			Log.d(tag,"Inside from todays date");
		}
		else 	if(v== toCurrentTime )
		{
			Intent intent = new Intent(this, TimePickerActivity.class);
		    intent.putExtra("Caller", "To_Time_event");
			startActivityForResult(intent, 0);


			Log.d(tag,"Inside to current time");
		}
		else 	if(v== fromCurrentTime )
		{
			Intent intent = new Intent(this, TimePickerActivity.class);
		    intent.putExtra("Caller", "From_Time_event");
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
	        Event event_new = new Event(edittext_eventTitle.getText().toString(),
	        		to_Date,from_Date,to_Time,from_Time,
	        		edittext_description.getText().toString());

	        try {
				if(EventActivity.db.checkConflictinEvents(event_new))
				{
			        if(EventActivity.categary_from_Spinner.equalsIgnoreCase("Add Category"))
			        {
			            ctg = new Category(category_name.getText().toString(), EventActivity.categary_color);  
			            long ctg1_id = db.createCategory(ctg);
			            ctg.setId(ctg1_id);
			        	
			        }
					Log.d(tag,"Inside save button:no conflict ");
					long event_current_id = db.createEvent(event_new, new long[] { ctg.getId() });
					
					this.finish();
				}
				else
					{Log.d(tag,"Inside save button: conflict ");
					// Creating alert Dialog with one Button
					 
		            AlertDialog alertDialog1 = new AlertDialog.Builder(
		                    this).create();
		 
		            // Setting Dialog Title
		            alertDialog1.setTitle("Alert Dialog");
		 
		            // Setting Dialog Message
		            alertDialog1.setMessage("Events conflict: Change the time!");
		 
		            // Setting Icon to Dialog
		        //    alertDialog1.setIcon(R.drawable.tick);
		 
		            // Setting OK Button
		            alertDialog1.setButton("OK", new DialogInterface.OnClickListener() {
		 
		                public void onClick(DialogInterface dialog, int which) {
		                    // Write your code here to execute after dialog
		                    // closed
		                	//alertDialog1.
		                }
		            });
		            alertDialog1.show();
					}
					
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
			//Log.d(tag,"Inside save button: created event ID: "+ event_current_id);

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
    	ctg = db.getCategoryID(EventActivity.categary_from_Spinner); 
    	
    	if(EventActivity.categary_from_Spinner.equalsIgnoreCase("Add category"))
    	{
            category_name.setVisibility(View.VISIBLE);
         //   Spinner spinner = (Spinner) findViewById(R.id.spinner2);
         // Create an ArrayAdapter using the string array and a default spinner layout
         ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                 R.array.categary_color, android.R.layout.simple_spinner_item);
         // Specify the layout to use when the list of choices appears
         adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         // Apply the adapter to the spinner
         	spinn_category_color.setAdapter(adapter);
         	spinn_category_color.setVisibility(View.VISIBLE);
            
    	}
    	}
    	else     	if(idnum == R.id.spinner2)
    	{
        	EventActivity.categary_color =  parent.getItemAtPosition(pos).toString();
        	Log.d("SpinnerListener2 :", EventActivity.categary_color);
    	}
    	else     	if(idnum == R.id.spinner_repeat)
    	{
        	repeat =  parent.getItemAtPosition(pos).toString();
        	Log.d("Repeat Spinner :", repeat);
    	}
    	
    	
    }

    public void onNothingSelected(AdapterView<?> parent) {
  
    	if(parent.getId() == R.id.spinner1){
    	EventActivity.categary_from_Spinner = "Random";
    	ctg = db.getCategoryID(EventActivity.categary_from_Spinner); 
    	}
    }
    
    
}