package com.example.mycalendarapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class EventListAdapter extends BaseAdapter{
	
	private Context eContext;
	static final String[] numbers = new String[] { 
			"A", "B", "C", "D", "E",
			"F", "G", "H", "I", "J",
			"K", "L", "M", "N", "O",
			"P", "Q", "R", "S", "T",
			"U", "V", "W", "X", "Y", "Z"};
	
	
	public EventListAdapter (Context c){
		eContext =c;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return numbers.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return numbers[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		TextView txt1 = new TextView(eContext);
		txt1.setText(numbers[position]);
		txt1.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.MATCH_PARENT, GridView.LayoutParams.WRAP_CONTENT));
		return txt1;
	}

}
