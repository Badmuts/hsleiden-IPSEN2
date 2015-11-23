package Panthera.Models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * 
 * @author Victor
 *
 */
public class Settings {

	private SimpleIntegerProperty id;
	private SimpleStringProperty bedrijfsnaam;
	private SimpleStringProperty mailadres;
	private SimpleStringProperty telefoon;
	private SimpleStringProperty kvk;
	private SimpleStringProperty BTWNummer;
	private SimpleStringProperty iBan;
	private SimpleStringProperty BIC;
	private SimpleStringProperty adres;
	private SimpleStringProperty password;
	private SimpleStringProperty host;
	private SimpleStringProperty port;
	/**
	 * Settings constructor. 
	 * Set alle variabelen van een nieuw setting.
	 * @param id
	 * @param bedrijfsnaam
	 * @param adres
	 * @param telefoon
	 * @param mailadres
	 * @param kvk
	 * @param BTWNummer
	 * @param iBan
	 * @param BIC
	 * @param password
	 * @param host
	 * @param port
	 */

	public Settings(int id, String bedrijfsnaam, String adres, String telefoon, String mailadres, String kvk,
			String BTWNummer, String iBan, String BIC, String password, String host, String port) {
		this.id = new SimpleIntegerProperty(id);
		this.bedrijfsnaam = new SimpleStringProperty(bedrijfsnaam);
		this.mailadres = new SimpleStringProperty(mailadres);
		this.telefoon = new SimpleStringProperty(telefoon);
		this.kvk = new SimpleStringProperty(kvk);
		this.BTWNummer = new SimpleStringProperty(BTWNummer);
		this.iBan = new SimpleStringProperty(iBan);
		this.BIC = new SimpleStringProperty(BIC);
		this.adres = new SimpleStringProperty(adres);
		this.password = new SimpleStringProperty(password);
		this.host = new SimpleStringProperty(host);
		this.port = new SimpleStringProperty(port);
	}
	/**
	 * Settings constructor. 
	 * Maakt nieuwe properties.
	 */
	public Settings() {
		this.id = new SimpleIntegerProperty();
		this.bedrijfsnaam = new SimpleStringProperty();
		this.mailadres = new SimpleStringProperty();
		this.telefoon = new SimpleStringProperty();
		this.kvk = new SimpleStringProperty();
		this.BTWNummer = new SimpleStringProperty();
		this.iBan = new SimpleStringProperty();
		this.BIC = new SimpleStringProperty();
		this.adres = new SimpleStringProperty();
		this.password = new SimpleStringProperty();
		this.host = new SimpleStringProperty();
		this.port = new SimpleStringProperty();
	}

	// getters
	/**
	 * 
	 * @return id
	 */
	public int getId() {
		return this.id.get();
	}
	/**
	 * 
	 * @return bedrijfsnaam
	 */

	public String getBedrijfsnaam() {
		return this.bedrijfsnaam.get();
	}
	/**
	 * 
	 * @return mailadres
	 */

	public String getMailadres() {
		return this.mailadres.get();
	}
	/**
	 * 
	 * @return telefoon
	 */

	public String getTelefoon() {
		return this.telefoon.get();
	}
	/**
	 * 
	 * @return kvk
	 */

	public String getKvK() {
		return this.kvk.get();
	}
	/**
	 * 
	 * @return BTWNummer
	 */

	public String getBTWNummer() {
		return this.BTWNummer.get();
	}
	/**
	 * 
	 * @return iBan
	 */

	public String getIban() {
		return this.iBan.get();
	}
	/**
	 * 
	 * @return BIC
	 */

	public String getBIC() {
		return this.BIC.get();
	}
	/**
	 * 
	 * @return adres
	 */

	public String getAdres() {
		return this.adres.get();
	}
	/**
	 * 
	 * @return password
	 */

	public String getPassword() {
		return this.password.get();
	}
	/**
	 * 
	 * @return host
	 */

	public String getHost() {
		return this.host.get();
	}
	/**
	 * 
	 * @return port
	 */

	public String getPort() {
		return this.port.get();
	}
	/**
	 * 
	 * @return boolean hasId
	 */

	public boolean hasId() {
		return (id.get() != 0);
	}

	// setters
	/**
	 * set id
	 * @param id
	 */
	public void setId(int id) {
		this.id.set(id);
	}
	/**
	 * set bedrijfsnaam
	 * @param bedrijfsnaam
	 */
	public void setBedrijfsnaam(String bedrijfsnaam) {
		this.bedrijfsnaam.set(bedrijfsnaam);
	}
	/**
	 * set mailadres
	 * @param mailadres
	 */
	public void setMailadres(String mailadres) {
		this.mailadres.set(mailadres);
	}
	/**
	 * set telefoon
	 * @param telefoon
	 */
	public void setTelefoon(String telefoon) {
		this.telefoon.set(telefoon);
	}
	/**
	 * set kvk
	 * @param kvk
	 */
	public void setKvk(String kvk) {
		this.kvk.set(kvk);
	}
	/**
	 * set BTWNummer
	 * @param btw
	 */
	public void setBTWNummer(String btw) {
		this.BTWNummer.set(btw);
	}
	/**
	 * set iBan
	 * @param iBan
	 */
	public void setIban(String iBan) {
		this.iBan.set(iBan);
	}
	/**
	 * set BIC
	 * @param BIC
	 */
	public void setBIC(String BIC) {
		this.BIC.set(BIC);
	}
	/**
	 * set adres
	 * @param adres
	 */
	public void setAdres(String adres) {
		this.adres.set(adres);
	}
	/**
	 * set passwoord
	 * @param password
	 */
	public void setPassword(String password) {
		this.password.set(password);
	}
	/**
	 * set host
	 * @param host
	 */
	public void setHost(String host) {
		this.host.set(host);
	}
	/**
	 * set port
	 * @param port
	 */
	public void setPort(String port){
		this.port.set(port);
	}

	// Properties
	/**
	 * 
	 * @return id property
	 */
	public SimpleIntegerProperty idProperty() {
		return id;
	}
	/**
	 * 
	 * @return bedrijfnaam property
	 */
	public SimpleStringProperty bedrijfsnaamProperty() {
		return bedrijfsnaam;
	}
	/**
	 * 
	 * @return mailadres property
	 */
	public SimpleStringProperty mailadresProperty() {
		return mailadres;
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
	 * @return kvk property
	 */
	public SimpleStringProperty kvkProperty() {
		return kvk;
	}
	/**
	 * 
	 * @return BTWNummer property
	 */
	public SimpleStringProperty BTWNummerProperty() {
		return BTWNummer;
	}
	/**
	 * 
	 * @return iBan property
	 */
	public SimpleStringProperty iBanProperty() {
		return iBan;
	}
	/**
	 * 
	 * @return BIC  property
	 */
	public SimpleStringProperty BICProperty() {
		return BIC;
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
	 * @return password property
	 */
	public SimpleStringProperty passwordProperty() {
		return password;
	}
	/**
	 * 
	 * @return host property
	 */
	public SimpleStringProperty hostProperty() {
		return host;
	}
	/**
	 * 
	 * @return port property
	 */
	public SimpleStringProperty portProperty() {
		return port;
	}
	

}
