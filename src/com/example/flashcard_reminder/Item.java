package com.example.flashcard_reminder;

import java.text.SimpleDateFormat;
import java.util.Date;



public class Item {
 
    private String title;
    private String description;
    private long datetime;
 
    public String getDatetime() {
    	SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss:SSS dd.MM.yyyy");
    	return formatter.format(datetime);
	}
	public void setDatetime(long datetime) {
		this.datetime = datetime;
	}
	public Item(String title, String description) {
        super();
        this.title = title;
        this.description = description;
        this.datetime = new Date().getTime();
    }
    // getters and setters...   
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
    
}