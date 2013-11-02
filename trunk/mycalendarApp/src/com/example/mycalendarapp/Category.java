package com.example.mycalendarapp;

public class Category {

	private long id;
    private String name;
    private String color;
 
    public Category(){}
 
    public Category(String name, String color) {
        super();
        this.name = name;
        this.color = color;
    }
 
    @Override
    public String toString() {
        return "Category [id=" + getId() + ", name=" + name + ", color=" + color
                + "]";
    }
    //getters & setters
    public void setId(long parseInt) {

		this.id = parseInt;
	} 
    public long getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

    public void setName(String string) {
		// TODO Auto-generated method stub
		this.name = string;
				
	}
    public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

    public void setColor(String string) {
		// TODO Auto-generated method stub
		
		this.color = string;
	}
    public String getColor() {
		// TODO Auto-generated method stub
		return this.color;
	}
	
}
