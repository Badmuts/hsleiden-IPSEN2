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
	 * @author Victor
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
	 * @author Victor
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
	 * @author Victor
	 * @return id
	 */
	public int getId() {
		return this.id.get();
	}
	/**
	 * @author Victor
	 * @return bedrijfsnaam
	 */

	public String getBedrijfsnaam() {
		return this.bedrijfsnaam.get();
	}
	/**
	 * @author Victor
	 * @return mailadres
	 */

	public String getMailadres() {
		return this.mailadres.get();
	}
	/**
	 * @author Victor
	 * @return telefoon
	 */

	public String getTelefoon() {
		return this.telefoon.get();
	}
	/**
	 * @author Victor
	 * @return kvk
	 */

	public String getKvK() {
		return this.kvk.get();
	}
	/**
	 * @author Victor
	 * @return BTWNummer
	 */

	public String getBTWNummer() {
		return this.BTWNummer.get();
	}
	/**
	 * @author Victor
	 * @return iBan
	 */

	public String getIban() {
		return this.iBan.get();
	}
	/**
	 * @author Victor
	 * @return BIC
	 */

	public String getBIC() {
		return this.BIC.get();
	}
	/**
	 * @author Victor
	 * @return adres
	 */

	public String getAdres() {
		return this.adres.get();
	}
	/**
	 * @author Victor
	 * @return password
	 */

	public String getPassword() {
		return this.password.get();
	}
	/**
	 * @author Victor
	 * @return host
	 */

	public String getHost() {
		return this.host.get();
	}
	/**
	 * @author Victor
	 * @return port
	 */

	public String getPort() {
		return this.port.get();
	}
	/**
	 * @author Victor
	 * @return boolean hasId
	 */

	public boolean hasId() {
		return (id.get() != 0);
	}

	// setters
	/**
	 * @author Victor
	 * set id
	 * @param id
	 */
	public void setId(int id) {
		this.id.set(id);
	}
	/**
	 * @author Victor
	 * set bedrijfsnaam
	 * @param bedrijfsnaam
	 */
	public void setBedrijfsnaam(String bedrijfsnaam) {
		this.bedrijfsnaam.set(bedrijfsnaam);
	}
	/**
	 * @author Victor
	 * set mailadres
	 * @param mailadres
	 */
	public void setMailadres(String mailadres) {
		this.mailadres.set(mailadres);
	}
	/**
	 * @author Victor
	 * set telefoon
	 * @param telefoon
	 */
	public void setTelefoon(String telefoon) {
		this.telefoon.set(telefoon);
	}
	/**
	 * @author Victor
	 * set kvk
	 * @param kvk
	 */
	public void setKvk(String kvk) {
		this.kvk.set(kvk);
	}
	/**
	 * @author Victor
	 * set BTWNummer
	 * @param btw
	 */
	public void setBTWNummer(String btw) {
		this.BTWNummer.set(btw);
	}
	/**
	 * @author Victor
	 * set iBan
	 * @param iBan
	 */
	public void setIban(String iBan) {
		this.iBan.set(iBan);
	}
	/**
	 * @author Victor
	 * set BIC
	 * @param BIC
	 */
	public void setBIC(String BIC) {
		this.BIC.set(BIC);
	}
	/**
	 * @author Victor
	 * set adres
	 * @param adres
	 */
	public void setAdres(String adres) {
		this.adres.set(adres);
	}
	/**
	 * @author Victor
	 * set passwoord
	 * @param password
	 */
	public void setPassword(String password) {
		this.password.set(password);
	}
	/**
	 * @author Victor
	 * set host
	 * @param host
	 */
	public void setHost(String host) {
		this.host.set(host);
	}
	/**
	 * @author Victor
	 * set port
	 * @param port
	 */
	public void setPort(String port){
		this.port.set(port);
	}

	// Properties
	/**
	 * @author Victor
	 * @return id property
	 */
	public SimpleIntegerProperty idProperty() {
		return id;
	}
	/**
	 * @author Victor
	 * @return bedrijfnaam property
	 */
	public SimpleStringProperty bedrijfsnaamProperty() {
		return bedrijfsnaam;
	}
	/**
	 * @author Victor
	 * @return mailadres property
	 */
	public SimpleStringProperty mailadresProperty() {
		return mailadres;
	}
	/**
	 * @author Victor
	 * @return telefoon property
	 */
	public SimpleStringProperty telefoonProperty() {
		return telefoon;
	}
	/**
	 * @author Victor
	 * @return kvk property
	 */
	public SimpleStringProperty kvkProperty() {
		return kvk;
	}
	/**
	 * @author Victor
	 * @return BTWNummer property
	 */
	public SimpleStringProperty BTWNummerProperty() {
		return BTWNummer;
	}
	/**
	 * @author Victor
	 * @return iBan property
	 */
	public SimpleStringProperty iBanProperty() {
		return iBan;
	}
	/**
	 * @author Victor
	 * @return BIC  property
	 */
	public SimpleStringProperty BICProperty() {
		return BIC;
	}
	/**
	 * @author Victor
	 * @return adres property
	 */
	public SimpleStringProperty adresProperty() {
		return adres;
	}
	/**
	 * @author Victor
	 * @return password property
	 */
	public SimpleStringProperty passwordProperty() {
		return password;
	}
	/**
	 * @author Victor
	 * @return host property
	 */
	public SimpleStringProperty hostProperty() {
		return host;
	}
	/**
	 * @author Victor
	 * @return port property
	 */
	public SimpleStringProperty portProperty() {
		return port;
	}
	

}
