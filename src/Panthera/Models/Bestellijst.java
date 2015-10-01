package Panthera.Models;

import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;

import javafx.beans.property.SimpleBooleanProperty;

/**
 * Bestellijst represents a bestellijst.
 * @author Roy
 */



public class Bestellijst extends Model {

	private int id;
	private int bestellijst_id;
	private Date date;
	List<Product> producten = new ArrayList<>();
	private String name;
	private SimpleBooleanProperty active;

	public Bestellijst(int bestellijst_id, Date date, List<Product> producten) {
		this.id = bestellijst_id;
		this.date = date;
		this.producten = producten;
	}



	public Bestellijst(int bestellijst_id, String name, Date date) {
		this.id = bestellijst_id;
		this.name = name;
		this.date = date;
		this.active = new SimpleBooleanProperty(false);
	}

	public Bestellijst() {

	}

	public Bestellijst(int bestellijst_id, Date date, String name) {
		this.id = bestellijst_id;
		this.date = date;
		this.name = name;
	}

	public int getBestellijdtId() {
		return this.id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Product> getProducten() {
		return producten;
	}

	public void setProducten(List<Product> producten) {
		this.producten = producten;
	}


	public void addProduct(Product product) {
		this.producten.add(product);
	}

	
	public String getNaam() {
		return this.name;
	}

	
	public boolean isActive() {
		return active.get();
	}
	
	public SimpleBooleanProperty activeProperty() {
		return active;
	}

	@Override
	public String toString() {
		return this.name + " (" + date + ")";

	}
}
