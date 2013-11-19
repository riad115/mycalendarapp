package com.example.mycalendarapp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

// TODO: Auto-generated Javadoc
/**
 * The Class MonthlyView for the monthly view of the calendar.
 */
public class MonthlyView extends Activity implements OnClickListener{
	
	/** The Constant tag. */
	private static final String tag = "MonthlyView";

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
	
	/** The date formatter. */
	private final DateFormat dateFormatter = new DateFormat();
	
	/** The Constant dateTemplate. */
	private static final String dateTemplate = "MMMM yyyy";
	
	/** The date format. */
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
	
	//public static MySQLiteHelper db;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calendar_view);
		
		
		
		
		_calendar = Calendar.getInstance(Locale.getDefault());
		month = _calendar.get(Calendar.MONTH) + 1;
		year = _calendar.get(Calendar.YEAR);
		Log.d(tag, "Calendar Instance:= " + "Month: " + month + " " + "Year: " + year);

		//selectedDayMonthYearButton = (Button) this.findViewById(R.id.selectedDayMonthYear);
		//selectedDayMonthYearButton.setText("Selected: ");

		prevMonth = (ImageView) this.findViewById(R.id.prevMonth);
		prevMonth.setOnClickListener(this);

		currentMonth = (Button) this.findViewById(R.id.currentMonth);
		currentMonth.setText(dateFormatter.format(dateTemplate, _calendar.getTime()));

		nextMonth = (ImageView) this.findViewById(R.id.nextMonth);
		nextMonth.setOnClickListener(this);

		calendarView = (GridView) this.findViewById(R.id.calendar);

		// Initialised
		adapter = new SimpleCalendarView(getApplicationContext(), R.id.calendar_day_gridcell, month, year);
		adapter.notifyDataSetChanged();
		calendarView.setAdapter(adapter);
		//db = new  MySQLiteHelper(getApplicationContext());
		/*List<Event> allEvents = MainCalenadarActivity.db.getEvent("2013-11-09", "2013-11-09");
        for (Event event : allEvents) {
            Log.d("Event:"+event.getTitle(),"ID:"+ event.getId()+"Description:"+event.getDescription());
        }*/
		
		//db.closeDB();
    }
	
	/**
	 * Sets the grid cell adapter to date for the selected month.
	 *
	 * @param month the month
	 * @param year the year
	 */
	private void setGridCellAdapterToDate(int month, int year)
	{
		adapter = new SimpleCalendarView(getApplicationContext(), R.id.calendar_day_gridcell, month, year);
		_calendar.set(year, month - 1, _calendar.get(Calendar.DAY_OF_MONTH));
		currentMonth.setText(dateFormatter.format(dateTemplate, _calendar.getTime()));
		adapter.notifyDataSetChanged();
		calendarView.setAdapter(adapter);
	}
	
	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
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
	
	
	 /**
 	 * Event count.
 	 *
 	 * @param date the date
 	 * @return the event count of that particular date
 	 */
 	public static int eventCount(String date){
		
		 List<Event> allEvents = SimpleCalendarView.db.getEvent(date, date);
		 
		 
		 return allEvents.size();
		  // return 0;
	   }

}
