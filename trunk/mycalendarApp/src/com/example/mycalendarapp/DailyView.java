package com.example.mycalendarapp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DailyView extends Activity implements OnClickListener{
	
	private static final String tag = "DailyView";
	private Calendar _calendar;
	private Button currentMonth;
	private ImageView prevMonth;
	private ImageView nextMonth;
	private TextView txt1;
	private TextView[] dailytxt;
	private int month, year , week;
	private int i =0;
	private int j =0;
	private int hour, hourdp, min;
	private Long id;
	private String currentDate;
	private List<Event> dailyEvent;
	//private MySQLiteHelper db;
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
	private final SimpleDateFormat dateFormatSQl = new SimpleDateFormat("yyyy-MM-dd");
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
		currentMonth.setText(dateFormat.format(_calendar.getTime()));
		
		prevMonth = (ImageView) this.findViewById(R.id.prevMonth);
		prevMonth.setOnClickListener(this);
		
		nextMonth = (ImageView) this.findViewById(R.id.nextMonth);
		nextMonth.setOnClickListener(this);
		
		currentDate = dateFormatSQl.format(_calendar.getTime()).toString();
		
		 Log.d(tag, "Getting All Events for a Specific date : "+currentDate);
		
		
		hourdp = ((hour-1) * 120) + (min * 2);
		LinearLayout currentTimeMarkerLinearLayout1 = (LinearLayout) findViewById(R.id.currentTimeMarkerLinearLayout);
		//currentTimeMarkerLinearLayout.setId(100);
		//currentTimeMarkerLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
		android.widget.RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams)currentTimeMarkerLinearLayout1.getLayoutParams();
		layoutParams2.setMargins(0, hourdp, 0, 0);
		//currentTimeMarkerLinearLayout.setLayoutParams(layoutParams);
		//currentTimeMarkerLinearLayout.setBaselineAligned(false);
		//currentTimeMarkerLinearLayout.setPadding(0, 0, 0, 0);
		
		 /*txt1 = new TextView(this);
		LinearLayout.LayoutParams txtParams1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 240);
		txt1.setLayoutParams(txtParams1);
		txt1.setText("Coding");
		RelativeLayout currentDateRelativeLayout = (RelativeLayout) findViewById(R.id.currentDateRelativeLayout);
		currentDateRelativeLayout.addView(txt1);
		txt1.setOnClickListener(this);*/
		//dailyEvent =  EventActivity.db.getEvent(currentDate,currentDate);
		printDailyEvent(currentDate);
	
		
    }
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
		  printDailyEvent(currentDate);
			Log.d(tag, "Setting Next Date in GridCellAdapter: " + first.getTime());
			
		}
		//for(int txt=0;txt<dailyEvent.size();txt++){
			//if(v==tx){
			Intent ev= new Intent(this, EventDetailsActivity.class);
            startActivity(ev);      
            //finish();
			//}
		//}
	
	}
	
	public void printDailyEvent(String date){
		int txtView = 0;
		
		dailyEvent =  EventActivity.db.getEvent(date,date);
		if(!dailyEvent.isEmpty()){
			TextView[] dailytxt = new TextView[dailyEvent.size()];
			for(int i = 0;i<dailyEvent.size();i++ ){
				dailytxt[i] = new TextView(this);
			}
			for (Event event : dailyEvent) {
				id =event.getId();
				LinearLayout.LayoutParams txtParams1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 240);
				dailytxt[txtView].setLayoutParams(txtParams1);
				dailytxt[txtView].setText(event.getTitle());
				RelativeLayout currentDateRelativeLayout = (RelativeLayout) findViewById(R.id.currentDateRelativeLayout);
				currentDateRelativeLayout.addView(dailytxt[txtView]);
				dailytxt[txtView].setBackgroundColor(Color.parseColor("#FF0000"));
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
	
}
