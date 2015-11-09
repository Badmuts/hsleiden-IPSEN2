package Panthera.Models;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @author Victor
 * Created on 24-Sep-15.
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
	public int getId(){
		return this.id.get();
	}
	public String getAanhef(){
		return this.aanhef.get();
	}
	public String getVoornaam(){
		return this.voornaam.get();
	}
	public String getTussenvoegsel(){
		return this.tussenvoegsel.get();
	}
	public String getNaam(){
		return this.naam.get();
	}
	public String getAdres(){
		return this.adres.get();
	}
	public String getWoonplaats(){
		return this.woonplaats.get();
	}
	public String getPostcode(){
		return this.postcode.get();
	}
	public String getEmail(){
		return this.email.get();
	}
	public String getTelefoon(){
		return this.telefoon.get();
	}
	public Land getLand(){
		return this.land.get();
	}
	public boolean isActive() {
		return active.get();
	}
	public boolean hasId() {
		return (id.get() != 0);
	}

	//setters
	public void setId(int id){
		this.id.set(id);
	}
	public void setAanhef(String aanhef){
		this.aanhef.set(aanhef);
	}
	public void setVoornaam(String voornaam){
		this.voornaam.set(voornaam);
	}
	public void setTussenvoegsel(String tussenvoegsel){
		this.tussenvoegsel.set(tussenvoegsel);
	}
	public void setNaam(String naam){
		this.naam.set(naam);
	}
	public void setAdres(String adres){
		this.adres.set(adres);
	}
	public void setWoonplaats(String woonplaats){
		this.woonplaats.set(woonplaats);
	}
	public void setPostcode(String postcode){
		this.postcode.set(postcode);
	}
	public void setPresent(SimpleBooleanProperty present) {
		this.present = present;
	}
	public void setEmail(String email){
		this.email.set(email);
	}
	public void setTelefoon(String telefoon){
		this.telefoon.set(telefoon);
	}
	public void setLand(Land land){
		this.land.set(land);
	}
	public void setActive(boolean active) { this.active.set(active); }

	//properties

	public SimpleIntegerProperty idProperty() {
		return id;
	}

	public SimpleStringProperty aanhefProperty() {
		return aanhef;
	}

	public SimpleStringProperty voornaamProperty() {
		return voornaam;
	}

	public SimpleStringProperty tussenvoegselProperty() {
		return tussenvoegsel;
	}

	public SimpleStringProperty naamProperty() {
		return naam;
	}

	public SimpleStringProperty adresProperty() {
		return adres;
	}

	public SimpleStringProperty woonplaatsProperty() {
		return woonplaats;
	}

	public SimpleStringProperty postcodeProperty() {
		return postcode;
	}

	public SimpleStringProperty emailProperty() {
		return email;
	}

	public SimpleStringProperty telefoonProperty() {
		return telefoon;
	}

	public SimpleObjectProperty<Land> landProperty() {
		return land;
	}

	public SimpleBooleanProperty activeProperty() {
		return active;
	}

    @Override public String toString() {
        return voornaam.get() + " " + tussenvoegsel.get() + " " + naam.get() ;
    }

	public SimpleBooleanProperty isPresent() {
		return present;
	}
	
	public boolean isPresentBool() {
		boolean p = present.getValue();
		return p;
	}

}
