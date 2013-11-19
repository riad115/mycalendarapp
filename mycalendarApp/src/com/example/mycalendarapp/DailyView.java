package com.example.mycalendarapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

// TODO: Auto-generated Javadoc
/**
 * The Class DailyView.
 */
public class DailyView extends Activity implements OnClickListener{
	
	/** The Constant tag. */
	private static final String tag = "DailyView";
	
	/** The _calendar. */
	private Calendar _calendar;
	
	/** The current month. */
	private Button currentMonth;
	
	/** The prev month. */
	private ImageView prevMonth;
	
	/** The next month. */
	private ImageView nextMonth;
	
	/** The current date relative layout. */
	private RelativeLayout currentDateRelativeLayout;
	
	/** The layout inflater. */
	private LayoutInflater layoutInflater;
	
	/** The stub. */
	private ViewStub stub;
	
	/** The txt1. */
	private TextView txt1;
	
	/** The dailytxt. */
	private TextView[] dailytxt;
	
	/** The week. */
	private int month, year , week;
	
	/** The i. */
	private int i =0;
	
	/** The j. */
	private int j =0;
	
	/** The min. */
	private int hour, hourdp, min;
	
	/** The id. */
	private Long id;
	
	/** The current date. */
	private String currentDate;
	
	/** The dt. */
	private Date dt = null;
	
	/** The daily event. */
	private List<Event> dailyEvent;
	//private MySQLiteHelper db;
	/** The date format. */
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
	
	/** The date format s ql. */
	private final SimpleDateFormat dateFormatSQl = new SimpleDateFormat("yyyy-MM-dd");
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        //TextView textview = new TextView(this);
        //textview.setText("This is Daily tab");
        setContentView(R.layout.calendar_day_view);
        //db = new  MySQLiteHelper(getApplicationContext());
        _calendar = Calendar.getInstance(Locale.getDefault());
		month = _calendar.get(Calendar.MONTH) + 1;
		year = _calendar.get(Calendar.YEAR);
		week = _calendar.get(Calendar.WEEK_OF_YEAR);
		hour = _calendar.get(Calendar.HOUR_OF_DAY);
		min = _calendar.get(Calendar.MINUTE);
		Log.d(tag, "Calendar Instance:= " + "Month: " + month + " " + "Year: " + year);
		
		currentMonth = (Button) this.findViewById(R.id.currentMonth);
		//currentMonth.setText(dateFormat.format(_calendar.getTime()));
		
		prevMonth = (ImageView) this.findViewById(R.id.prevMonth);
		prevMonth.setOnClickListener(this);
		
