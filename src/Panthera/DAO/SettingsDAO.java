package Panthera.DAO;

import Panthera.Controllers.MainController;
import Panthera.Controllers.SettingsController;
import Panthera.Models.Settings;
import Panthera.Views.SettingsListView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SettingsDAO extends DAO {
	public SettingsDAO() throws Exception {
		super();
	}

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
		}

		return settings;

	}

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
					result.getString("bic")));
		}
		return settings;
	}
	public void saveSettings(Settings settings) throws Exception{
		if (settings.hasId()) {
			updateSettings(settings);
		}else {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO settings(bedrijfsnaam, adres, telefoon, mailadres, kvk, btwnummer, iban, bic) " +
			"VALUES('" + settings.getBedrijfsnaam() + "', '" +
					settings.getAdres() + "', '" +
					settings.getTelefoon() + "', '" +
					settings.getMailadres() + "', '" +
					settings.getKvK() + "', '" +
					settings.getBTWNummer() + "', '" +
					settings.getIban() + "', '" +
					settings.getBIC() +"')");
			
		}
	}

	public void updateSettings(Settings settings) {
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("UPDATE settings " + "SET bedrijfsnaam='" + settings.getBedrijfsnaam() + "', "
					+ "adres='" + settings.getAdres() + "', " + "telefoon='" + settings.getTelefoon() + "', "
					+ "mailadres='" + settings.getMailadres() + "', " + "kvk='" + settings.getKvK() + "',"
					+ "btwnummer='" + settings.getBTWNummer() + "', " + "iban='" + settings.getIban() + "', " + "bic='"
					+ settings.getBIC() + "' " + "WHERE id=" + settings.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

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
