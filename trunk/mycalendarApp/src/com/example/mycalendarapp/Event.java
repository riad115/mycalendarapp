package com.example.mycalendarapp;

// TODO: Auto-generated Javadoc
/**
 * The Class Event for the databse table Event
 */
public class Event {
	
	/** The id. */
	private long id;
    
    /** The title. */
    private String title;
    
    /** The start date. */
    private String startDate;
    
    /** The end date. */
    private String endDate;
    
    /** The start time. */
    private String startTime;
    
    /** The end time. */
    private String endTime;
    
    /** The description. */
    private String description;
    
    /** The repeat. */
    private String repeat;
   // private String categoryName;
   // private int categoryID;
 
    /**
    * Instantiates a new event.
    */
   public Event(){}
 
    /**
     * Instantiates a new event.
     *
     * @param title the title
     * @param sDate the s date
     * @param eDate the e date
     * @param sTime the s time
     * @param eTime the e time
     * @param desc the desc
     * @param repeat the repeat
     */
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
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Category [id=" + id + ", title=" + title + ", startDate=" + startDate + ", endDate=" + endDate + "," +
        		   " startTime=" + startTime + ", endTime=" + endTime + ", description=" + description + 
        		//   "," +  " categoryName=" + categoryName + ", categoryID=" + categoryID + 
        				"]";
    }
    
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(long id) {

		this.id = id;
	}
    
    /**
     * Gets the id.
     *
     * @return the id
     */
    public long getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

    /**
     * Sets the title.
     *
     * @param string the new title
     */
    public void setTitle(String string) {
		// TODO Auto-generated method stub
		this.title = string;
		
	}
    
    /**
     * Gets the title.
     *
     * @return the title
     */
    public String getTitle() {
		// TODO Auto-generated method stub
		return this.title;
	}

	/**
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	public String getStartDate() {
		// TODO Auto-generated method stub
		return this.startDate;
	}

	/**
	 * Gets the end date.
	 *
	 * @return the end date
	 */
	public String getEndDate() {
		// TODO Auto-generated method stub
		return this.endDate;
	}

	/**
	 * Gets the start time.
	 *
	 * @return the start time
	 */
	public String getStartTime() {
		// TODO Auto-generated method stub
		return this.startTime;
	}

	/**
	 * Gets the end time.
	 *
	 * @return the end time
	 */
	public String getEndTime() {
		// TODO Auto-generated method stub
		return this.endTime;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		// TODO Auto-generated method stub
		return this.description;
	}
	
	/**
	 * Gets the repeat.
	 *
	 * @return the repeat
	 */
	public String getRepeat() {
		// TODO Auto-generated method stub
		return this.repeat;
	}
	
	/**
	 * Sets the start date.
	 *
	 * @param string the new start date
	 */
	public void setStartDate(String string) {
		// TODO Auto-generated method stub
		this.startDate = string;
		
	}
	

	/**
	 * Sets the end date.
	 *
	 * @param string the new end date
	 */
	public void setEndDate(String string) {
		// TODO Auto-generated method stub
		this.endDate = string;
	}

	/**
	 * Sets the start time.
	 *
	 * @param string the new start time
	 */
	public void setStartTime(String string) {
		// TODO Auto-generated method stub
		this.startTime = string;
	}

	/**
	 * Sets the end time.
	 *
	 * @param string the new end time
	 */
	public void setEndTime(String string) {
		// TODO Auto-generated method stub
		this.endTime = string;
	}

	/**
	 * Sets the description.
	 *
	 * @param string the new description
	 */
	public void setDescription(String string) {
		// TODO Auto-generated method stub
		this.description = string;
	}
	
	/**
	 * Sets the repeat.
	 *
	 * @param string the new repeat
	 */
	public void setRepeat(String string) {
		// TODO Auto-generated method stub
		this.repeat = string;
	}
}
