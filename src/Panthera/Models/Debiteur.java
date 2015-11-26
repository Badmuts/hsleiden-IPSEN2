package Panthera.Models;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @author Victor
 * @author Brandon
 * Created on 24-Sep-15.
 * 
 */
public class Debiteur extends Model {


	private SimpleIntegerProperty id;
	private SimpleStringProperty aanhef;
	private SimpleStringProperty voornaam;
	private SimpleStringProperty tussenvoegsel;
	private SimpleStringProperty naam;
	private SimpleStringProperty adres;
	private SimpleStringProperty woonplaats;
	private SimpleStringProperty postcode;
	private SimpleStringProperty email;
	private SimpleStringProperty telefoon;
	private SimpleObjectProperty<Land> land;
	private SimpleBooleanProperty active;
	private SimpleBooleanProperty present;
	
	/**
	 * @author Victor
	 * @author Brandon
	 * Debiteur constructor.
	 * Set alle variabelen.
	 * @param id
	 * @param aanhef
	 * @param voornaam
	 * @param tussenvoegsel
	 * @param naam
	 * @param adres
	 * @param woonplaats
	 * @param postcode
	 * @param email
	 * @param telefoon
	 * @param land
	 */

	public Debiteur(int id, String aanhef, String voornaam, String tussenvoegsel, String naam, String adres, String woonplaats, String postcode, String email, String telefoon, Land land){
		this.id = new SimpleIntegerProperty(id);
		this.aanhef = new SimpleStringProperty(aanhef);
		this.voornaam = new SimpleStringProperty(voornaam);
		this.tussenvoegsel = new SimpleStringProperty(tussenvoegsel);
		this.naam = new SimpleStringProperty(naam);
		this.adres = new SimpleStringProperty(adres);
		this.woonplaats = new SimpleStringProperty(woonplaats);
		this.postcode = new SimpleStringProperty(postcode);
		this.email = new SimpleStringProperty(email);
		this.telefoon = new SimpleStringProperty(telefoon);
		this.land = new SimpleObjectProperty<>(land);
		this.active = new SimpleBooleanProperty(false);
		this.present = new SimpleBooleanProperty(false);
	}
	/**
	 * @author Victor
	 * @author Brandon
	 * Debiteur constructor.
	 * Set alle variabelen van een nieuw lid.
	 * @param aanhef
	 * @param voornaam
	 * @param tussenvoegsel
	 * @param naam
	 * @param adres
	 * @param woonplaats
	 * @param postcode
	 * @param email
	 * @param telefoon
	 * @param land
	 */

	public Debiteur(String aanhef, String voornaam, String tussenvoegsel, String naam, String adres, String woonplaats, String postcode, String email, String telefoon, Land land){
		this.id = new SimpleIntegerProperty();
		this.aanhef = new SimpleStringProperty(aanhef);
		this.voornaam = new SimpleStringProperty(voornaam);
		this.tussenvoegsel = new SimpleStringProperty(tussenvoegsel);
		this.naam = new SimpleStringProperty(naam);
		this.adres = new SimpleStringProperty(adres);
		this.woonplaats = new SimpleStringProperty(woonplaats);
		this.postcode = new SimpleStringProperty(postcode);
		this.email = new SimpleStringProperty(email);
		this.telefoon = new SimpleStringProperty(telefoon);
		this.land = new SimpleObjectProperty<>(land);
		this.active = new SimpleBooleanProperty(false);
		this.present = new SimpleBooleanProperty(false);
	}
	/**
	 * @author Victor
	 * @author Brandon
	 * Debiteur constructor.
	 * Maakt nieuw properties.
	 */
	public Debiteur(){

		this.id = new SimpleIntegerProperty();
		this.aanhef = new SimpleStringProperty();
		this.voornaam = new SimpleStringProperty();
		this.tussenvoegsel = new SimpleStringProperty("");
		this.naam = new SimpleStringProperty();
		this.adres = new SimpleStringProperty();
		this.woonplaats = new SimpleStringProperty();
		this.postcode = new SimpleStringProperty();
		this.email = new SimpleStringProperty();
		this.telefoon = new SimpleStringProperty();
		this.land = new SimpleObjectProperty<>();
		this.active = new SimpleBooleanProperty();

	}
	//getters
	/**
	 * @author Victor
	 * @return id
	 */
	public int getId(){
		return this.id.get();
	}
	/**
	 * @author Victor
	 * @return aanhef
	 */
	public String getAanhef(){
		return this.aanhef.get();
	}
	/**
	 * @author Victor
	 * @return voornaam
	 */
	public String getVoornaam(){
		return this.voornaam.get();
	}
	/**
	 * @author Victor
	 * @return tussenvoegsel
	 */
	public String getTussenvoegsel(){
		return this.tussenvoegsel.get();
	}
	/**
	 * @author Victor
	 * @return naam
	 */
	public String getNaam(){
		return this.naam.get();
	}
	/**
	 * @author Victor
	 * @return adres
	 */
	public String getAdres(){
		return this.adres.get();
	}
	/**
	 * @author Victor
	 * @return woonplaats
	 */
	public String getWoonplaats(){
		return this.woonplaats.get();
	}
	/**
	 * @author Victor
	 * @return postcode
	 */
	public String getPostcode(){
		return this.postcode.get();
	}
	/**
	 * @author Victor
	 * @return email
	 */
	public String getEmail(){
		return this.email.get();
	}
	/**
	 * @author Victor
	 * @return telefoon
	 */
	public String getTelefoon(){
		return this.telefoon.get();
	}
	/**
	 * @author Victor
	 * @return land
	 */
	public Land getLand(){
		return this.land.get();
	}
	/**
	 * @author Victor
	 * @return boolean isActive
	 */
	public boolean isActive() {
		return active.get();
	}
	/**
	 * @author Victor
	 * @return boolean hasId
	 */
	public boolean hasId() {
		return (id.get() != 0);
	}

