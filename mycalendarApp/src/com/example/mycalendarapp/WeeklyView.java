package com.example.mycalendarapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class WeeklyView extends Activity implements OnClickListener{
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        TextView textview = new TextView(this);
        textview.setText("This is Weekly tab");
        setContentView(textview);
    }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
