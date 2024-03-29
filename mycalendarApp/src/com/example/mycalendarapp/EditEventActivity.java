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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

// TODO: Auto-generated Javadoc
/**
 * The Class EditEventActivity for editing an event
 */
public class EditEventActivity extends Activity implements OnClickListener, OnItemSelectedListener{
	
	/** The Constant tag. */
	private static final String tag = "EditActivity";
	
	/** The to_ date. */
	public static String to_Date;
	
	/** The from_ date. */
	public static String from_Date;
	
	/** The to_ time. */
	public static String to_Time;
	
	/** The from_ time. */
	public static String from_Time;
	
	/** The categary_from_ spinner. */
	private static String categary_from_Spinner;
	
	/** The categary_color. */
	private static String categary_color;
	
	/** The all category. */
	private static  List<Category> allCategory;
	
	/** The spinner array. */
	private static ArrayList<String> spinnerArray;
	
	/** The spinn_repeat. */
	private Spinner spinn_repeat;
	
	/** The repeat. */
	private String repeat;
	
	/** The spinner repeat array. */
	private static ArrayList<String> spinnerRepeatArray;

	/** The to todays date. */
	public static Button toTodaysDate;
	
	/** The from todays date. */
	public static Button fromTodaysDate;
	
	/** The to current time. */
	public static Button toCurrentTime;
	
	/** The from current time. */
	public static Button fromCurrentTime;
	
	/** The ctg1_id. */
	public static long ctg1_id;
	
	/** The from dt. */
	private String fromDt;
	
	/** The st time. */
	private String stTime;
	
	/** The e time. */
	private String eTime;
	
	/** The spinn. */
	private Spinner spinn;
	
	/** The save button. */
	private Button saveButton;
	
	/** The cancel button. */
	private Button cancelButton;
	
	/** The edittext_event title. */
	private EditText edittext_eventTitle;
	
	/** The edittext_description. */
	private EditText edittext_description;
	
	/** The category_name. */
	private EditText category_name;
	
	/** The spinn_category_color. */
	private Spinner spinn_category_color;
	
	/** The _calendar. */
	private Calendar _calendar;
	
	/** The date formatter. */
	private final DateFormat dateFormatter = new DateFormat();
	
	/** The Constant dateTemplate. */
	private static final String dateTemplate = "yyyy-MM-dd";
	
	/** The event_1. */
	private Event event_1;
	
	/** The ctg. */
	private static Category ctg;
	
	/** The old_ctg. */
	private static Category old_ctg;

	/** The days of month. */
	private final int[] daysOfMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        
		_calendar = Calendar.getInstance(Locale.getDefault());
		
        TextView textview = new TextView(this);
        textview.setText("This is Event tab");
        setContentView(R.layout.calendar_event);
        


		Log.d(tag,"Inside edit on create: created event ID: "+ EventDetailsActivity.eventID);
		
        event_1 = SimpleCalendarView.db.getEvent(EventDetailsActivity.eventID);
        
     
        toTodaysDate = (Button) this.findViewById(R.id.toTodaysDate);
        toTodaysDate.setText(event_1.getStartDate());
        to_Date = event_1.getStartDate();
        toTodaysDate.setOnClickListener(this);
        fromDt=to_Date;
        
        
        fromTodaysDate = (Button) this.findViewById(R.id.fromTodaysDate);
        fromTodaysDate.setText(event_1.getEndDate());
        from_Date = event_1.getEndDate();
        fromTodaysDate.setOnClickListener(this);
        
        toCurrentTime = (Button) this.findViewById(R.id.toCurrentTime);
        toCurrentTime.setText(event_1.getStartTime());
        to_Time = event_1.getStartTime();
        toCurrentTime.setOnClickListener(this);
        stTime = to_Time;
        
