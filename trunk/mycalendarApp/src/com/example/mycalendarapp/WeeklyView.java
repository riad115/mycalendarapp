package com.example.mycalendarapp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

// TODO: Auto-generated Javadoc
/**
 * The Class WeeklyView is the weekly view of a particular week in the calendar.
 */
public class WeeklyView extends Activity implements OnClickListener{
	
	/** The Constant tag. */
	private static final String tag = "WeeklyView";
	
	/** The _calendar. */
	private Calendar _calendar;
	
	/** The current month. */
	private Button currentMonth;
	
	/** The prev month. */
	private ImageView prevMonth;
	
	/** The next month. */
	private ImageView nextMonth;
	
	/** The layout inflater. */
	private LayoutInflater layoutInflater;
	
	/** The sunday relative layout. */
	private RelativeLayout sundayRelativeLayout;
	
	/** The monday relative layout. */
	private RelativeLayout mondayRelativeLayout;
	
	/** The tuesday relative layout. */
	private RelativeLayout tuesdayRelativeLayout;
	
	/** The wednesday relative layout. */
	private RelativeLayout wednesdayRelativeLayout;
	
	/** The thursday relative layout. */
	private RelativeLayout thursdayRelativeLayout;
	
	/** The friday relative layout. */
	private RelativeLayout fridayRelativeLayout;
	
	/** The saturday relative layout. */
	private RelativeLayout saturdayRelativeLayout;
	
	/** The sun date. */
	private TextView sunDate;
	
	/** The mon date. */
	private TextView monDate;
	
	/** The tue date. */
	private TextView tueDate;
	
	/** The wed date. */
	private TextView wedDate;
	
	/** The thu date. */
	private TextView thuDate;
	
	/** The fri date. */
	private TextView friDate;
	
	/** The sat date. */
	private TextView satDate;
	
	/** The sun txt. */
	private TextView[] sunTxt;
	
	/** The mon txt. */
	private TextView[] monTxt;
	
	/** The tue txt. */
	private TextView[] tueTxt;
	
	/** The wed txt. */
	private TextView[] wedTxt;
	
	/** The thu txt. */
	private TextView[] thuTxt;
	
	/** The fri txt. */
	private TextView[] friTxt;
	
	/** The sat txt. */
	private TextView[] satTxt;
	
	/** The sun event. */
	private List<Event> sunEvent;
	
	/** The mon event. */
	private List<Event> monEvent;
	
	/** The tue event. */
	private List<Event> tueEvent;
	
	/** The wed event. */
	private List<Event> wedEvent;
	
	/** The thu event. */
	private List<Event> thuEvent;
	
	/** The fri event. */
	private List<Event> friEvent;
	
	/** The sat event. */
	private List<Event> satEvent;
	
	/** The sun id. */
	private Long sunId;
	
	/** The mon id. */
	private Long monId;
	
	/** The tue id. */
	private Long tueId;
	
	/** The wed id. */
	private Long wedId;
	
	/** The thu id. */
	private Long thuId;
	
	/** The fri id. */
	private Long friId;
	
	/** The sat id. */
	private Long satId;
	
	/** The sunday. */
	private String sunday;
	
	/** The monday. */
	private String monday;
	
	/** The tuesday. */
	private String tuesday;
	
	/** The wednesday. */
	private String wednesday;
	
	/** The thursday. */
	private String thursday;
	
	/** The friday. */
	private String friday;
	
	/** The saturday. */
	private String saturday;
	
	/** The current week. */
	private TextView currentWeek;
	
	/** The week. */
	private int month, year , week;
	
	/** The i. */
	private int i =0;
	
	/** The j. */
	private int j = 0;
	
	/** The min. */
	private int hour, hourdp, min;
	
	/** The Constant dateTemplate. */
	private static final String dateTemplate = "MMM dd";
	
	/** The date format. */
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd");
	
	/** The date format1. */
	private final SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd");
	
	/** The date format s ql. */
	private final SimpleDateFormat dateFormatSQl = new SimpleDateFormat("yyyy-MM-dd");

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
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
		
