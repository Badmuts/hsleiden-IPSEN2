package Panthera.Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Bestellijst represents a bestellijst.
 * @author Roy
 *
 */
public class Bestellijst {
	private int bestellijst_id;
	private Date date;
	List<Product> producten = new ArrayList<>();
	
	public int getBestellijdtId() {
		return this.bestellijst_id;
	}
}
