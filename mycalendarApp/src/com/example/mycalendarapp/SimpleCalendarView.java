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


public class SimpleCalendarView extends BaseAdapter implements OnClickListener{
	
	
	
	
	
	
	
	private static final String tag = "SimpleCalendarView";
	private final Context _context;
	private Button selectedDayMonthYearButton;
	private final List<String> list;
	private List<Event> monthlyEvent;
	private static final int DAY_OFFSET = 1;
	private final String[] weekdays = new String[]{"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
	private final String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	private final int[] daysOfMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	private final int month, year;
	private int daysInMonth, prevMonthDays;
	private int currentDayOfMonth;
	private int currentWeekDay;
	private Button gridcell;
	private TextView num_events_per_day;
	private final HashMap eventsPerMonthMap;
	private final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy");
	private final SimpleDateFormat dateFormatSQl = new SimpleDateFormat("yyyy-MM-dd");
	
	public static MySQLiteHelper db;
	
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
		if (month == calendar.get(Calendar.MONTH)+1){ 
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
	private String getMonthAsString(int i)
	{
		return months[i];
	}

private String getWeekDayAsString(int i)
	{
		return weekdays[i];
	}

private int getNumberOfDaysOfMonth(int i)
	{
		return daysOfMonth[i];
	}

public String getItem(int position)
{
	return list.get(position);
}
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

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

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
	
	
	
	

	private int getCurrentDayOfMonth() {
		// TODO Auto-generated method stub
		return currentDayOfMonth;
	}

	

	private void setCurrentWeekDay(int currentWeekDay) {
		// TODO Auto-generated method stub
		this.currentWeekDay =currentWeekDay;
		
	}
	
	public int getCurrentWeekDay()
	{
		return currentWeekDay;
	}
	

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
		
		if (cal.isLeapYear(cal.get(Calendar.YEAR)) && mm == 1)
			{
				++daysInMonth;
			}

		// Trailing Month days
		for (int i = 0; i < trailingSpaces; i++)
			{
				Log.d(tag, "PREV MONTH:= " + prevMonth + " => " + getMonthAsString(prevMonth) + " " + String.valueOf((daysInPrevMonth - trailingSpaces + DAY_OFFSET) + i));
				list.add(String.valueOf((daysInPrevMonth - trailingSpaces + DAY_OFFSET) + i) + "-GREY" + "-" + getMonthAsString(prevMonth) + "-" + prevYear+"-N");
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

	private void setCurrentDayOfMonth(int currentDayOfMonth) {
		// TODO Auto-generated method stub
		this.currentDayOfMonth =currentDayOfMonth;
	}
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