		layoutInflater = (LayoutInflater) 
		        this.getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
		sundayRelativeLayout = (RelativeLayout) findViewById(R.id.sundayRelativeLayout);
		mondayRelativeLayout = (RelativeLayout) findViewById(R.id.mondayRelativeLayout);
		tuesdayRelativeLayout = (RelativeLayout) findViewById(R.id.tuesdayRelativeLayout);
		wednesdayRelativeLayout = (RelativeLayout) findViewById(R.id.wednesdayRelativeLayout);
		thursdayRelativeLayout = (RelativeLayout) findViewById(R.id.thursdayRelativeLayout);
		fridayRelativeLayout = (RelativeLayout) findViewById(R.id.fridayRelativeLayout);
		saturdayRelativeLayout = (RelativeLayout) findViewById(R.id.saturdayRelativeLayout);
		
		Calendar nextDate = (Calendar) first.clone();
		sunDate = (TextView) this.findViewById(R.id.sundayDateTextView);
		sunDate.setText(dateFormat1.format(nextDate.getTime()));
		sunday = dateFormatSQl.format(nextDate.getTime());
		nextDate.add(Calendar.DAY_OF_YEAR, 1);
		monDate = (TextView) this.findViewById(R.id.mondayDateTextView);
		monDate.setText(dateFormat1.format(nextDate.getTime()));
		monday = dateFormatSQl.format(nextDate.getTime());
		nextDate.add(Calendar.DAY_OF_YEAR, 1);
		tueDate = (TextView) this.findViewById(R.id.tuesdayDateTextView);
		tueDate.setText(dateFormat1.format(nextDate.getTime()));
		tuesday = dateFormatSQl.format(nextDate.getTime());
		nextDate.add(Calendar.DAY_OF_YEAR, 1);
		wedDate = (TextView) this.findViewById(R.id.wednesdayDateTextView);
		wedDate.setText(dateFormat1.format(nextDate.getTime()));
		wednesday = dateFormatSQl.format(nextDate.getTime());
		nextDate.add(Calendar.DAY_OF_YEAR, 1);
		thuDate = (TextView) this.findViewById(R.id.thursdayDateTextView);
		thuDate.setText(dateFormat1.format(nextDate.getTime()));
		thursday = dateFormatSQl.format(nextDate.getTime());
		nextDate.add(Calendar.DAY_OF_YEAR, 1);
		
		friDate = (TextView) this.findViewById(R.id.fridayDateTextView);
		friDate.setText(dateFormat1.format(nextDate.getTime()));
		friday = dateFormatSQl.format(nextDate.getTime());
		
		nextDate.add(Calendar.DAY_OF_YEAR, 1);
		satDate = (TextView) this.findViewById(R.id.saturdayDateTextView);
		satDate.setText(dateFormat1.format(nextDate.getTime()));
		saturday = dateFormatSQl.format(nextDate.getTime());
		prevMonth = (ImageView) this.findViewById(R.id.prevMonth);
		prevMonth.setOnClickListener(this);
		
		nextMonth = (ImageView) this.findViewById(R.id.nextMonth);
		nextMonth.setOnClickListener(this);
		