		nextMonth = (ImageView) this.findViewById(R.id.nextMonth);
		nextMonth.setOnClickListener(this);
		currentDateRelativeLayout = (RelativeLayout) findViewById(R.id.currentDateRelativeLayout);
		layoutInflater = (LayoutInflater) 
		        this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);    
		
		//currentDateRelativeLayout.removeAllViews();
		
		currentDate = dateFormatSQl.format(_calendar.getTime()).toString();
		
		 Log.d(tag, "Getting All Events for a Specific date : "+currentDate);
		
		
		hourdp = ((hour-1) * 120) + (min * 2);
		LinearLayout currentTimeMarkerLinearLayout1 = (LinearLayout) findViewById(R.id.currentTimeMarkerLinearLayout);
		//currentTimeMarkerLinearLayout.setId(100);
		//currentTimeMarkerLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
		android.widget.RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams)currentTimeMarkerLinearLayout1.getLayoutParams();
		layoutParams2.setMargins(0, hourdp, 0, 0);
		
		
		Bundle extras = getIntent().getExtras();
        // Selected image id
        if(extras!=null){
        	int activity = extras.getInt("activity");
        	if(activity ==1){
        		
        		currentDate = extras.getString("Date");
        		
				/*try {
					dt = new SimpleDateFormat("dd-MMM-yyyy",Locale.ENGLISH).parse(currentDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
        		try {
					currentMonth.setText(dateFormat.format(dateFormatSQl.parse(currentDate)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		printDailyEvent(currentDate);
        	}
        	
        	
        }
        
        else{
        	currentMonth.setText(dateFormat.format(_calendar.getTime()));
        	printDailyEvent(currentDate);
        }
		
    }
	
	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		Calendar first;
		//TextView tx=(TextView) v;
		first = (Calendar) _calendar.clone();
		if (v == prevMonth)
		{
			if(j>0){
				j =j-1;
				i=0;
				first.add(Calendar.DATE, j); 
			}
			else{
				i=i+1;
				first.add(Calendar.DATE, -i); 
			}
			
			
			
			currentMonth.setText(dateFormat.format(first.getTime()));
			  currentDate = dateFormatSQl.format(first.getTime());
			//Intent i = new Intent(getApplicationContext(), DailyView.class);
            ///i.putExtra("activity", (int)1);
            //i.putExtra("Date", currentDate);
            //finish();
            //startActivity(i);
			printDailyEvent(currentDate);
			Log.d(tag, "Setting Prev Date in GridCellAdapter: " + first.getTime());
			
		}
	if (v == nextMonth)
		{
		
		if(i>0){
			i =i-1;
			j=0;
			first.add(Calendar.DATE, -i); 
		}
		else{
			j = j+1;
			first.add(Calendar.DATE, j); 
		}
		  //i++;
		  //first.add(Calendar.DATE, i);  
		  currentMonth.setText(dateFormat.format(first.getTime()));
		  currentDate = dateFormatSQl.format(first.getTime());
		  //Intent i = new Intent(getApplicationContext(), DailyView.class);
          //i.putExtra("activity", (int)1);
          //i.putExtra("Date", currentDate);
          //finish();
          //startActivity(i);
		  printDailyEvent(currentDate);
		  Log.d(tag, "Setting Next Date in GridCellAdapter: " + first.getTime());
			
		}
		//for(int txt=0;txt<dailyEvent.size();txt++){
			//if(v==tx){
			//Intent ev= new Intent(this, EventDetailsActivity.class);
            //startActivity(ev);      
            //finish();
			//}
		//}
	
	}
	
	/**
	 * Prints the daily event.
	 *
	 * @param date the date
	 */
	public void printDailyEvent(String date){
		int txtView = 0;
		String[] startTime;
		String[] endTime;
		String color;
		int start;
		int end;
		int top;
		currentDateRelativeLayout.removeAllViews();
		currentDateRelativeLayout.addView(layoutInflater.inflate(R.layout.calendar_zebra, null, false),0 );		
		dailyEvent =  SimpleCalendarView.db.getEvent(date,date);
		if(!dailyEvent.isEmpty()){
			TextView[] dailytxt = new TextView[dailyEvent.size()];
			for(int i = 0;i<dailyEvent.size();i++ ){
				dailytxt[i] = new TextView(this);
			}
			for (Event event : dailyEvent) {
				id =event.getId();
				color = SimpleCalendarView.db.getCategoryByEvent(id).getColor().toString();
				startTime = event.getStartTime().split(":");
				endTime = event.getEndTime().split(":");
				start = Integer.parseInt(startTime[0])*60 + Integer.parseInt(startTime[1]);
				end = Integer.parseInt(endTime[0])*60 + Integer.parseInt(endTime[1]);
				top = (start)*2;
				android.widget.RelativeLayout.LayoutParams txtParams1 = new android.widget.RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ((end*2)-(start*2)));
				txtParams1.setMargins(0, top, 0, 0);
				dailytxt[txtView].setLayoutParams(txtParams1);
				dailytxt[txtView].setText(event.getTitle());
				//RelativeLayout currentDateRelativeLayout = (RelativeLayout) findViewById(R.id.currentDateRelativeLayout);
				currentDateRelativeLayout.addView(dailytxt[txtView]);
				//dailytxt[txtView].setBackgroundColor(Color.parseColor("#FF0000"));
				dailytxt[txtView].setBackgroundColor(Color.parseColor(findColor(color)));
				//dailytxt[txtView].setTextColor(getResources().getColor(R.color.some_color));
				dailytxt[txtView].setOnClickListener(new OnClickListener() {
				    public void onClick(View view) {
				        // Do something
				    	//String title = (String)view.getTag();
				    	System.out.println(id);
				    	Intent ev= new Intent(DailyView.this, EventDetailsActivity.class);
				    	ev.putExtra("activity", (int)2);
				    	ev.putExtra("EVENT_ID", id);
			            startActivity(ev); 
				    }
				});
				txtView++;
			}
		}
	}
	
	
	/**
	 * Find color.
	 *
	 * @param color the color
	 * @return the string
	 */
	public static String findColor(String color){
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("Yellow", "#E9AB17");
		map.put("Black", "#000000");
		map.put("Blue", "#2B65EC");
		map.put("Red", "#C11B17");
		map.put("Green","#4CC552");
		map.put("Violet", "#8D38C9");
		map.put("Maroon", "#810541");
		map.put("Brown", "#806517");
		map.put("Olive", "#808000");
		map.put("Orange", "#FF8040");
		
		if(map.containsKey(color)){
			return map.get(color);
		}
		else{
			return "#848482";
		}
		
	}
	
}
