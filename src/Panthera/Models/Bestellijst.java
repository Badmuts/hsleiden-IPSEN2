package Panthera.Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.beans.property.SimpleBooleanProperty;

/**
 * Bestellijst represents a bestellijst.
 * @author Roy
 *
 */
public class Bestellijst {
	private int bestellijst_id;
	private Date date;
	List<Product> producten = new ArrayList<>();
	private SimpleBooleanProperty active;
	
	public Bestellijst(int bestellijst_id, Date date) {
		this.bestellijst_id = bestellijst_id;
		this.date = date;
		this.active = new SimpleBooleanProperty(false);
	}
	
	public int getBestellijstId() {
		return this.bestellijst_id;
	}
	
	public SimpleBooleanProperty activeProperty() {
		return active;
	}
}
