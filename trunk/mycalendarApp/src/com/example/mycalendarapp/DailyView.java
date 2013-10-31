package com.example.mycalendarapp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.app.Activity;
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
	private int month, year , week;
	private int i =0;
	private int j =0;
	private int hour, hourdp, min;
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
		hour = _calendar.get(Calendar.HOUR_OF_DAY);
		min = _calendar.get(Calendar.MINUTE);
		Log.d(tag, "Calendar Instance:= " + "Month: " + month + " " + "Year: " + year);
		
		currentMonth = (Button) this.findViewById(R.id.currentMonth);
		currentMonth.setText(dateFormat.format(_calendar.getTime()));
		
		prevMonth = (ImageView) this.findViewById(R.id.prevMonth);
		prevMonth.setOnClickListener(this);
		
		nextMonth = (ImageView) this.findViewById(R.id.nextMonth);
		nextMonth.setOnClickListener(this);
		
		/*LinearLayout currentTimeMarkerLinearLayout = new LinearLayout(this);
		currentTimeMarkerLinearLayout.setId(100);
		currentTimeMarkerLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		layoutParams.setMargins(0, 200, 0, 0);
		currentTimeMarkerLinearLayout.setLayoutParams(layoutParams);
		currentTimeMarkerLinearLayout.setBaselineAligned(false);
		currentTimeMarkerLinearLayout.setPadding(0, 0, 0, 0);
		
		View view = new View(this);
		LinearLayout.LayoutParams viewParams = new LinearLayout.LayoutParams(0, 3);
		viewParams.weight =1f;
		view.setLayoutParams(viewParams);
		
		View currentTimeLineView =new View(this);
		LinearLayout.LayoutParams viewParams1 = new LinearLayout.LayoutParams(0,1);
		viewParams1.weight =14f;
		currentTimeLineView.setLayoutParams(viewParams1);
		currentTimeLineView.setBackgroundColor(Color.parseColor("#FF0000"));

		currentTimeMarkerLinearLayout.addView(view);
		currentTimeMarkerLinearLayout.addView(currentTimeLineView);
		
		RelativeLayout calendarRelativeLayout = (RelativeLayout) findViewById(R.id.calendarRelativeLayout);
		calendarRelativeLayout.addView(currentTimeMarkerLinearLayout);*/
		
		
		hourdp = ((hour-1) * 120) + (min * 1)  ;
		LinearLayout currentTimeMarkerLinearLayout1 = (LinearLayout) findViewById(R.id.currentTimeMarkerLinearLayout);
		//currentTimeMarkerLinearLayout.setId(100);
		//currentTimeMarkerLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
		android.widget.RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams)currentTimeMarkerLinearLayout1.getLayoutParams();
		layoutParams2.setMargins(0, hourdp, 0, 0);
		//currentTimeMarkerLinearLayout.setLayoutParams(layoutParams);
		//currentTimeMarkerLinearLayout.setBaselineAligned(false);
		//currentTimeMarkerLinearLayout.setPadding(0, 0, 0, 0);
	
		
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
