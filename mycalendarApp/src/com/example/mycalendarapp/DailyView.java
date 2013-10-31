package com.example.mycalendarapp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DailyView extends Activity implements OnClickListener{
	
	private static final String tag = "DailyView";
	private Calendar _calendar;
	private Button currentMonth;
	private ImageView prevMonth;
	private ImageView nextMonth;
	private int month, year , week;
	private int i =0;
	private int j =0;
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        //TextView textview = new TextView(this);
        //textview.setText("This is Daily tab");
        setContentView(R.layout.calendar_day_view);
        
        _calendar = Calendar.getInstance(Locale.getDefault());
		month = _calendar.get(Calendar.MONTH) + 1;
		year = _calendar.get(Calendar.YEAR);
		week = _calendar.get(Calendar.WEEK_OF_YEAR);
		Log.d(tag, "Calendar Instance:= " + "Month: " + month + " " + "Year: " + year);
		
		currentMonth = (Button) this.findViewById(R.id.currentMonth);
		currentMonth.setText(dateFormat.format(_calendar.getTime()));
		
		prevMonth = (ImageView) this.findViewById(R.id.prevMonth);
		prevMonth.setOnClickListener(this);
		
		nextMonth = (ImageView) this.findViewById(R.id.nextMonth);
		nextMonth.setOnClickListener(this);
    }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		Calendar first;
		
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
			Log.d(tag, "Setting Next Date in GridCellAdapter: " + first.getTime());
			
		}
	}

}
