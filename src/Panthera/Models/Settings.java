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
	public int getId() {
		return this.id.get();
	}

	public String getBedrijfsnaam() {
		return this.bedrijfsnaam.get();
	}

	public String getMailadres() {
		return this.mailadres.get();
	}

	public String getTelefoon() {
		return this.telefoon.get();
	}

	public String getKvK() {
		return this.kvk.get();
	}

	public String getBTWNummer() {
		return this.BTWNummer.get();
	}

	public String getIban() {
		return this.iBan.get();
	}

	public String getBIC() {
		return this.BIC.get();
	}

	public String getAdres() {
		return this.adres.get();
	}

	public String getPassword() {
		return this.password.get();
	}

	public String getHost() {
		return this.host.get();
	}

	public String getPort() {
		return this.port.get();
	}

	public boolean hasId() {
		return (id.get() != 0);
	}

	// setters
	public void setId(int id) {
		this.id.set(id);
	}

	public void setBedrijfsnaam(String bedrijfsnaam) {
		this.bedrijfsnaam.set(bedrijfsnaam);
	}

	public void setMailadres(String mailadres) {
		this.mailadres.set(mailadres);
	}

	public void setTelefoon(String string) {
		this.telefoon.set(string);
	}

	public void setKvk(String string) {
		this.kvk.set(string);
	}

	public void setBTWNummer(String string) {
		this.BTWNummer.set(string);
	}

	public void setIban(String iBan) {
		this.iBan.set(iBan);
	}

	public void setBIC(String BIC) {
		this.BIC.set(BIC);
	}

	public void setAdres(String adres) {
		this.adres.set(adres);
	}

	public void setPassword(String password) {
		this.password.set(password);
	}

	public void setHost(String host) {
		this.host.set(host);
	}
	public void setPort(String port){
		this.port.set(port);
	}

	// Properties
	public SimpleIntegerProperty idProperty() {
		return id;
	}

	public SimpleStringProperty bedrijfsnaamProperty() {
		return bedrijfsnaam;
	}

	public SimpleStringProperty mailadresProperty() {
		return mailadres;
	}

	public SimpleStringProperty telefoonProperty() {
		return telefoon;
	}

	public SimpleStringProperty kvkProperty() {
		return kvk;
	}

	public SimpleStringProperty BTWNummerProperty() {
		return BTWNummer;
	}

	public SimpleStringProperty iBanProperty() {
		return iBan;
	}

	public SimpleStringProperty BICProperty() {
		return BIC;
	}

	public SimpleStringProperty adresProperty() {
		return adres;
	}
	public SimpleStringProperty passwordProperty() {
		return password;
	}
	public SimpleStringProperty hostProperty() {
		return host;
	}
	public SimpleStringProperty portProperty() {
		return port;
	}
	

}
