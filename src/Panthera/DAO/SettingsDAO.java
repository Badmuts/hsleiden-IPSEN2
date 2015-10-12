package Panthera.DAO;

import Panthera.Models.Settings;

import java.sql.ResultSet;
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
			settings.add(new Settings(result.getInt("id"), result.getString("bedrijfsnaam"), result.getString("adres"),
					result.getString("telefoon"), result.getString("mailadres"), result.getString("kvk"),
					result.getString("btwnummer"), result.getString("iban"), result.getString("bic")));
		}
		return settings;
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

}
