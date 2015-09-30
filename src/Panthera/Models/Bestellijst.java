package Panthera.Models;

import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;

/**
 * Bestellijst represents a bestellijst.
 * @author Roy
 *
 */
public class Bestellijst {
	private int id;
	private Date date;
	List<Product> producten = new ArrayList<>();
	private String name;

	public Bestellijst(int bestellijst_id, Date date, List<Product> producten) {
		this.id = bestellijst_id;
		this.date = date;
		this.producten = producten;
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

	@Override
	public String toString() {
		return this.name + " (" + date + ")";
	}
}
