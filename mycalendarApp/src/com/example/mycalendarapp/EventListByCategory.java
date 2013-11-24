package com.example.mycalendarapp;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;

public class EventListByCategory extends Activity implements OnClickListener{
	
	private Spinner spinCategory;
	/** The all category. */
	private  List<Category> allCategory;
	private  String categary_from_Spinner;
	public static  Category ctg;
	/** The spinner array. */
	private  ArrayList<String> spinnerArray;
	
	private GridView gridView;
	
	/** The adapter. */
	private EventListByCategoryAdapter adapter;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        //TextView textview = new TextView(this);
        //textview.setText("This is Daily tab");
        setContentView(R.layout.calendar_search_category);
        
       // db = new  MySQLiteHelper(getApplicationContext());
        gridView = (GridView) findViewById(R.id.grid_search_category);
        spinCategory = (Spinner) findViewById(R.id.spinner_category);
        
        allCategory = SimpleCalendarView.db.getAllCategories();
        spinnerArray = new ArrayList<String>();
        for (Category category : allCategory) {
            //Log.d("Category Name:"+category.getName(), "ID:"+category.getId()+"Color:"+category.getColor());
        	spinnerArray.add(category.getName());
        }
    	
        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item,
                    spinnerArray);

     // Specify the layout to use when the list of choices appears
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinCategory.setAdapter(spinnerArrayAdapter);
            spinCategory.setOnItemSelectedListener(new OnItemSelectedListener() {
            	
            	public void onItemSelected(AdapterView<?> parent, View view, 
        	            int pos, long id) {
        	        // An item was selected. You can retrieve the selected item using
        	    	int idnum = parent.getId();

        	    	if(idnum == R.id.spinner_category)
        	    	{
        	    	categary_from_Spinner =  parent.getItemAtPosition(pos).toString();
        	    	Log.d("SpinnerListener :", categary_from_Spinner);
        	    	ctg = SimpleCalendarView.db.getCategoryID(categary_from_Spinner); 
        	    	
        	    	adapter = new EventListByCategoryAdapter(getApplicationContext(), ctg);
        	    	adapter.notifyDataSetChanged();
        	        gridView.invalidateViews();
        	         //gridView.setAdapter(new EventListAdapter(this));
        	        gridView.setAdapter(adapter);
        	         //gridView.setOnClickListener(this);
        	         
        	         gridView.setOnItemClickListener(new OnItemClickListener() {
        	             @Override
        	             public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        	  
        	            	 Long clickedID = Long.parseLong((String)v.getTag());
        	                 Intent i = new Intent(getApplicationContext(), EventDetailsActivity.class);
        	                 i.putExtra("activity", (int)11);
        	                 i.putExtra("id", clickedID);
        	                 startActivity(i);
        	                 //EventListByCategory.this.finish();
        	             }
        	         });
        	    	
        	    	}
        	    	
        	    	
        	    }

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}
			});
           
            
            gridView = (GridView) findViewById(R.id.grid_search_category);
            
            //((BaseAdapter) gridView.getAdapter()).notifyDataSetChanged(); 
            
        
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	
	
	 
}
