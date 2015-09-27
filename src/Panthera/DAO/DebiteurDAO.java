package Panthera.DAO;


import Panthera.Models.Debiteur;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DebiteurDAO extends DAO {
	
	public DebiteurDAO() throws IllegalAccessException, SQLException, InstantiationException {
		super();
	}


	public Debiteur getDebiteur(int id) throws SQLException {
		Debiteur debiteur = new Debiteur();
		try (Statement stmt = conn.createStatement()) {
			ResultSet result = stmt.executeQuery("SELECT * FROM debiteur WHERE id = " + id);
			while (result.next()) {
				debiteur.setId(result.getInt("id"));
				debiteur.setAanhef(result.getString("aanhef"));
				debiteur.setVoornaam(result.getString("voornaam"));
				debiteur.setTussenvoegsel(result.getString("tussenvoegsel"));
				debiteur.setNaam(result.getString("naam"));
				debiteur.setAdres(result.getString("adres"));
				debiteur.setWoonplaats(result.getString("woonplaats"));
				debiteur.setPostcode(result.getString("postcode"));
				debiteur.setEmail(result.getString("email"));
				debiteur.setTelefoon(result.getInt("telefoon"));
				debiteur.setLand(result.getString(String.valueOf(stmt.executeQuery("SELECT land FROM land WHERE id =" + result.getString("id")))));
			}
		}
		return debiteur;
	}

	public List<Debiteur> getAllDebiteuren() throws SQLException {
		try (Statement stmt = conn.createStatement()) {
			stmt.executeQuery("SELECT * FROM debiteur");
		}
		return null;
	}
	public void deleteDebiteur(int id) throws SQLException {
		try (Statement stmt = conn.createStatement()) {
			stmt.executeQuery("DELETE * FROM debiteur WHERE id =" + id);
		}
	}
	public void addDebiteur(Debiteur debiteur) throws SQLException {
		try (Statement stmt = conn.createStatement()) {
			stmt.executeQuery("INSERT INTO Debiteur VALUES(" + debiteur.getAanhef() + "," + 
					debiteur.getVoornaam() + "," + debiteur.getTussenvoegsel() + "," +
					debiteur.getNaam() + "," + debiteur.getAdres() + "," + debiteur.getWoonplaats() +
					"," + debiteur.getPostcode() + "," + debiteur.getEmail() + "," + debiteur.getTelefoon() + 
					"," + debiteur.getLand() + ")");
					
		}
	}
}