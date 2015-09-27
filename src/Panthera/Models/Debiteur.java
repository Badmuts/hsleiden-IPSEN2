package Panthera.Models;

import Panthera.DAO.DebiteurDAO;

/**
 * Created by Victor on 24-Sep-15.
 */
public class Debiteur {

	private int id;
	private String aanhef;
	private String voornaam;
	private String tussenvoegsel;
	private String naam;
	private String adres;
	private String woonplaats;
	private String postcode;
	private String email;
	private int telefoon;
	private String land;
	DebiteurDAO debiteurDAO;

	public Debiteur(int id, String aanhef, String voornaam, String tussenvoegsel, String naam, 
					String adres, String woonplaats, String postcode, String email, int telefoon, String land){
		this.id = id;
		this.aanhef = aanhef;
		this.voornaam = voornaam;
		this.tussenvoegsel = tussenvoegsel;
		this.naam = naam;
		this.adres = adres;
		this.woonplaats = woonplaats;
		this.postcode = postcode;
		this.email = email;
		this.telefoon = telefoon;
		this.land = land;
	}
	
	public Debiteur(){
	
	}
	//getters
	public int getId(){
		return this.id;
	}
	public String getAanhef(){
		return this.aanhef;
	}
	public String getVoornaam(){
		return this.voornaam;
	}
	public String getTussenvoegsel(){
		return this.tussenvoegsel;
	}
	public String getNaam(){
		return this.naam;
	}
	public String getAdres(){
		return this.adres;
	}
	public String getWoonplaats(){
		return this.woonplaats;
	}
	public String getPostcode(){
		return this.postcode;
	}
	public String getEmail(){
		return this.email;
	}
	public int getTelefoon(){
		return this.telefoon;
	}
	public String getLand(){
		return this.land;
	}
	//setters
	public void setId(int id){
		this.id = id;
	}
	public void setAanhef(String aanhef){
		this.aanhef = aanhef;
	}
	public void setVoornaam(String voornaam){
		this.voornaam = voornaam;
	}
	public void setTussenvoegsel(String tussenvoegsel){
		this.tussenvoegsel = tussenvoegsel;
	}
	public void setNaam(String naam){
		this.naam = naam;
	}
	public void setAdres(String adres){
		this.adres = adres;
	}
	public void setWoonplaats(String woonplaats){
		this.woonplaats = woonplaats;
	}
	public void setPostcode(String postcode){
		this.postcode = postcode;
	}
	public void setEmail(String email){
		this.email = email;
	}
	public void setTelefoon(int telefoon){
		this.telefoon = telefoon;
	}
	public void setLand(String land){
		this.land = land;
	}
}
