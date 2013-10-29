package com.example.mycalendarapp;

import java.util.Calendar;
import java.util.Locale;

import android.os.Bundle;
import android.app.Activity;
import android.app.LocalActivityManager;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;


public class MainCalenadarActivity extends Activity implements OnClickListener{
	private static final String tag = "SimpleCalendarViewActivity";

	private ImageView calendarToJournalButton;
	private Button selectedDayMonthYearButton;
	private Button currentMonth;
	private ImageView prevMonth;
	private ImageView nextMonth;
	private GridView calendarView;
	private SimpleCalendarView adapter;
	private Calendar _calendar;
	private int month, year;
	private final DateFormat dateFormatter = new DateFormat();
	private static final String dateTemplate = "MMMM yyyy";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calendar_tab_view);
		
		Resources ressources = getResources(); 
		TabHost tabHost = (TabHost) findViewById(R.id.tabhost);
		LocalActivityManager mLocalActivityManager = new LocalActivityManager(this, false);
		mLocalActivityManager.dispatchCreate(savedInstanceState);
		tabHost.setup(mLocalActivityManager);
		
		//Monthly
		Intent intentMonthly = new Intent().setClass(this, MonthlyView.class);
		TabSpec tabSpecMonthly = tabHost
		  .newTabSpec("Month")
		  .setIndicator("", ressources.getDrawable(R.drawable.monthly_view))
		  .setContent(intentMonthly);
		
		Intent intentWeekly = new Intent().setClass(this, WeeklyView.class);
		TabSpec tabSpecWeekly = tabHost
		  .newTabSpec("Week")
		  .setIndicator("", ressources.getDrawable(R.drawable.weekly_view))
		  .setContent(intentWeekly);
		
		Intent intentDaily = new Intent().setClass(this, DailyView.class);
		TabSpec tabSpecDaily = tabHost
		  .newTabSpec("Day")
		  .setIndicator("", ressources.getDrawable(R.drawable.daily_view))
		  .setContent(intentDaily);
		
		Intent intentEvent = new Intent().setClass(this, EventActivity.class);
		TabSpec tabSpecEvent = tabHost
		  .newTabSpec("Event")
		  .setIndicator("", ressources.getDrawable(R.drawable.calendar_add_event))
		  .setContent(intentEvent);
		
		//addIng Tab
		tabHost.addTab(tabSpecMonthly);
		tabHost.addTab(tabSpecWeekly);
		tabHost.addTab(tabSpecDaily);
		tabHost.addTab(tabSpecEvent);
		
		//deafualt tab
		
		tabHost.setCurrentTab(0);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	private void setGridCellAdapterToDate(int month, int year)
	{
		adapter = new SimpleCalendarView(getApplicationContext(), R.id.calendar_day_gridcell, month, year);
		_calendar.set(year, month - 1, _calendar.get(Calendar.DAY_OF_MONTH));
		currentMonth.setText(dateFormatter.format(dateTemplate, _calendar.getTime()));
		adapter.notifyDataSetChanged();
		calendarView.setAdapter(adapter);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == prevMonth)
		{
			if (month <= 1)
				{
					month = 12;
					year--;
				}
			else
				{
					month--;
				}
			Log.d(tag, "Setting Prev Month in GridCellAdapter: " + "Month: " + month + " Year: " + year);
			setGridCellAdapterToDate(month, year);
		}
	if (v == nextMonth)
		{
			if (month > 11)
				{
					month = 1;
					year++;
				}
			else
				{
					month++;
				}
			Log.d(tag, "Setting Next Month in GridCellAdapter: " + "Month: " + month + " Year: " + year);
			setGridCellAdapterToDate(month, year);
		}
	}
	
	
	@Override
	public void onDestroy()
		{
			Log.d(tag, "Destroying View ...");
			super.onDestroy();
		}

}
