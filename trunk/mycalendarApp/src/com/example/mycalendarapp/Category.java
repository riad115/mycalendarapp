package com.example.mycalendarapp;

// TODO: Auto-generated Javadoc
/**
 * The Class Category.
 */
public class Category {

	/** The id. */
	private long id;
    
    /** The name. */
    private String name;
    
    /** The color. */
    private String color;
 
    /**
     * Instantiates a new category.
     */
    public Category(){
    	this.name = null;
    	this.color = null;
    }
 
    /**
     * Instantiates a new category.
     *
     * @param name the name
     * @param color the color
     */
    public Category(String name, String color) {
        super();
        this.name = name;
        this.color = color;
    }
 
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Category [id=" + getId() + ", name=" + name + ", color=" + color
                + "]";
    }
    //getters & setters
    /**
     * Sets the id.
     *
     * @param parseInt the new id
     */
    public void setId(long parseInt) {

		this.id = parseInt;
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
     * Sets the name.
     *
     * @param string the new name
     */
    public void setName(String string) {
		// TODO Auto-generated method stub
		this.name = string;
				
	}
    
    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

    /**
     * Sets the color.
     *
     * @param string the new color
     */
    public void setColor(String string) {
		// TODO Auto-generated method stub
		
		this.color = string;
	}
    
    /**
     * Gets the color.
     *
     * @return the color
     */
    public String getColor() {
		// TODO Auto-generated method stub
		return this.color;
	}
	
}
