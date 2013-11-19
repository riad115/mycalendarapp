package com.example.mycalendarapp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import android.net.ParseException;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;


// TODO: Auto-generated Javadoc
/**
 * The Class SimpleCalendarView is for the gridview for {@link MonthlyView} class.
 */
public class SimpleCalendarView extends BaseAdapter implements OnClickListener{
	
	
	
	
	
	
	
	/** The Constant tag. */
	private static final String tag = "SimpleCalendarView";
	
	/** The _context. */
	private final Context _context;
	
	/** The selected day month year button. */
	private Button selectedDayMonthYearButton;
	
	/** The list. */
	private final List<String> list;
	
	/** The monthly event. */
	private List<Event> monthlyEvent;
	
	/** The Constant DAY_OFFSET. */
	private static final int DAY_OFFSET = 1;
	
	/** The weekdays. */
	private final String[] weekdays = new String[]{"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
	
	/** The months. */
	private final String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	
	/** The days of month. */
	private final int[] daysOfMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
	/** The days of month leap year. */
	private final int[] daysOfMonthLeapYear = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
	/** The year. */
	private final int month, year;
	
	/** The prev month days. */
	private int daysInMonth, prevMonthDays;
	
	/** The current day of month. */
	private int currentDayOfMonth;
	
	/** The current week day. */
	private int currentWeekDay;
	
	/** The gridcell. */
	private Button gridcell;
	
	/** The num_events_per_day. */
	private TextView num_events_per_day;
	
	/** The events per month map. */
	private final HashMap eventsPerMonthMap;
	
	/** The date formatter. */
	private final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy");
	
	/** The date format s ql. */
	private final SimpleDateFormat dateFormatSQl = new SimpleDateFormat("yyyy-MM-dd");
	
	/** The db. */
	public static MySQLiteHelper db;
	
	/**
	 * Instantiates a new simple calendar view.
	 *
	 * @param context the context
	 * @param textViewResourceId the text view resource id
	 * @param month the month
	 * @param year the year
	 */
	public SimpleCalendarView(Context context, int textViewResourceId, int month, int year)
	{
		super();
		this._context = context;
		this.list = new ArrayList<String>();
		this.month = month;
		this.year = year;
		this.monthlyEvent = new ArrayList<Event>();
		Log.d(tag, "==> Passed in Date FOR Month: " + month + " " + "Year: " + year);
		Calendar calendar = Calendar.getInstance();
		if (month == calendar.get(Calendar.MONTH)+1 && year == calendar.get(Calendar.YEAR)){ 
			setCurrentDayOfMonth(calendar.get(Calendar.DAY_OF_MONTH));
		}
		setCurrentWeekDay(calendar.get(Calendar.DAY_OF_WEEK));
		Log.d(tag, "New Calendar:= " + calendar.getTime().toString());
		Log.d(tag, "CurrentDayOfWeek :" + getCurrentWeekDay());
		Log.d(tag, "CurrentDayOfMonth :" + getCurrentDayOfMonth());
		db = new  MySQLiteHelper(_context);
		//db.clearDatebase();
		// Print Month
		printMonth(month, year);
		
		// Find Number of Events
		eventsPerMonthMap = findNumberOfEventsPerMonth(year, month);
	}
	
	/**
	 * Gets the month as string.
	 *
	 * @param i the i
	 * @return the month as string
	 */
	private String getMonthAsString(int i)
	{
		return months[i];
	}

/**
 * Gets the week day as string.
 *
 * @param i the i
 * @return the week day as string
 */
private String getWeekDayAsString(int i)
	{
		return weekdays[i];
	}

/**
 * Gets the number of days of month.
 *
 * @param i the i
 * @return the number of days of month
 */
private int getNumberOfDaysOfMonth(int i)
	{
		return daysOfMonth[i];
	}

/* (non-Javadoc)
 * @see android.widget.Adapter#getItem(int)
 */
public String getItem(int position)
{
	return list.get(position);
}
	
	/* (non-Javadoc)
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	//@Override
	//public Object getItem(int position) {
		// TODO Auto-generated method stub
		//return null;
	//}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View row = convertView;
		if (row == null)
			{
				LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				row = inflater.inflate(R.layout.calendar_day_gridcell, parent, false);
			}

		// Get a reference to the Day gridcell
		gridcell = (Button) row.findViewById(R.id.calendar_day_gridcell);
		gridcell.setOnClickListener(this);
		
		//selectedDayMonthYearButton = (Button) row.findViewById(R.id.selectedDayMonthYear);
		

		// ACCOUNT FOR SPACING

		Log.d(tag, "Current Day: " + getCurrentDayOfMonth());
		String[] day_color = list.get(position).split("-");
		String theday = day_color[0];
		String themonth = day_color[2];
		String theyear = day_color[3];
		String event = day_color[4];
		/*if ((!eventsPerMonthMap.isEmpty()) && (eventsPerMonthMap != null))
			{
				if (eventsPerMonthMap.containsKey(theday))
					{
						num_events_per_day = (TextView) row.findViewById(R.id.num_events_per_day);
						Integer numEvents = (Integer) eventsPerMonthMap.get(theday);
						num_events_per_day.setText(numEvents.toString());
					}
			}
		*/
		// Set the Day GridCell
		gridcell.setText(theday);
		if(Integer.parseInt(theday)<=9){
			gridcell.setTag("0"+theday + "-" + themonth + "-" + theyear);
		}
		
		else{
			gridcell.setTag(theday + "-" + themonth + "-" + theyear);
		}
		//String dt = theday + "-" + themonth + "-" + theyear;
		//String calDate = theyear+"-"+Integer.toString(month)+"-"+theday;
		/*try {
			Date parseDt = dateFormatSQl.parse(dt);
			calDate = dateFormatSQl.format(parseDt);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		//Log.d(tag, "Setting GridCell " + calDate);
		
		Log.d(tag, "Setting GridCell " + theday + "-" + themonth + "-" + theyear);
		
		//monthlyEvent = MainCalenadarActivity.db.getEvent(calDate,calDate);
		
		//Log.d(tag, "Setting GridCell " + Integer.toString(monthlyEvent.size()));
		
		if(day_color[4].equals("Y")){
			//gridcell.setBackgroundColor(Color.parseColor("#008000"));
			gridcell.setBackgroundResource(R.drawable.event_bg2);
		}
		
		if(day_color[1].equals("GREEN")){
			gridcell.setTextColor(Color.parseColor("#008000"));
		}
		if (day_color[1].equals("GREY"))
			{
				gridcell.setTextColor(Color.LTGRAY);
			}
		if (day_color[1].equals("WHITE"))
			{
				gridcell.setTextColor(Color.WHITE);
			}
		if (day_color[1].equals("RED"))
		{
			gridcell.setTextColor(Color.RED);
		}
		if (day_color[1].equals("BLUE"))
			{
				gridcell.setTextColor(_context.getResources().getColor(R.color.static_text_color));
			}
		return row;
	}
	
	
	
	

	/**
	 * Gets the current day of month.
	 *
	 * @return the current day of month
	 */
	private int getCurrentDayOfMonth() {
		// TODO Auto-generated method stub
		return currentDayOfMonth;
	}

	

	/**
	 * Sets the current week day.
	 *
	 * @param currentWeekDay the new current week day
	 */
	private void setCurrentWeekDay(int currentWeekDay) {
		// TODO Auto-generated method stub
		this.currentWeekDay =currentWeekDay;
		
	}
	
	/**
	 * Gets the current week day.
	 *
	 * @return the current week day
	 */
	public int getCurrentWeekDay()
	{
		return currentWeekDay;
	}
	

	/**
	 * Prints the month.
	 *
	 * @param mm the mm
	 * @param yy the yy
	 */
	private void printMonth(int mm, int yy) {
		// TODO Auto-generated method stub
		Log.d(tag, "==> printMonth: mm: " + mm + " " + "yy: " + yy);
		// The number of days to leave blank at
		// the start of this month.
		int trailingSpaces = 0;
		int leadSpaces = 0;
		int daysInPrevMonth = 0;
		int prevMonth = 0;
		int prevYear = 0;
		int nextMonth = 0;
		int nextYear = 0;
		List <Integer> listWeekend = new ArrayList<Integer>();

		int currentMonth = mm - 1;
		String currentMonthName = getMonthAsString(currentMonth);
		daysInMonth = getNumberOfDaysOfMonth(currentMonth);

		Log.d(tag, "Current Month: " + " " + currentMonthName + " having " + daysInMonth + " days.");

		// Gregorian Calendar : MINUS 1, set to FIRST OF MONTH
		GregorianCalendar cal = new GregorianCalendar(yy, currentMonth, 1);
		GregorianCalendar calWknd = new GregorianCalendar(yy, currentMonth, 1);
		Log.d(tag, "Gregorian Calendar:= " + cal.getTime().toString());

		if (currentMonth == 11)
			{
				prevMonth = currentMonth - 1;
				daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
				nextMonth = 0;
				prevYear = yy;
				nextYear = yy + 1;
				Log.d(tag, "*->PrevYear: " + prevYear + " PrevMonth:" + prevMonth + " NextMonth: " + nextMonth + " NextYear: " + nextYear);
			}
		else if (currentMonth == 0)
			{
				prevMonth = 11;
				prevYear = yy - 1;
				nextYear = yy;
				daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
				nextMonth = 1;
				Log.d(tag, "**--> PrevYear: " + prevYear + " PrevMonth:" + prevMonth + " NextMonth: " + nextMonth + " NextYear: " + nextYear);
			}
		else
			{
				prevMonth = currentMonth - 1;
				nextMonth = currentMonth + 1;
				nextYear = yy;
				prevYear = yy;
				daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
				Log.d(tag, "***---> PrevYear: " + prevYear + " PrevMonth:" + prevMonth + " NextMonth: " + nextMonth + " NextYear: " + nextYear);
			}

		// Compute how much to leave before before the first day of the
		// month.
		// getDay() returns 0 for Sunday.
		int currentWeekDay = cal.get(Calendar.DAY_OF_WEEK) - 1;
		trailingSpaces = currentWeekDay;

		Log.d(tag, "Week Day:" + currentWeekDay + " is " + getWeekDayAsString(currentWeekDay));
		Log.d(tag, "No. Trailing space to Add: " + trailingSpaces);
		Log.d(tag, "No. of Days in Previous Month: " + daysInPrevMonth);
		
		do {
		    // get the day of the week for the current day
		    int day = calWknd.get(Calendar.DAY_OF_WEEK);
		    // check if it is a Saturday or Sunday
		    if (day == Calendar.SATURDAY || day == Calendar.SUNDAY) {
		        // print the day - but you could add them to a list or whatever
		        listWeekend.add(calWknd.get(Calendar.DAY_OF_MONTH));
		       // System.out.println(calWknd.get(Calendar.DAY_OF_MONTH));
		    }
		    // advance to the next day
		    calWknd.add(Calendar.DAY_OF_YEAR, 1);
		    
		}  while (calWknd.get(Calendar.MONTH) == currentMonth);
		
		if (cal.isLeapYear(cal.get(Calendar.YEAR)) && mm == 2)
			{
				++daysInMonth;
			}

		// Trailing Month days
		for (int i = 0; i < trailingSpaces; i++)
			{
				if (cal.isLeapYear(cal.get(Calendar.YEAR)) && mm == Calendar.MARCH + 1)
					{
					//list.add(String.valueOf((daysInPrevMonth – trailingSpaces + DAY_OFFSET) + i + 1) + "-GREY" + "-" + getMonthAsString(prevMonth) + "-" + prevYear+"-N");
					list.add(String.valueOf((daysInPrevMonth - trailingSpaces + DAY_OFFSET) + i+1) + "-GREY" + "-" + getMonthAsString(prevMonth) + "-" + prevYear+"-N");

					}
				else{
					list.add(String.valueOf((daysInPrevMonth - trailingSpaces + DAY_OFFSET) + i) + "-GREY" + "-" + getMonthAsString(prevMonth) + "-" + prevYear+"-N");
				}
				Log.d(tag, "PREV MONTH:= " + prevMonth + " => " + getMonthAsString(prevMonth) + " " + String.valueOf((daysInPrevMonth - trailingSpaces + DAY_OFFSET) + i));

			}

		// Current Month Days
		for (int i = 1; i <= daysInMonth; i++)
			{
				String date = null;
				String month= null;
				if(mm<=9){
					month = "0"+Integer.toString(mm);
				}
				
				else{
					month = Integer.toString(mm);
				}
				
				if(i<=9){
					date = Integer.toString(yy)+"-"+month+"-0"+Integer.toString(i);
					Log.d(tag, "Setting GridCell " + date);
				//monthlyEvent = MainCalenadarActivity.db.getEvent(date,date);
				}
				else{
					date = Integer.toString(yy)+"-"+month+"-"+Integer.toString(i);
				}
				int size = MonthlyView.eventCount(date);
				
				//for (Event event : monthlyEvent) {
		           // Log.d(tag+"  "+event.getTitle(),"ID:"+ event.getId()+"Description:"+event.getDescription());
		        //}
				Log.d(tag, "Setting GridCell " + date +"   "+ Integer.toString(size));
				Log.d(currentMonthName, String.valueOf(i) + " " + getMonthAsString(currentMonth) + " " + yy);
				
				if(findHoliday(date)){
					if(size==0){
						list.add(String.valueOf(i) + "-GREEN" + "-" + getMonthAsString(currentMonth) + "-" + yy+"-N");
					}
					
					else{
						list.add(String.valueOf(i) + "-GREEN" + "-" + getMonthAsString(currentMonth) + "-" + yy+"-Y");
					}
				}
				
				else if (i == getCurrentDayOfMonth())
					{  
						if(size==0){
							list.add(String.valueOf(i) + "-BLUE" + "-" + getMonthAsString(currentMonth) + "-" + yy+"-N");
						}
						
						else{
							list.add(String.valueOf(i) + "-BLUE" + "-" + getMonthAsString(currentMonth) + "-" + yy+"-Y");
						}
					}
				else if(listWeekend.contains(i)){
					if(size==0){
						list.add(String.valueOf(i) + "-RED" + "-" + getMonthAsString(currentMonth) + "-" + yy+"-N");
					}
					//System.out.println(listWeekend.get(i));
					else{
						list.add(String.valueOf(i) + "-BLUE" + "-" + getMonthAsString(currentMonth) + "-" + yy+"-Y");
					}
				}
				else
					{
					if(size==0){
						list.add(String.valueOf(i) + "-WHITE" + "-" + getMonthAsString(currentMonth) + "-" + yy+"-N");
					}
						else{
							list.add(String.valueOf(i) + "-BLUE" + "-" + getMonthAsString(currentMonth) + "-" + yy+"-Y");
						}
					}
			}

		// Leading Month days
		for (int i = 0; i < list.size() % 7; i++)
			{
				Log.d(tag, "NEXT MONTH:= " + getMonthAsString(nextMonth));
				list.add(String.valueOf(i + 1) + "-GREY" + "-" + getMonthAsString(nextMonth) + "-" + nextYear+"-N");
			}
	}

	/**
	 * Sets the current day of month.
	 *
	 * @param currentDayOfMonth the new current day of month
	 */
	private void setCurrentDayOfMonth(int currentDayOfMonth) {
		// TODO Auto-generated method stub
		this.currentDayOfMonth =currentDayOfMonth;
	}
	
	/**
	 * Find number of events per month.
	 *
	 * @param year the year
	 * @param month the month
	 * @return the hash map
	 */
	private HashMap findNumberOfEventsPerMonth(int year, int month)
	{
		HashMap map = new HashMap<String, Integer>();
		// DateFormat dateFormatter2 = new DateFormat();
		//						
		// String day = dateFormatter2.format("dd", dateCreated).toString();
		//
		// if (map.containsKey(day))
		// {
		// Integer val = (Integer) map.get(day) + 1;
		// map.put(day, val);
		// }
		// else
		// {
		// map.put(day, 1);
		// }
		return map;
	}
	
	/**
	 * Find holiday.
	 *
	 * @param date the date
	 * @return true, if successful
	 */
	public static boolean findHoliday(String date){
		HashMap<String, Integer> map= new HashMap<String, Integer>();
		map.put("2013-09-02", 1);
		map.put("2013-11-28", 1);
		map.put("2013-11-29", 1);
		map.put("2013-12-23", 1);
		map.put("2013-12-24", 1);
		map.put("2013-12-25", 1);
		map.put("2013-12-26", 1);
		map.put("2013-12-27", 1);
		map.put("2013-12-30", 1);
		map.put("2013-12-31", 1);
		map.put("2014-01-01", 1);
		map.put("2014-01-02", 1);
		map.put("2014-01-20", 1);
		map.put("2014-05-26", 1);
		map.put("2014-07-04", 1);
		map.put("2013-01-01", 1);
		map.put("2013-01-21", 1);
		map.put("2013-05-27", 1);
		map.put("2012-09-03", 1);
		map.put("2012-11-22", 1);
		map.put("2012-11-23", 1);
		map.put("2012-12-28", 1);
		map.put("2012-12-24", 1);
		map.put("2012-12-25", 1);
		map.put("2012-12-26", 1);
		map.put("2012-12-27", 1);
		map.put("2012-12-30", 1);
		map.put("2012-12-31", 1);
		boolean holiday = map.containsKey(date);
		return holiday;
		
	}
	

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		String date_month_year = (String) view.getTag();
		System.out.println(date_month_year);
		String date = null;
		//selectedDayMonthYearButton = (Button) view.findViewById(R.id.selectedDayMonthYear);
		//selectedDayMonthYearButton.setOnClickListener(this);
		

		try{
				//Date parsedDate = dateFormatSQl.parse(date_month_year);
				//selectedDayMonthYearButton.setText("Selected: ");
				date = dateFormatSQl.format(dateFormatter.parse(date_month_year));
				Log.d(tag, "Parsed Date: " + date.toString());

			}
		//catch (ParseException e)
			//{
				//e.printStackTrace();
			//} 
		
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Intent i = new Intent(_context, DailyView.class);
        i.putExtra("activity", (int)1);
        i.putExtra("Date", date);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);
		
	}

}