        fromCurrentTime = (Button) this.findViewById(R.id.fromCurrentTime);
        fromCurrentTime.setText(event_1.getEndTime());
        from_Time = event_1.getEndTime();
        fromCurrentTime.setOnClickListener(this);
        eTime = from_Time;
        
        
        saveButton = (Button) this.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);
        saveButton.setText("Edit");
        
        spinn_repeat = (Spinner) findViewById(R.id.spinner_repeat);
        spinnerRepeatArray= new ArrayList<String>();

        repeat=event_1.getRepeat(); 

        
    	spinnerRepeatArray.add(event_1.getRepeat());
    	
        ArrayAdapter spinnerRepeatAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item,
                    spinnerRepeatArray);        
    	spinn_repeat.setAdapter(spinnerRepeatAdapter);
        
        spinn = (Spinner) findViewById(R.id.spinner1);
        spinnerArray = new ArrayList<String>();
        allCategory = SimpleCalendarView.db.getAllCategories();
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
        String cat = SimpleCalendarView.db.getCategoryByEvent(event_1.getId()).getName();
        int index = 0;
        for(String s : spinnerArray)
        {
        	if(s.equals(cat))
        		break;
        	index++;
        }
        spinn.setSelection(index);
       

		Log.d(tag,"Index: " + index + "Cat: "  + cat);
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
	
	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		if(v== toTodaysDate )
		{
			Intent intent = new Intent(this, DatePickerActivity.class);
		    intent.putExtra("Caller", "To_Date_edit");
			startActivityForResult(intent, 0);


			Log.d(tag,"Inside to todays date" );
		}
		else 	if(v== fromTodaysDate )
		{
			Intent intent = new Intent(this, DatePickerActivity.class);
		    intent.putExtra("Caller", "From_Date_edit");
			startActivityForResult(intent, 0);
			
			Log.d(tag,"Inside from todays date");
		}
		else 	if(v== toCurrentTime )
		{
			Intent intent = new Intent(this, TimePickerActivity.class);
		    intent.putExtra("Caller", "To_Time_edit");
			startActivityForResult(intent, 0);


			Log.d(tag,"Inside to current time");
		}
		else 	if(v== fromCurrentTime )
		{
			Intent intent = new Intent(this, TimePickerActivity.class);
		    intent.putExtra("Caller", "From_Time_edit");
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
			event_1.setRepeat(repeat);
//<<<<<<< .mine
	        
			//int update = SimpleCalendarView.db.updateEvent(event_1);
			//Log.d(tag,"Inside edit button: created event ID: "+ update);
//=======
		//	old_ctg = EventActivity.db.getCategoryByEvent(event_1.getId());
			//event_1.

			//Log.d(tag,"Inside save button:before conflict ");
//>>>>>>> .r46
			
			
			
			
	        //event_1.seTCategory
				try {
					
					if(repeat.equals("Repeat-ON")){
						String[] getmonth = to_Date.split("-");
		        		int daysInMonth = daysOfMonth[Integer.parseInt(getmonth[1])-1];
		        		int selectedDate = Integer.parseInt(getmonth[2]);
		        		boolean checkConflict=true;
		        		long id=event_1.getId();
		        		String startDt= to_Date;
		        		Event ev =event_1;
		        		for(int i=selectedDate;i<=daysInMonth;i=i+7){
		        			//Event ev = new Event(edittext_eventTitle.getText().toString(),
		        					//startDt,startDt,to_Time,from_Time,
		        	        		//edittext_description.getText().toString(), repeat);
		     			   		ev.setTitle(event_1.getTitle());
		     			   		ev.setDescription(event_1.getDescription());
		     			   		ev.setStartDate(startDt);
		     			   		ev.setEndDate(startDt);
		     			   		ev.setStartTime(event_1.getStartTime());
		     			   		ev.setEndTime(event_1.getEndTime());
		     			   		ev.setRepeat(event_1.getRepeat());
		        				checkConflict = checkConflict && SimpleCalendarView.db.checkConflictinUpdateEvents(ev);

		        			   String[] getDate = startDt.split("-");
		        			   startDt = getDate[0]+"-"+getDate[1]+"-"+TimePickerActivity.padding_str(Integer.parseInt(getDate[2])+7);
		        			   id=SimpleCalendarView.db.getRepeatEvent(startDt, startDt, stTime, eTime);
		        			   if(id!=0){
		        				   ev = SimpleCalendarView.db.getEvent(id);
		        			   }
		        		}
		        		
		        		ev = event_1;
		        		
		        		if(checkConflict){
		        			String start_Dt = to_Date;
		        			if(EditEventActivity.categary_from_Spinner.equalsIgnoreCase("Add Category"))
		        			{
		 			            ctg = new Category(category_name.getText().toString(), EditEventActivity.categary_color);  
		 			            long ctg1_id = SimpleCalendarView.db.createCategory(ctg);
		 			            ctg.setId(ctg1_id);
		 			        	
		 			        }
		        			Log.d(tag,"Inside  Repeat");
		        			for(int i=selectedDate;i<=daysInMonth;i=i+7){
			        			//Event ev = new Event(edittext_eventTitle.getText().toString(),
			        					//start_Dt,start_Dt,to_Time,from_Time,
			        	        		//edittext_description.getText().toString(), repeat);
		        				ev.setTitle(event_1.getTitle());
		     			   		ev.setDescription(event_1.getDescription());
		     			   		ev.setStartDate(start_Dt);
		     			   		ev.setEndDate(start_Dt);
		     			   		ev.setStartTime(event_1.getStartTime());
		     			   		ev.setEndTime(event_1.getEndTime());
		     			   		ev.setRepeat(event_1.getRepeat());
			 			        
			 					Log.d(tag,"Inside Repeat save button:no conflict " + SimpleCalendarView.db.getCatEvByEvent(ev.getId()));
			 					 int ck = SimpleCalendarView.db.updateEventCategory(SimpleCalendarView.db.getCatEvByEvent(ev.getId()), ctg.getId());
			 					
			 					 //SimpleCalendarView.db.printCATEAVtable();
			 					 
			 					Log.d(tag,"Inside save button: no conflict " + ctg.getName() + ctg.getId() );
			 						
			 					int update = SimpleCalendarView.db.updateEvent(ev);
			 					Log.d(tag,"Inside edit button: category: "+ SimpleCalendarView.db.getCategoryByEvent(ev.getId()).getName()
			 							+ SimpleCalendarView.db.getCategoryByEvent(ev.getId()).getId() );
			 					
			 					//this.finish();
			        			   String[] getDate = start_Dt.split("-");
			        			   start_Dt = getDate[0]+"-"+getDate[1]+"-"+TimePickerActivity.padding_str(Integer.parseInt(getDate[2])+7);
			        			   id=SimpleCalendarView.db.getRepeatEvent(start_Dt, start_Dt, stTime, eTime);
			        			   if(id!=0){
			        				   ev = SimpleCalendarView.db.getEvent(id);
			        			   }
			        		}
		        			
		        			MainCalenadarActivity.tabHost.setCurrentTab(0);
		        			this.finish();
		        		}
		        		
		        		else{
		        			{Log.d(tag,"Inside Repeat save button: conflict ");
							// Creating alert Dialog with one Button
							 
				            AlertDialog alertDialog1 = new AlertDialog.Builder(
				                    this).create();
				 
				            // Setting Dialog Title
				            alertDialog1.setTitle("Alert Dialog");
				 
				            // Setting Dialog Message
				            alertDialog1.setMessage("Repeat Events conflict: Change the time!");
				 
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
		        		}
		        	}
					
					else{
					if(SimpleCalendarView.db.checkConflictinUpdateEvents(event_1))
					{
				        if(categary_from_Spinner.equalsIgnoreCase("Add Category"))
				        {
				            ctg = new Category(category_name.getText().toString(), categary_color);  
				            long ctg1_id = SimpleCalendarView.db.createCategory(ctg);
				            ctg.setId(ctg1_id);
				        	Log.d(tag,"Inside  ");
	
				        	
				        }
				   int ck = SimpleCalendarView.db.updateEventCategory(SimpleCalendarView.db.getCatEvByEvent(event_1.getId()), ctg.getId());
	
					Log.d(tag,"Inside save button: no conflict " + ctg.getName() );
						
					int update = SimpleCalendarView.db.updateEvent(event_1);
					Log.d(tag,"Inside edit button: category: "+ SimpleCalendarView.db.getCategoryByEvent(event_1.getId()).getName());
					
					//Intent list_new = new Intent(this, EventList.class);
					//startActivity(list_new);
	    
					MainCalenadarActivity.tabHost.setCurrentTab(0);
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
					
				}	
				} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
	
			
		}
			
	}
	
	   /* (non-Javadoc)
   	 * @see android.widget.AdapterView.OnItemSelectedListener#onItemSelected(android.widget.AdapterView, android.view.View, int, long)
   	 */
   	public void onItemSelected(AdapterView<?> parent, View view, 
	            int pos, long id) {
	        // An item was selected. You can retrieve the selected item using
	    	int idnum = parent.getId();

	    	if(idnum == R.id.spinner1)
	    	{
	    	categary_from_Spinner =  parent.getItemAtPosition(pos).toString();
	    	Log.d("SpinnerListener :", categary_from_Spinner);
	    	ctg = SimpleCalendarView.db.getCategoryID(categary_from_Spinner); 
	    	
	    	if(categary_from_Spinner.equalsIgnoreCase("Add category"))
	    	{
	            category_name.setVisibility(View.VISIBLE);
	          //  Spinner spinner = (Spinner) findViewById(R.id.spinner2);
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
	        	categary_color =  parent.getItemAtPosition(pos).toString();
	        	Log.d("SpinnerListener2 :", categary_from_Spinner);
	    	}
	    	
	    	else     	if(idnum == R.id.spinner_repeat)
	    	{
	        	repeat =  parent.getItemAtPosition(pos).toString();
	        	Log.d("Repeat Spinner :", repeat);
	    	}
	    	
	    }

	    /* (non-Javadoc)
    	 * @see android.widget.AdapterView.OnItemSelectedListener#onNothingSelected(android.widget.AdapterView)
    	 */
    	public void onNothingSelected(AdapterView<?> parent) {
	        // Another interface callb{ack
	    	if(parent.getId() == R.id.spinner1){
	    	categary_from_Spinner = "Random";
	    	ctg = SimpleCalendarView.db.getCategoryID(categary_from_Spinner); 
	    	}
	    }

}
