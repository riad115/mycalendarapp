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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class WeeklyView extends Activity implements OnClickListener{
	private static final String tag = "WeeklyView";
	private Calendar _calendar;
	private Button currentMonth;
	private ImageView prevMonth;
	private ImageView nextMonth;
	private TextView sunDate;
	private TextView monDate;
	private TextView tueDate;
	private TextView wedDate;
	private TextView thuDate;
	private TextView friDate;
	private TextView satDate;
	private TextView currentWeek;
	private int month, year , week;
	private int i =0;
	private int j = 0;
	private int hour, hourdp, min;
	private static final String dateTemplate = "MMM dd";
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd");
	private final SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd");
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        /*TextView textview = new TextView(this);
        textview.setText("This is Weekly tab");*/
        setContentView(R.layout.calendar_week_view);

		_calendar = Calendar.getInstance(Locale.getDefault());
		month = _calendar.get(Calendar.MONTH) + 1;
		year = _calendar.get(Calendar.YEAR);
		week = _calendar.get(Calendar.WEEK_OF_YEAR);
		hour = _calendar.get(Calendar.HOUR_OF_DAY);
		min = _calendar.get(Calendar.MINUTE);
		Log.d(tag, "Calendar Instance:= " + "Month: " + month + " " + "Year: " + year);
        ScrollView sv = (ScrollView)findViewById(R.id.calendarScrollView);
        sv.scrollTo(0, sv.getBottom());
        
     // "calculate" the start date of the week
        Calendar first = (Calendar) _calendar.clone();
        first.add(Calendar.DAY_OF_WEEK, 
                  first.getFirstDayOfWeek() - first.get(Calendar.DAY_OF_WEEK));

        // and add six days to the end date
        Calendar last = (Calendar) first.clone();
        last.add(Calendar.DAY_OF_YEAR, 6);
        
        currentMonth = (Button) this.findViewById(R.id.currentMonth);
		currentMonth.setText(dateFormat.format( first.getTime())+"-"+ dateFormat.format(last.getTime())+", "+year);
		
		currentWeek = (TextView) this.findViewById(R.id.currentYearTextView);
		currentWeek.setText("W"+week);
		
		Calendar nextDate = (Calendar) first.clone();
		sunDate = (TextView) this.findViewById(R.id.sundayDateTextView);
		sunDate.setText(dateFormat1.format(nextDate.getTime()));
		nextDate.add(Calendar.DAY_OF_YEAR, 1);
		monDate = (TextView) this.findViewById(R.id.mondayDateTextView);
		monDate.setText(dateFormat1.format(nextDate.getTime()));
		nextDate.add(Calendar.DAY_OF_YEAR, 1);
		tueDate = (TextView) this.findViewById(R.id.tuesdayDateTextView);
		tueDate.setText(dateFormat1.format(nextDate.getTime()));
		nextDate.add(Calendar.DAY_OF_YEAR, 1);
		wedDate = (TextView) this.findViewById(R.id.wednesdayDateTextView);
		wedDate.setText(dateFormat1.format(nextDate.getTime()));
		nextDate.add(Calendar.DAY_OF_YEAR, 1);
		thuDate = (TextView) this.findViewById(R.id.thursdayDateTextView);
		thuDate.setText(dateFormat1.format(nextDate.getTime()));
		nextDate.add(Calendar.DAY_OF_YEAR, 1);
		
		friDate = (TextView) this.findViewById(R.id.fridayDateTextView);
		friDate.setText(dateFormat1.format(nextDate.getTime()));
		
		nextDate.add(Calendar.DAY_OF_YEAR, 1);
		satDate = (TextView) this.findViewById(R.id.saturdayDateTextView);
		satDate.setText(dateFormat1.format(nextDate.getTime()));
		
		prevMonth = (ImageView) this.findViewById(R.id.prevMonth);
		prevMonth.setOnClickListener(this);
		
		nextMonth = (ImageView) this.findViewById(R.id.nextMonth);
		nextMonth.setOnClickListener(this);
		
		hourdp = ((hour - 1) * 120) + (min * 1)   ;
		LinearLayout currentTimeMarkerLinearLayout1 = (LinearLayout) findViewById(R.id.currentTimeMarkerLinearLayout);
		//currentTimeMarkerLinearLayout.setId(100);
		//currentTimeMarkerLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
		android.widget.RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams)currentTimeMarkerLinearLayout1.getLayoutParams();
		layoutParams2.setMargins(0, hourdp, 0, 0);
    }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Calendar first;
		Calendar last;
		Calendar nextDate;
		first = (Calendar) _calendar.clone();
		if (v == prevMonth)
		{
			//i= i +7;
			if(week<=1){
				week =52;
				year--;
			}
			
			else{
				week--;
			}
			
			 //first = (Calendar) _calendar.clone();
			if(j>0){
				j =j-7;
				i=0;
				first.add(Calendar.DAY_OF_WEEK, j+( 
		                  first.getFirstDayOfWeek() - first.get(Calendar.DAY_OF_WEEK)));
			}
			
			else{
			i = i+7;
	        first.add(Calendar.DAY_OF_WEEK, -i+( 
	                  first.getFirstDayOfWeek() - first.get(Calendar.DAY_OF_WEEK)));
			}
	        // and add six days to the end date
	        last = (Calendar) first.clone();
	        last.add(Calendar.DAY_OF_YEAR, 6);
	        
	        //currentMonth = (Button) this.findViewById(R.id.currentMonth);
			currentMonth.setText(dateFormat.format( first.getTime())+"-"+ dateFormat.format(last.getTime())+", "+year);
			
			//currentWeek = (TextView) this.findViewById(R.id.currentYearTextView);
			currentWeek.setText("W"+week);
			
			 nextDate = (Calendar) first.clone();
			//sunDate = (TextView) this.findViewById(R.id.sundayDateTextView);
			sunDate.setText(dateFormat1.format(nextDate.getTime()));
			nextDate.add(Calendar.DAY_OF_YEAR, 1);
			//monDate = (TextView) this.findViewById(R.id.mondayDateTextView);
			monDate.setText(dateFormat1.format(nextDate.getTime()));
			nextDate.add(Calendar.DAY_OF_YEAR, 1);
			//tueDate = (TextView) this.findViewById(R.id.tuesdayDateTextView);
			tueDate.setText(dateFormat1.format(nextDate.getTime()));
			nextDate.add(Calendar.DAY_OF_YEAR, 1);
			//wedDate = (TextView) this.findViewById(R.id.wednesdayDateTextView);
			wedDate.setText(dateFormat1.format(nextDate.getTime()));
			nextDate.add(Calendar.DAY_OF_YEAR, 1);
			//thuDate = (TextView) this.findViewById(R.id.thursdayDateTextView);
			thuDate.setText(dateFormat1.format(nextDate.getTime()));
			nextDate.add(Calendar.DAY_OF_YEAR, 1);
			
			//friDate = (TextView) this.findViewById(R.id.fridayDateTextView);
			friDate.setText(dateFormat1.format(nextDate.getTime()));
			
			nextDate.add(Calendar.DAY_OF_YEAR, 1);
			//satDate = (TextView) this.findViewById(R.id.saturdayDateTextView);
			satDate.setText(dateFormat1.format(nextDate.getTime()));
			Log.d(tag, "Setting Prev Week in WeeklyView: Week:"+week+" "+  " Year: " + year);
		}
		
		
		if (v == nextMonth)
		{
			//i = i+7;
			if(week>51){
				week=1;
				year++;
						
			}
			
			else{
				week++;
			}
			 //first = (Calendar) _calendar.clone();
			if(i>0){
				i = i -7;
				j=0;
				
				first.add(Calendar.DAY_OF_WEEK, -i+( 
		                  first.getFirstDayOfWeek() - first.get(Calendar.DAY_OF_WEEK)));
			}
			else{
				j =j+7;
	        first.add(Calendar.DAY_OF_WEEK, j+( 
	                  first.getFirstDayOfWeek() - first.get(Calendar.DAY_OF_WEEK)));
			}
	        // and add six days to the end date
	        last = (Calendar) first.clone();
	        last.add(Calendar.DAY_OF_YEAR, 6);
	        
	        //currentMonth = (Button) this.findViewById(R.id.currentMonth);
			currentMonth.setText(dateFormat.format( first.getTime())+"-"+ dateFormat.format(last.getTime())+", "+year);
			
			//currentWeek = (TextView) this.findViewById(R.id.currentYearTextView);
			currentWeek.setText("W"+week);
			
			 nextDate = (Calendar) first.clone();
			//sunDate = (TextView) this.findViewById(R.id.sundayDateTextView);
			sunDate.setText(dateFormat1.format(nextDate.getTime()));
			nextDate.add(Calendar.DAY_OF_YEAR, 1);
			//monDate = (TextView) this.findViewById(R.id.mondayDateTextView);
			monDate.setText(dateFormat1.format(nextDate.getTime()));
			nextDate.add(Calendar.DAY_OF_YEAR, 1);
			//tueDate = (TextView) this.findViewById(R.id.tuesdayDateTextView);
			tueDate.setText(dateFormat1.format(nextDate.getTime()));
			nextDate.add(Calendar.DAY_OF_YEAR, 1);
			//wedDate = (TextView) this.findViewById(R.id.wednesdayDateTextView);
			wedDate.setText(dateFormat1.format(nextDate.getTime()));
			nextDate.add(Calendar.DAY_OF_YEAR, 1);
			//thuDate = (TextView) this.findViewById(R.id.thursdayDateTextView);
			thuDate.setText(dateFormat1.format(nextDate.getTime()));
			nextDate.add(Calendar.DAY_OF_YEAR, 1);
			
			//friDate = (TextView) this.findViewById(R.id.fridayDateTextView);
			friDate.setText(dateFormat1.format(nextDate.getTime()));
			
			nextDate.add(Calendar.DAY_OF_YEAR, 1);
			//satDate = (TextView) this.findViewById(R.id.saturdayDateTextView);
			satDate.setText(dateFormat1.format(nextDate.getTime()));
			Log.d(tag, "Setting Next Week in WeeklyView: Week:"+week+" " + " Year: " + year);
		}
		
		
	}

}
