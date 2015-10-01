package Panthera.DAO;


import Panthera.Models.Debiteur;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author Victor
 *
 */
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
				//debiteur.setLand(result.getInt(stmt.executeQuery("SELECT land FROM land WHERE id =" + result.getInt("id"))));
			}
		}
		return debiteur;
	}

	public ArrayList<Debiteur> getAllDebiteuren() throws Exception {
		ArrayList<Debiteur> debiteuren = new ArrayList<>();
		Statement stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery("SELECT * FROM debiteur LIMIT 25");

		while(result.next()) {
			debiteuren.add(new Debiteur(
					result.getInt("id"),
					result.getString("aanhef"),
					result.getString("voornaam"),
					result.getString("tussenvoegesel"),
					result.getString("naam"),
					result.getString("adres"),
					result.getString("woonplaats"),
					result.getString("postcode"),
					result.getString("email"),
					result.getInt("telefoon"),
					new LandDAO().get(result.getInt("land_id"))));
		}
			return debiteuren;

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
	public void updateDebiteur(Debiteur debiteur) throws SQLException {
		try (Statement stmt = conn.createStatement()) {
			stmt.executeQuery("UPDATE debiteur SET aanhef= " + debiteur.getAanhef() + 
					",voornaam = " + debiteur.getVoornaam() +
					",tussenvoegsel = " + debiteur.getTussenvoegsel() + 
					",naam = " + debiteur.getNaam() + 
					",adres = " + debiteur.getAdres() + 
					",woonplaats = " + debiteur.getWoonplaats() + 
					",postcode = " + debiteur.getPostcode() + 
					",email = " + debiteur.getEmail() + 
					",telefoon = " + debiteur.getTelefoon() + 
					",land = " + debiteur.getLand() + 
					" WHERE id = " + debiteur.getId());

		}
	}
}