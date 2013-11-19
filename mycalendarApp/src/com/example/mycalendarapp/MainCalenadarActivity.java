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
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;


// TODO: Auto-generated Javadoc
/**
 * The Class MainCalenadarActivity.
 */
public class MainCalenadarActivity extends Activity implements OnClickListener{
	
	/** The Constant tag. */
	private static final String tag = "SimpleCalendarViewActivity";

	/** The calendar to journal button. */
	private ImageView calendarToJournalButton;
	
	/** The selected day month year button. */
	private Button selectedDayMonthYearButton;
	
	/** The current month. */
	private Button currentMonth;
	
	/** The prev month. */
	private ImageView prevMonth;
	
	/** The next month. */
	private ImageView nextMonth;
	
	/** The calendar view. */
	private GridView calendarView;
	
	/** The adapter. */
	private SimpleCalendarView adapter;
	
	/** The _calendar. */
	private Calendar _calendar;
	
	/** The year. */
	private int month, year;
	
	/** The tab host. */
	public static TabHost tabHost;
	
	/** The date formatter. */
	private final DateFormat dateFormatter = new DateFormat();
	
	/** The Constant dateTemplate. */
	private static final String dateTemplate = "MMMM yyyy";
	
	/** The i. */
	public static int i=0;
	
	/** The j. */
	public static int j =0;
	
	//public static MySQLiteHelper db;
	
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calendar_tab_view);
		
		
		//db = new  MySQLiteHelper(getApplicationContext());
		
		
		Resources ressources = getResources(); 
		tabHost = (TabHost) findViewById(R.id.tabhost);
		LocalActivityManager mLocalActivityManager = new LocalActivityManager(this, false);
		mLocalActivityManager.dispatchCreate(savedInstanceState);
		tabHost.setup(mLocalActivityManager);
		
		//Monthly
		Intent intentMonthly = new Intent().setClass(this, MonthlyView.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		TabSpec tabSpecMonthly = tabHost
		  .newTabSpec("Month")
		  .setIndicator("", ressources.getDrawable(R.drawable.monthly_view))
		  .setContent(intentMonthly);
		
		Intent intentWeekly = new Intent().setClass(this, WeeklyView.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		TabSpec tabSpecWeekly = tabHost
		  .newTabSpec("Week")
		  .setIndicator("", ressources.getDrawable(R.drawable.weekly_view))
		  .setContent(intentWeekly);
		
		Intent intentDaily = new Intent().setClass(this, DailyView.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		TabSpec tabSpecDaily = tabHost
		  .newTabSpec("Day")
		  .setIndicator("", ressources.getDrawable(R.drawable.daily_view))
		  .setContent(intentDaily);
		
		Intent intentEvent = new Intent().setClass(this, EventList.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	/*private void setGridCellAdapterToDate(int month, int year)
	{
		adapter = new SimpleCalendarView(getApplicationContext(), R.id.calendar_day_gridcell, month, year);
		_calendar.set(year, month - 1, _calendar.get(Calendar.DAY_OF_MONTH));
		currentMonth.setText(dateFormatter.format(dateTemplate, _calendar.getTime()));
		adapter.notifyDataSetChanged();
		calendarView.setAdapter(adapter);
	}*/
	
	/**
	 * Gets the my tab host.
	 *
	 * @return the my tab host
	 */
	public TabHost getMyTabHost() { return tabHost; }
	

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		
	}
	
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    case R.id.add_event:
	    	Intent ev= new Intent(this, EventActivity.class);
            startActivity(ev); 
	        return true;
	    
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onDestroy()
	 */
	@Override
	public void onDestroy()
		{
			Log.d(tag, "Destroying View ...");
			super.onDestroy();
		}

}
