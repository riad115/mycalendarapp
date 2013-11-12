package com.example.mycalendarapp;

import junit.framework.TestCase;

public class TestCategory extends TestCase {
	
	Category Cat=new Category("TestCat","blue");	
	
	public void testGetSetId() {
		Cat.setId(1510);
		assertEquals(Cat.getId(),1510);
	}

	public void testGetSetName() {
		Cat.setName("Shopping");
		assertEquals(Cat.getName(),"Shopping");
	}

	public void testGetSetColor() {
		Cat.setColor("yellow");
		assertEquals(Cat.getColor(),"yellow");
	}

}
