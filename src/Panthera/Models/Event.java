package Panthera.Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Event represents a wine event.
 * @author Roy
 *
 */
public class Event {
	private int id;
	private Date date;
	private List<Debiteur> aanwezigen = new ArrayList<>();
	
	public Event() {
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
		
}
