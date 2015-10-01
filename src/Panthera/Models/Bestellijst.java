package Panthera.Models;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import javafx.beans.property.SimpleBooleanProperty;

/**
 * Bestellijst represents a bestellijst.
 * @author Roy
 *
 */
public class Bestellijst {
	private int id;
	private Date date;
	private String name;
	List<Product> producten = new ArrayList<>();
	private SimpleBooleanProperty active;
	
	public Bestellijst(int bestellijst_id, String name, Date date) {
		this.id = bestellijst_id;
		this.name = name;
		this.date = date;
		this.active = new SimpleBooleanProperty(false);
	}
	
	public String getNaam() {
		return this.name;
	}

	public int getId() {
		return this.id;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public boolean isActive() {
		return active.get();
	}
	
	public SimpleBooleanProperty activeProperty() {
		return active;
	}
}