		hourdp = ((hour - 1) * 120) + (min * 2)   ;
		LinearLayout currentTimeMarkerLinearLayout1 = (LinearLayout) findViewById(R.id.currentTimeMarkerLinearLayout);
		//currentTimeMarkerLinearLayout.setId(100);
		//currentTimeMarkerLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
		android.widget.RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams)currentTimeMarkerLinearLayout1.getLayoutParams();
		layoutParams2.setMargins(0, hourdp, 0, 0);
		
		printWeek(sunday, monday, tuesday, wednesday, thursday, friday, saturday);
    }
	
	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
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
			sunday = dateFormatSQl.format(nextDate.getTime());
			nextDate.add(Calendar.DAY_OF_YEAR, 1);
			//monDate = (TextView) this.findViewById(R.id.mondayDateTextView);
			monDate.setText(dateFormat1.format(nextDate.getTime()));
			monday = dateFormatSQl.format(nextDate.getTime());
			nextDate.add(Calendar.DAY_OF_YEAR, 1);
			//tueDate = (TextView) this.findViewById(R.id.tuesdayDateTextView);
			tueDate.setText(dateFormat1.format(nextDate.getTime()));
			tuesday = dateFormatSQl.format(nextDate.getTime());
			nextDate.add(Calendar.DAY_OF_YEAR, 1);
			//wedDate = (TextView) this.findViewById(R.id.wednesdayDateTextView);
			wedDate.setText(dateFormat1.format(nextDate.getTime()));
			wednesday = dateFormatSQl.format(nextDate.getTime());
			nextDate.add(Calendar.DAY_OF_YEAR, 1);
			//thuDate = (TextView) this.findViewById(R.id.thursdayDateTextView);
			thuDate.setText(dateFormat1.format(nextDate.getTime()));
			thursday = dateFormatSQl.format(nextDate.getTime());
			nextDate.add(Calendar.DAY_OF_YEAR, 1);
			
			//friDate = (TextView) this.findViewById(R.id.fridayDateTextView);
			friDate.setText(dateFormat1.format(nextDate.getTime()));
			friday = dateFormatSQl.format(nextDate.getTime());
			nextDate.add(Calendar.DAY_OF_YEAR, 1);
			//satDate = (TextView) this.findViewById(R.id.saturdayDateTextView);
			satDate.setText(dateFormat1.format(nextDate.getTime()));
			saturday = dateFormatSQl.format(nextDate.getTime());
			Log.d(tag, "Setting Prev Week in WeeklyView: Week:"+week+" "+  " Year: " + year);
			printWeek(sunday, monday, tuesday, wednesday, thursday, friday, saturday);
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
			sunday = dateFormatSQl.format(nextDate.getTime());
			nextDate.add(Calendar.DAY_OF_YEAR, 1);
			//monDate = (TextView) this.findViewById(R.id.mondayDateTextView);
			monDate.setText(dateFormat1.format(nextDate.getTime()));
			monday = dateFormatSQl.format(nextDate.getTime());
			nextDate.add(Calendar.DAY_OF_YEAR, 1);
			//tueDate = (TextView) this.findViewById(R.id.tuesdayDateTextView);
			tueDate.setText(dateFormat1.format(nextDate.getTime()));
			tuesday = dateFormatSQl.format(nextDate.getTime());
			nextDate.add(Calendar.DAY_OF_YEAR, 1);
			//wedDate = (TextView) this.findViewById(R.id.wednesdayDateTextView);
			wedDate.setText(dateFormat1.format(nextDate.getTime()));
			wednesday = dateFormatSQl.format(nextDate.getTime());
			nextDate.add(Calendar.DAY_OF_YEAR, 1);
			//thuDate = (TextView) this.findViewById(R.id.thursdayDateTextView);
			thuDate.setText(dateFormat1.format(nextDate.getTime()));
			thursday = dateFormatSQl.format(nextDate.getTime());
			nextDate.add(Calendar.DAY_OF_YEAR, 1);
			
			//friDate = (TextView) this.findViewById(R.id.fridayDateTextView);
			friDate.setText(dateFormat1.format(nextDate.getTime()));
			friday = dateFormatSQl.format(nextDate.getTime());
			nextDate.add(Calendar.DAY_OF_YEAR, 1);
			//satDate = (TextView) this.findViewById(R.id.saturdayDateTextView);
			satDate.setText(dateFormat1.format(nextDate.getTime()));
			saturday = dateFormatSQl.format(nextDate.getTime());
			Log.d(tag, "Setting Next Week in WeeklyView: Week:"+week+" " + " Year: " + year);
			printWeek(sunday, monday, tuesday, wednesday, thursday, friday, saturday);
		}
		
		
	}
	
	
	/**
	 * Prints the week.
	 *
	 * @param sun the sun
	 * @param mon the mon
	 * @param tue the tue
	 * @param wed the wed
	 * @param thu the thu
	 * @param fri the fri
	 * @param sat the sat
	 */
	public void printWeek(String sun, String mon, String tue, String wed, String thu, String fri, String sat){
		int txtView = 0, txtMon=0,txtTue=0,txtWed=0, txtThu=0,txtFri=0, txtSat=0 ;
		//String[] startTime;
		//String[] endTime;
		int start;
		int end;
		//int top;
		//currentDateRelativeLayout.removeAllViews();
		//currentDateRelativeLayout.addView(layoutInflater.inflate(R.layout.calendar_zebra, null, false),0 );
		sundayRelativeLayout.removeAllViews();
		mondayRelativeLayout.removeAllViews();
		tuesdayRelativeLayout.removeAllViews();
		wednesdayRelativeLayout.removeAllViews();
		thursdayRelativeLayout.removeAllViews();
		fridayRelativeLayout.removeAllViews();
		saturdayRelativeLayout.removeAllViews();
		
		sundayRelativeLayout.addView(layoutInflater.inflate(R.layout.calendar_zebra, null, false),0 );
		mondayRelativeLayout.addView(layoutInflater.inflate(R.layout.calendar_zebra, null, false),0 );
		tuesdayRelativeLayout.addView(layoutInflater.inflate(R.layout.calendar_zebra, null, false),0 );
		wednesdayRelativeLayout.addView(layoutInflater.inflate(R.layout.calendar_zebra, null, false),0 );
		thursdayRelativeLayout.addView(layoutInflater.inflate(R.layout.calendar_zebra, null, false),0 );
		fridayRelativeLayout.addView(layoutInflater.inflate(R.layout.calendar_zebra, null, false),0 );
		saturdayRelativeLayout.addView(layoutInflater.inflate(R.layout.calendar_zebra, null, false),0 );
		sunEvent =  SimpleCalendarView.db.getEvent(sun,sun);
		if(!sunEvent.isEmpty()){
			sunTxt = new TextView[sunEvent.size()];
			for(int i = 0;i<sunEvent.size();i++ ){
				sunTxt[i] = new TextView(this);
			}
			for (Event event : sunEvent) {
				sunId =event.getId();
				String color = SimpleCalendarView.db.getCategoryByEvent(sunId).getColor().toString();
				String[] startTime = event.getStartTime().split(":");
				String[] endTime = event.getEndTime().split(":");
				//start = Integer.parseInt(startTime[0])*60 + Integer.parseInt(startTime[1]);
				//end = Integer.parseInt(endTime[0])*60 + Integer.parseInt(endTime[1]);
				//top = (start)*2;
				Log.d(tag, "inside Sunday");
				int height = calHeight(startTime, endTime);
				int top = calTop(startTime);
				android.widget.RelativeLayout.LayoutParams txtParams1 = new android.widget.RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height);
				txtParams1.setMargins(0, top, 0, 0);
				sunTxt[txtView].setLayoutParams(txtParams1);
				sunTxt[txtView].setText(event.getTitle());
				sunTxt[txtView].setTag(Long.toString(event.getId()));
				//RelativeLayout currentDateRelativeLayout = (RelativeLayout) findViewById(R.id.currentDateRelativeLayout);
				sundayRelativeLayout.addView(sunTxt[txtView]);
				sunTxt[txtView].setBackgroundColor(Color.parseColor(DailyView.findColor(color)));
				//dailytxt[txtView].setTextColor(getResources().getColor(R.color.some_color));
				sunTxt[txtView].setOnClickListener(new OnClickListener() {
				    public void onClick(View view) {
				        // Do something
				    	//String title = (String)view.getTag();
				    	Long clickedID = Long.parseLong((String)view.getTag());
				    	System.out.println(clickedID);
				    	Intent ev= new Intent(WeeklyView.this, EventDetailsActivity.class);
				    	ev.putExtra("activity", (int)3);
				    	ev.putExtra("EVENT_ID", clickedID);
			            startActivity(ev); 
				    }
				});
				txtView++;
			}
		}
		
		
		/////////////////Monday
		
		monEvent =  SimpleCalendarView.db.getEvent(mon,mon);
		if(!monEvent.isEmpty()){
			monTxt = new TextView[monEvent.size()];
			for(int i = 0;i<monEvent.size();i++ ){
				monTxt[i] = new TextView(this);
			}
			for (Event event : monEvent) {
				monId =event.getId();
				String color = SimpleCalendarView.db.getCategoryByEvent(monId).getColor().toString();
				String[] startTime = event.getStartTime().split(":");
				String[] endTime = event.getEndTime().split(":");
				//start = Integer.parseInt(startTime[0])*60 + Integer.parseInt(startTime[1]);
				//end = Integer.parseInt(endTime[0])*60 + Integer.parseInt(endTime[1]);
				//top = (start)*2;
				Log.d(tag, "inside Monday");
				int height = calHeight(startTime, endTime);
				int top = calTop(startTime);
				android.widget.RelativeLayout.LayoutParams txtParams1 = new android.widget.RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height);
				txtParams1.setMargins(0, top, 0, 0);
				monTxt[txtMon].setLayoutParams(txtParams1);
				monTxt[txtMon].setText(event.getTitle());
				monTxt[txtMon].setTag(Long.toString(event.getId()));
				//RelativeLayout currentDateRelativeLayout = (RelativeLayout) findViewById(R.id.currentDateRelativeLayout);
				mondayRelativeLayout.addView(monTxt[txtMon]);
				monTxt[txtMon].setBackgroundColor(Color.parseColor(DailyView.findColor(color)));
				//dailytxt[txtView].setTextColor(getResources().getColor(R.color.some_color));
				monTxt[txtMon].setOnClickListener(new OnClickListener() {
				    public void onClick(View view) {
				        // Do something
				    	//String title = (String)view.getTag();
				    	Long clickedID = Long.parseLong((String)view.getTag());
				    	System.out.println(clickedID);
				    	Intent ev= new Intent(WeeklyView.this, EventDetailsActivity.class);
				    	ev.putExtra("activity", (int)3);
				    	ev.putExtra("EVENT_ID", clickedID);
			            startActivity(ev); 
				    }
				});
				txtMon++;
			}
		}
		
		
		////////Tuesday
		tueEvent =  SimpleCalendarView.db.getEvent(tue,tue);
		if(!tueEvent.isEmpty()){
			tueTxt = new TextView[tueEvent.size()];
			for(int i = 0;i<tueEvent.size();i++ ){
				tueTxt[i] = new TextView(this);
			}
			for (Event event : tueEvent) {
				tueId =event.getId();
				String color = SimpleCalendarView.db.getCategoryByEvent(tueId).getColor().toString();
				String[] startTime = event.getStartTime().split(":");
				String[] endTime = event.getEndTime().split(":");
				//start = Integer.parseInt(startTime[0])*60 + Integer.parseInt(startTime[1]);
				//end = Integer.parseInt(endTime[0])*60 + Integer.parseInt(endTime[1]);
				//top = (start)*2;
				Log.d(tag, "inside Tuesday");
				int height = calHeight(startTime, endTime);
				int top = calTop(startTime);
				android.widget.RelativeLayout.LayoutParams txtParams1 = new android.widget.RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height);
				txtParams1.setMargins(0, top, 0, 0);
				tueTxt[txtTue].setLayoutParams(txtParams1);
				tueTxt[txtTue].setText(event.getTitle());
				tueTxt[txtTue].setTag(Long.toString(event.getId()));
				//RelativeLayout currentDateRelativeLayout = (RelativeLayout) findViewById(R.id.currentDateRelativeLayout);
				tuesdayRelativeLayout.addView(tueTxt[txtTue]);
				tueTxt[txtTue].setBackgroundColor(Color.parseColor(DailyView.findColor(color)));
				//dailytxt[txtView].setTextColor(getResources().getColor(R.color.some_color));
				tueTxt[txtTue].setOnClickListener(new OnClickListener() {
				    public void onClick(View view) {
				        // Do something
				    	//String title = (String)view.getTag();
				    	Long clickedID = Long.parseLong((String)view.getTag());

				    	System.out.println(clickedID);
				    	Intent ev= new Intent(WeeklyView.this, EventDetailsActivity.class);
				    	ev.putExtra("activity", (int)3);
				    	ev.putExtra("EVENT_ID", clickedID);
			            startActivity(ev); 
				    }
				});
				txtTue++;
			}
		}
		
		
		///////////////Wednesday
		
		
		wedEvent =  SimpleCalendarView.db.getEvent(wed,wed);
		if(!wedEvent.isEmpty()){
			wedTxt = new TextView[wedEvent.size()];
			for(int i = 0;i<wedEvent.size();i++ ){
				wedTxt[i] = new TextView(this);
			}
			for (Event event : wedEvent) {
				wedId =event.getId();
				String color = SimpleCalendarView.db.getCategoryByEvent(wedId).getColor().toString();
				String[] startTime = event.getStartTime().split(":");
				String[] endTime = event.getEndTime().split(":");
				//start = Integer.parseInt(startTime[0])*60 + Integer.parseInt(startTime[1]);
				//end = Integer.parseInt(endTime[0])*60 + Integer.parseInt(endTime[1]);
				//top = (start)*2;
				
				Log.d(tag, "inside Wednesday");
				int height = calHeight(startTime, endTime);
				int top = calTop(startTime);
				android.widget.RelativeLayout.LayoutParams txtParams1 = new android.widget.RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height);
				txtParams1.setMargins(0, top, 0, 0);
				wedTxt[txtWed].setLayoutParams(txtParams1);
				wedTxt[txtWed].setText(event.getTitle());
				wedTxt[txtWed].setTag(Long.toString(event.getId()));
				//RelativeLayout currentDateRelativeLayout = (RelativeLayout) findViewById(R.id.currentDateRelativeLayout);
				wednesdayRelativeLayout.addView(wedTxt[txtWed]);
				wedTxt[txtWed].setBackgroundColor(Color.parseColor(DailyView.findColor(color)));
				//dailytxt[txtView].setTextColor(getResources().getColor(R.color.some_color));
				wedTxt[txtWed].setOnClickListener(new OnClickListener() {
				    public void onClick(View view) {
				        // Do something
				    	//String title = (String)view.getTag();
				    	Long clickedID = Long.parseLong((String)view.getTag());
				    	System.out.println(clickedID);
				    	Intent ev= new Intent(WeeklyView.this, EventDetailsActivity.class);
				    	ev.putExtra("activity", (int)3);
				    	ev.putExtra("EVENT_ID", clickedID);
			            startActivity(ev); 
				    }
				});
				txtWed++;
			}
		}
		
		
		//////////////Thursday
		
		
		thuEvent =  SimpleCalendarView.db.getEvent(thu,thu);
		if(!thuEvent.isEmpty()){
			thuTxt = new TextView[thuEvent.size()];
			for(int i = 0;i<thuEvent.size();i++ ){
				thuTxt[i] = new TextView(this);
			}
			for (Event event : thuEvent) {
				thuId =event.getId();
				String color = SimpleCalendarView.db.getCategoryByEvent(thuId).getColor().toString();
				String[] startTime = event.getStartTime().split(":");
				String[] endTime = event.getEndTime().split(":");
				//start = Integer.parseInt(startTime[0])*60 + Integer.parseInt(startTime[1]);
				//end = Integer.parseInt(endTime[0])*60 + Integer.parseInt(endTime[1]);
				//top = (start)*2;
				Log.d(tag, "inside Thursdayday");
				int height = calHeight(startTime, endTime);
				int top = calTop(startTime);
				android.widget.RelativeLayout.LayoutParams txtParams1 = new android.widget.RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height);
				txtParams1.setMargins(0, top, 0, 0);
				thuTxt[txtThu].setLayoutParams(txtParams1);
				thuTxt[txtThu].setText(event.getTitle());
				thuTxt[txtThu].setTag(Long.toString(event.getId()));
				//RelativeLayout currentDateRelativeLayout = (RelativeLayout) findViewById(R.id.currentDateRelativeLayout);
				thursdayRelativeLayout.addView(thuTxt[txtThu]);
				thuTxt[txtThu].setBackgroundColor(Color.parseColor(DailyView.findColor(color)));
				//dailytxt[txtView].setTextColor(getResources().getColor(R.color.some_color));
				thuTxt[txtThu].setOnClickListener(new OnClickListener() {
				    public void onClick(View view) {
				        // Do something
				    	//String title = (String)view.getTag();
				    	Long clickedID = Long.parseLong((String)view.getTag());
				    	System.out.println(clickedID);
				    	Intent ev= new Intent(WeeklyView.this, EventDetailsActivity.class);
				    	ev.putExtra("activity", (int)3);
				    	ev.putExtra("EVENT_ID", clickedID);
			            startActivity(ev); 
				    }
				});
				txtThu++;
			}
		}
		
		
		
		
		//////////////////////Friday
		
		friEvent =  SimpleCalendarView.db.getEvent(fri,fri);
		if(!friEvent.isEmpty()){
			friTxt = new TextView[friEvent.size()];
			for(int i = 0;i<friEvent.size();i++ ){
				friTxt[i] = new TextView(this);
			}
			for (Event event : friEvent) {
				friId =event.getId();
				String color = SimpleCalendarView.db.getCategoryByEvent(friId).getColor().toString();
				String[] startTime = event.getStartTime().split(":");
				String[] endTime = event.getEndTime().split(":");
				//start = Integer.parseInt(startTime[0])*60 + Integer.parseInt(startTime[1]);
				//end = Integer.parseInt(endTime[0])*60 + Integer.parseInt(endTime[1]);
				//top = (start)*2;
				Log.d(tag, "inside Friday");
				int height = calHeight(startTime, endTime);
				int top = calTop(startTime);
				android.widget.RelativeLayout.LayoutParams txtParams1 = new android.widget.RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height);
				txtParams1.setMargins(0, top, 0, 0);
				friTxt[txtFri].setLayoutParams(txtParams1);
				friTxt[txtFri].setText(event.getTitle());
				friTxt[txtFri].setTag(Long.toString(event.getId()));
				//RelativeLayout currentDateRelativeLayout = (RelativeLayout) findViewById(R.id.currentDateRelativeLayout);
				fridayRelativeLayout.addView(friTxt[txtFri]);
				friTxt[txtFri].setBackgroundColor(Color.parseColor(DailyView.findColor(color)));
				//dailytxt[txtView].setTextColor(getResources().getColor(R.color.some_color));
				friTxt[txtFri].setOnClickListener(new OnClickListener() {
				    public void onClick(View view) {
				        // Do something
				    	//String title = (String)view.getTag();
				    	Long clickedID = Long.parseLong((String)view.getTag());
				    	System.out.println(clickedID);
				    	Intent ev= new Intent(WeeklyView.this, EventDetailsActivity.class);
				    	ev.putExtra("activity", (int)3);
				    	ev.putExtra("EVENT_ID", clickedID);
			            startActivity(ev); 
				    }
				});
				txtFri++;
			}
		}
		
		
		
		/////////////////////Saturday
		
		
		satEvent =  SimpleCalendarView.db.getEvent(sat,sat);
		if(!satEvent.isEmpty()){
			satTxt = new TextView[satEvent.size()];
			for(int i = 0;i<satEvent.size();i++ ){
				satTxt[i] = new TextView(this);
			}
			for (Event event : satEvent) {
				satId =event.getId();
				String color = SimpleCalendarView.db.getCategoryByEvent(satId).getColor().toString();
				String[] startTime = event.getStartTime().split(":");
				String[] endTime = event.getEndTime().split(":");
				//start = Integer.parseInt(startTime[0])*60 + Integer.parseInt(startTime[1]);
				//end = Integer.parseInt(endTime[0])*60 + Integer.parseInt(endTime[1]);
				//top = (start)*2;
				Log.d(tag, "inside Saturday");
				int height = calHeight(startTime, endTime);
				int top = calTop(startTime);
				Log.d(tag, "inside Saturday"+Integer.toString(height)+  "  " +Integer.toString(top));
				android.widget.RelativeLayout.LayoutParams txtParams1 = new android.widget.RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height);
				txtParams1.setMargins(0, top, 0, 0);
				satTxt[txtSat].setLayoutParams(txtParams1);
				satTxt[txtSat].setText(event.getTitle());
				satTxt[txtSat].setTag(Long.toString(event.getId()));
				//RelativeLayout currentDateRelativeLayout = (RelativeLayout) findViewById(R.id.calendarRelativeLayout);
				saturdayRelativeLayout.addView(satTxt[txtSat]);
				//currentDateRelativeLayout.addView(satTxt[txtSat]);
				satTxt[txtSat].setBackgroundColor(Color.parseColor(DailyView.findColor(color)));
				//dailytxt[txtView].setTextColor(getResources().getColor(R.color.some_color));
				satTxt[txtSat].setOnClickListener(new OnClickListener() {
				    public void onClick(View view) {
				        // Do something
				    	//String title = (String)view.getTag();
				    	Long clickedID = Long.parseLong((String)view.getTag());
				    	System.out.println(clickedID);
				    	Intent ev= new Intent(WeeklyView.this, EventDetailsActivity.class);
				    	ev.putExtra("activity", (int)3);
				    	ev.putExtra("EVENT_ID", clickedID);
			            startActivity(ev); 
				    }
				});
				txtSat++;
			}
		}
	}
	
	
	/**
	 * Cal height.
	 *
	 * @param startTime the start time
	 * @param endTime the end time
	 * @return the height of the event textview
	 */
	public static int calHeight(String[] startTime, String[] endTime){
		int start;
		int end;
		int height;
		start = Integer.parseInt(startTime[0])*60 + Integer.parseInt(startTime[1]);
		end = Integer.parseInt(endTime[0])*60 + Integer.parseInt(endTime[1]);
		height = (end*2)-(start*2);
		return height;
	}
	
	
	/**
	 * Cal top.
	 *
	 * @param startTime the start time
	 * @return the top margin of event textview
	 */
	public static int calTop(String[] startTime){
		int start;
		int top;
		
		start = Integer.parseInt(startTime[0])*60 + Integer.parseInt(startTime[1]);
		top = start*2;
		return top;
	}

}
