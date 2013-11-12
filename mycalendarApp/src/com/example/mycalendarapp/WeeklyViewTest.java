package com.example.mycalendarapp;

import junit.framework.TestCase;

public class WeeklyViewTest extends TestCase {

	WeeklyView wkView ;
	public void TestcalTop(){
		
		String[] stTime = {Integer.toString(2),Integer.toString(15)};
		//String[] eTime = {Integer.toString(20),Integer.toString(15)};
		int top = wkView.calTop(stTime);
		assertEquals(135,top );
		
	}
	
	
	public void TestcalHeight(){
		String[] stTime = {Integer.toString(2),Integer.toString(15)};
		//tring[] eTime = {Integer.toString(2),Integer.toString(15)};
	}
}
