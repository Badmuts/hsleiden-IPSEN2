package Panthera.Models;

/**
 * 
 * @author Roy
 *
 */
public class InkoopfactuurRegel {
	private int factuur_id;
	private int product_id;
	private String productnaam;
	private int aantal;
	private double prijs;
	
	public double getPrijs() {
		return prijs;
	}
	public void setPrijs(double prijs) {
		this.prijs = prijs;
	}
	public int getFactuur_id() {
		return factuur_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public String getProductnaam() {
		return productnaam;
	}
	public int getAantal() {
		return aantal;
	}
	public void setFactuur_id(int factuur_id) {
		this.factuur_id = factuur_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public void setProductnaam(String productnaam) {
		this.productnaam = productnaam;
	}
	public void setAantal(int aantal) {
		this.aantal = aantal;
	}
	

}
