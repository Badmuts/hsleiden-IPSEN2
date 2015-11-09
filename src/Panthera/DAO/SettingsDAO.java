package Panthera.DAO;

import Panthera.Models.Settings;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
/**
 * In deze klasse zijn de queries om met de database te communiceren
 * De SettingsDAO maakt
 * @author Victor
 *
 */
public class SettingsDAO extends DAO {
	public SettingsDAO() throws Exception {
		super();
	}
	/**
	 * geeft een Settings object terug waarvan de id overeenkomt met de param id.
	 * 
	 * @param id	een id van de desbetreffende setting als een integer
	 * @return settings
	 * @throws Exception
	 */

	public Settings getSettings(int id) throws Exception {
		Settings settings = new Settings();
		Statement stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery("SELECT * FROM settings WHERE id = " + id);
		while (result.next()) {
			settings.setBedrijfsnaam(result.getString("bedrijfsnaam"));
			settings.setAdres(result.getString("adres"));
			settings.setTelefoon(result.getString("telefoon"));
			settings.setMailadres(result.getString("mailadres"));
			settings.setKvk(result.getString("kvk"));
			settings.setBTWNummer(result.getString("btwnummer"));
			settings.setIban(result.getString("iban"));
			settings.setBIC(result.getString("bic"));
			settings.setPassword(result.getString("wachtwoord"));
			settings.setHost(result.getString("host"));
			settings.setPort(result.getString("port"));
		}

		return settings;

	}
	/**
	 * Returns een arraylist bestaand uit alle opgeslagen Settings objecten.
	 * 
	 * @return settings arraylist met alle settings die opgeslagen zijn in de database.
	 * @throws Exception
	 */

	public ArrayList<Settings> getAllSettings() throws Exception {
		ArrayList<Settings> settings = new ArrayList<>();
		Statement stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery("SELECT * FROM settings");
		while (result.next()) {
			settings.add(new Settings(result.getInt("id"),
					result.getString("bedrijfsnaam"),
					result.getString("adres"),
					result.getString("telefoon"),
					result.getString("mailadres"),
					result.getString("kvk"),
					result.getString("btwnummer"),
					result.getString("iban"),
					result.getString("bic"),
					result.getString("wachtwoord"),
					result.getString("host"),
					result.getString("port")));
		}
		return settings;
	}
	/**
	 * Slaat nieuw settings op in de database.
	 * 
	 * @param settings	nieuw settings object.
	 * @throws Exception
	 */
	
	public void saveSettings(Settings settings) throws Exception{
		if (settings.hasId()) {
			updateSettings(settings);
		}else {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO settings(bedrijfsnaam, adres, telefoon, mailadres, kvk, btwnummer, iban, bic, wachtwoord, host, port) " +
			"VALUES('" + settings.getBedrijfsnaam() + "', '" +
					settings.getAdres() + "', '" +
					settings.getTelefoon() + "', '" +
					settings.getMailadres() + "', '" +
					settings.getKvK() + "', '" +
					settings.getBTWNummer() + "', '" +
					settings.getIban() + "', '" +
					settings.getBIC() + "', '"+
					settings.getPassword() + "', '"+
					settings.getHost() + "', '"+
					settings.getPort() + "')");
			
		}
	}
	/**
	 * Werkt opgeslagen settings in de database bij aan de hand van een gewijzigde settings object.
	 * 
	 * @param settings	gewijzigde settings.
	 */

	public void updateSettings(Settings settings) {
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("UPDATE settings " + "SET bedrijfsnaam='" + settings.getBedrijfsnaam() + "', "
					+ "adres='" + settings.getAdres() + "', " + "telefoon='" + settings.getTelefoon() + "', "
					+ "mailadres='" + settings.getMailadres() + "', " + "kvk='" + settings.getKvK() + "',"
					+ "btwnummer='" + settings.getBTWNummer() + "', " + "iban='" + settings.getIban() + "', " 
					+ "bic='" + settings.getBIC() + "', " + "wachtwoord='" + settings.getPassword() + "', "
					+ "host='" + settings.getHost() + "', " + "port='" + settings.getPort() + "' " 
					+ "WHERE id=" + settings.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 * Verwijdert setting uit de database aan de hand van een settings object.
	 * @param settings	settings object die in de database voorkomt.
	 */

	public void removeSettings(Settings settings) {
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM settings " 
					+ "WHERE id = " + settings.getId());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
