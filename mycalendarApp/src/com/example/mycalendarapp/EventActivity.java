package com.example.mycalendarapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class EventActivity extends Activity implements OnClickListener{
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        TextView textview = new TextView(this);
        textview.setText("This is Event tab");
        setContentView(R.layout.calendar_event);
    }
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}

}
