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

public class WeeklyView extends Activity implements OnClickListener{
	private static final String tag = "WeeklyView";
	private Calendar _calendar;
	private Button currentMonth;
	private ImageView prevMonth;
	private ImageView nextMonth;
	private LayoutInflater layoutInflater;
	private RelativeLayout sundayRelativeLayout;
	private RelativeLayout mondayRelativeLayout;
	private RelativeLayout tuesdayRelativeLayout;
	private RelativeLayout wednesdayRelativeLayout;
	private RelativeLayout thursdayRelativeLayout;
	private RelativeLayout fridayRelativeLayout;
	private RelativeLayout saturdayRelativeLayout;
	private TextView sunDate;
	private TextView monDate;
	private TextView tueDate;
	private TextView wedDate;
	private TextView thuDate;
	private TextView friDate;
	private TextView satDate;
	private TextView[] sunTxt;
	private TextView[] monTxt;
	private TextView[] tueTxt;
	private TextView[] wedTxt;
	private TextView[] thuTxt;
	private TextView[] friTxt;
	private TextView[] satTxt;
	private List<Event> sunEvent;
	private List<Event> monEvent;
	private List<Event> tueEvent;
	private List<Event> wedEvent;
	private List<Event> thuEvent;
	private List<Event> friEvent;
	private List<Event> satEvent;
	private Long sunId;
	private Long monId;
	private Long tueId;
	private Long wedId;
	private Long thuId;
	private Long friId;
	private Long satId;
	private String sunday;
	private String monday;
	private String tuesday;
	private String wednesday;
	private String thursday;
	private String friday;
	private String saturday;
	private TextView currentWeek;
	private int month, year , week;
	private int i =0;
	private int j = 0;
	private int hour, hourdp, min;
	private static final String dateTemplate = "MMM dd";
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd");
	private final SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd");
	private final SimpleDateFormat dateFormatSQl = new SimpleDateFormat("yyyy-MM-dd");

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
				//RelativeLayout currentDateRelativeLayout = (RelativeLayout) findViewById(R.id.currentDateRelativeLayout);
				sundayRelativeLayout.addView(sunTxt[txtView]);
				sunTxt[txtView].setBackgroundColor(Color.parseColor(DailyView.findColor(color)));
				//dailytxt[txtView].setTextColor(getResources().getColor(R.color.some_color));
				sunTxt[txtView].setOnClickListener(new OnClickListener() {
				    public void onClick(View view) {
				        // Do something
				    	//String title = (String)view.getTag();
				    	System.out.println(sunId);
				    	Intent ev= new Intent(WeeklyView.this, EventDetailsActivity.class);
				    	ev.putExtra("activity", (int)3);
				    	ev.putExtra("EVENT_ID", sunId);
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
				//RelativeLayout currentDateRelativeLayout = (RelativeLayout) findViewById(R.id.currentDateRelativeLayout);
				mondayRelativeLayout.addView(monTxt[txtMon]);
				monTxt[txtMon].setBackgroundColor(Color.parseColor(DailyView.findColor(color)));
				//dailytxt[txtView].setTextColor(getResources().getColor(R.color.some_color));
				monTxt[txtMon].setOnClickListener(new OnClickListener() {
				    public void onClick(View view) {
				        // Do something
				    	//String title = (String)view.getTag();
				    	System.out.println(monId);
				    	Intent ev= new Intent(WeeklyView.this, EventDetailsActivity.class);
				    	ev.putExtra("activity", (int)3);
				    	ev.putExtra("EVENT_ID", monId);
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
				//RelativeLayout currentDateRelativeLayout = (RelativeLayout) findViewById(R.id.currentDateRelativeLayout);
				tuesdayRelativeLayout.addView(tueTxt[txtTue]);
				tueTxt[txtTue].setBackgroundColor(Color.parseColor(DailyView.findColor(color)));
				//dailytxt[txtView].setTextColor(getResources().getColor(R.color.some_color));
				tueTxt[txtTue].setOnClickListener(new OnClickListener() {
				    public void onClick(View view) {
				        // Do something
				    	//String title = (String)view.getTag();
				    	System.out.println(tueId);
				    	Intent ev= new Intent(WeeklyView.this, EventDetailsActivity.class);
				    	ev.putExtra("activity", (int)3);
				    	ev.putExtra("EVENT_ID", tueId);
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
				//RelativeLayout currentDateRelativeLayout = (RelativeLayout) findViewById(R.id.currentDateRelativeLayout);
				wednesdayRelativeLayout.addView(wedTxt[txtWed]);
				wedTxt[txtWed].setBackgroundColor(Color.parseColor(DailyView.findColor(color)));
				//dailytxt[txtView].setTextColor(getResources().getColor(R.color.some_color));
				wedTxt[txtWed].setOnClickListener(new OnClickListener() {
				    public void onClick(View view) {
				        // Do something
				    	//String title = (String)view.getTag();
				    	System.out.println(wedId);
				    	Intent ev= new Intent(WeeklyView.this, EventDetailsActivity.class);
				    	ev.putExtra("activity", (int)3);
				    	ev.putExtra("EVENT_ID", wedId);
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
				//RelativeLayout currentDateRelativeLayout = (RelativeLayout) findViewById(R.id.currentDateRelativeLayout);
				thursdayRelativeLayout.addView(thuTxt[txtThu]);
				thuTxt[txtThu].setBackgroundColor(Color.parseColor(DailyView.findColor(color)));
				//dailytxt[txtView].setTextColor(getResources().getColor(R.color.some_color));
				thuTxt[txtThu].setOnClickListener(new OnClickListener() {
				    public void onClick(View view) {
				        // Do something
				    	//String title = (String)view.getTag();
				    	System.out.println(thuId);
				    	Intent ev= new Intent(WeeklyView.this, EventDetailsActivity.class);
				    	ev.putExtra("activity", (int)3);
				    	ev.putExtra("EVENT_ID", thuId);
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
				//RelativeLayout currentDateRelativeLayout = (RelativeLayout) findViewById(R.id.currentDateRelativeLayout);
				fridayRelativeLayout.addView(friTxt[txtFri]);
				friTxt[txtFri].setBackgroundColor(Color.parseColor(DailyView.findColor(color)));
				//dailytxt[txtView].setTextColor(getResources().getColor(R.color.some_color));
				friTxt[txtFri].setOnClickListener(new OnClickListener() {
				    public void onClick(View view) {
				        // Do something
				    	//String title = (String)view.getTag();
				    	System.out.println(friId);
				    	Intent ev= new Intent(WeeklyView.this, EventDetailsActivity.class);
				    	ev.putExtra("activity", (int)3);
				    	ev.putExtra("EVENT_ID", friId);
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
				//RelativeLayout currentDateRelativeLayout = (RelativeLayout) findViewById(R.id.calendarRelativeLayout);
				saturdayRelativeLayout.addView(satTxt[txtSat]);
				//currentDateRelativeLayout.addView(satTxt[txtSat]);
				satTxt[txtSat].setBackgroundColor(Color.parseColor(DailyView.findColor(color)));
				//dailytxt[txtView].setTextColor(getResources().getColor(R.color.some_color));
				satTxt[txtSat].setOnClickListener(new OnClickListener() {
				    public void onClick(View view) {
				        // Do something
				    	//String title = (String)view.getTag();
				    	System.out.println(satId);
				    	Intent ev= new Intent(WeeklyView.this, EventDetailsActivity.class);
				    	ev.putExtra("activity", (int)3);
				    	ev.putExtra("EVENT_ID", satId);
			            startActivity(ev); 
				    }
				});
				txtSat++;
			}
		}
	}
	
	
	public int calHeight(String[] startTime, String[] endTime){
		int start;
		int end;
		int height;
		start = Integer.parseInt(startTime[0])*60 + Integer.parseInt(startTime[1]);
		end = Integer.parseInt(endTime[0])*60 + Integer.parseInt(endTime[1]);
		height = (end*2)-(start*2);
		return height;
	}
	
	
	public int calTop(String[] startTime){
		int start;
		int top;
		
		start = Integer.parseInt(startTime[0])*60 + Integer.parseInt(startTime[1]);
		top = start*2;
		return top;
	}

}
