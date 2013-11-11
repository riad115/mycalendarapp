package com.example.mycalendarapp;

public class Event {
	private long id;
    private String title;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
    private String description;
    private String repeat;
   // private String categoryName;
   // private int categoryID;
 
    public Event(){}
 
    public Event(String title, String sDate, String eDate, String sTime, String eTime, String desc, String repeat) {
        super();
        this.title = title;
        this.startDate = sDate;
        this.endDate = eDate;
        this.startTime = sTime;
        this.endTime = eTime;
        this.description = desc;
        this.repeat = repeat;
    //    this.categoryName = catName;
        /*this.categoryID = getCategoryID(categoryName);
          {
         	Cursor c = db.query( categories,null,"name=?" , new String[ ] {categoryName},null,null,null);
        	return Integer.parseInt(c.getString(0));
          }*/ 
    }
    
    @Override
    public String toString() {
        return "Category [id=" + id + ", title=" + title + ", startDate=" + startDate + ", endDate=" + endDate + "," +
        		   " startTime=" + startTime + ", endTime=" + endTime + ", description=" + description + 
        		//   "," +  " categoryName=" + categoryName + ", categoryID=" + categoryID + 
        				"]";
    }
    
	public void setId(long id) {

		this.id = id;
	}
    public long getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

    public void setTitle(String string) {
		// TODO Auto-generated method stub
		this.title = string;
		
	}
    public String getTitle() {
		// TODO Auto-generated method stub
		return this.title;
	}

	public String getStartDate() {
		// TODO Auto-generated method stub
		return this.startDate;
	}

	public String getEndDate() {
		// TODO Auto-generated method stub
		return this.endDate;
	}

	public String getStartTime() {
		// TODO Auto-generated method stub
		return this.startTime;
	}

	public String getEndTime() {
		// TODO Auto-generated method stub
		return this.endTime;
	}

	public String getDescription() {
		// TODO Auto-generated method stub
		return this.description;
	}
	public String getRepeat() {
		// TODO Auto-generated method stub
		return this.repeat;
	}
	public void setStartDate(String string) {
		// TODO Auto-generated method stub
		this.startDate = string;
		
	}
	

	public void setEndDate(String string) {
		// TODO Auto-generated method stub
		this.endDate = string;
	}

	public void setStartTime(String string) {
		// TODO Auto-generated method stub
		this.startTime = string;
	}

	public void setEndTime(String string) {
		// TODO Auto-generated method stub
		this.endTime = string;
	}

	public void setDescription(String string) {
		// TODO Auto-generated method stub
		this.description = string;
	}
	public void setRepeat(String string) {
		// TODO Auto-generated method stub
		this.repeat = string;
	}
}