	//setters
	/**
	 * @author Victor
	 * @param id
	 */
	public void setId(int id){
		this.id.set(id);
	}
	/**
	 * @author Victor
	 * @param aanhef
	 */
	public void setAanhef(String aanhef){
		this.aanhef.set(aanhef);
	}
	/**
	 * @author Victor
	 * @param voornaam
	 */
	public void setVoornaam(String voornaam){
		this.voornaam.set(voornaam);
	}
	/**
	 * @author Victor
	 * @param tussenvoegsel
	 */
	public void setTussenvoegsel(String tussenvoegsel){
		this.tussenvoegsel.set(tussenvoegsel);
	}
	/**
	 * @author Victor
	 * @param naam
	 */
	public void setNaam(String naam){
		this.naam.set(naam);
	}
	/**
	 * @author Victor
	 * @param adres
	 */
	public void setAdres(String adres){
		this.adres.set(adres);
	}
	/**
	 * @author Victor
	 * @param woonplaats
	 */
	public void setWoonplaats(String woonplaats){
		this.woonplaats.set(woonplaats);
	}
	/**
	 * @author Victor
	 * @param postcode
	 */
	public void setPostcode(String postcode){
		this.postcode.set(postcode);
	}
	/**
	 * @author Victor
	 * @param present
	 */
	public void setPresent(SimpleBooleanProperty present) {
		this.present = present;
	}
	/**
	 * @author Victor
	 * @param email
	 */
	public void setEmail(String email){
		this.email.set(email);
	}
	/**
	 * @author Victor
	 * @param telefoon
	 */
	public void setTelefoon(String telefoon){
		this.telefoon.set(telefoon);
	}
	/**
	 * @author Victor
	 * @param land
	 */
	public void setLand(Land land){
		this.land.set(land);
	}
	/**
	 * @author Victor
	 * @param active
	 */
	public void setActive(boolean active) {
		this.active.set(active); 
	}

	//properties
	/**
	 * 
	 * @return id property
	 */
	public SimpleIntegerProperty idProperty() {
		return id;
	}
	/**
	 * 
	 * @return aanhef property
	 */
	public SimpleStringProperty aanhefProperty() {
		return aanhef;
	}
	/**
	 * 
	 * @return voornaam property
	 */
	public SimpleStringProperty voornaamProperty() {
		return voornaam;
	}
	/**
	 * 
	 * @return tussenvoegsel property
	 */
	public SimpleStringProperty tussenvoegselProperty() {
		return tussenvoegsel;
	}
	/**
	 * 
	 * @return naam property
	 */
	public SimpleStringProperty naamProperty() {
		return naam;
	}
	/**
	 * 
	 * @return adres property
	 */
	public SimpleStringProperty adresProperty() {
		return adres;
	}
	 /**
	  * 
	  * @return woonplaats property
	  */
	public SimpleStringProperty woonplaatsProperty() {
		return woonplaats;
	}
	/**
	 * 
	 * @return postcode property
	 */
	public SimpleStringProperty postcodeProperty() {
		return postcode;
	}
	/**
	 * 
	 * @return email property
	 */
	public SimpleStringProperty emailProperty() {
		return email;
	}
	/**
	 * 
	 * @return telefoon property
	 */
	public SimpleStringProperty telefoonProperty() {
		return telefoon;
	}
	/**
	 * 
	 * @return land property
	 */
	public SimpleObjectProperty<Land> landProperty() {
		return land;
	}
	/**
	 * 
	 * @return active property
	 */
	public SimpleBooleanProperty activeProperty() {
		return active;
	}
	/**
	 * 
	 * @return present property
	 */
	public SimpleBooleanProperty isPresent() {
		return present;
	}
	/**
	 * 
	 * @return boolean p isPresent
	 */
	public boolean isPresentBool() {
		boolean p = present.getValue();
		return p;
	}

}
