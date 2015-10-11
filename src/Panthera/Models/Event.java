package Panthera.Models;

import java.util.Date;

import javafx.beans.property.SimpleBooleanProperty;

/**
 * Event represents a wine event.
 * @author Roy
 *
 */
public class Event {
	private int id;
	private String name;
	private Date date;
	private SimpleBooleanProperty active;
	
	public Event() {
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public SimpleBooleanProperty activeProperty() {
		return this.active;
	}
	
	
}
