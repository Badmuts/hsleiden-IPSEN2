package Panthera.DAO;


import Panthera.Models.Debiteur;

import javax.swing.plaf.nimbus.State;
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
	
	public DebiteurDAO() throws Exception {
		super();
	}


	public Debiteur getDebiteur(int id) throws Exception {
		Debiteur debiteur = new Debiteur();
		Statement stmt = conn.createStatement();
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
				debiteur.setTelefoon(result.getString("telefoon"));
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
					result.getString("tussenvoegsel"),
					result.getString("naam"),
					result.getString("adres"),
					result.getString("woonplaats"),
					result.getString("postcode"),
					result.getString("email"),
					result.getString("telefoon"),
					new LandDAO().get(result.getInt("land_id"))));
		}
			return debiteuren;

	}
	public void deleteDebiteur(Debiteur debiteur) throws Exception {
		Statement stmt = conn.createStatement();
		stmt.executeUpdate("DELETE FROM debiteur " +
			"WHERE id=" + debiteur.getId());
	}
	public void addDebiteur(Debiteur debiteur) throws Exception {
		if(debiteur.hasId()) {
			updateDebiteur(debiteur);
		} else {
			Statement stmt = conn.createStatement(); {
			stmt.executeUpdate("" + "INSERT INTO debiteur(aanhef, voornaam, tussenvoegsel, naam, adres, woonplaats, postcode, email, telefoon, land_id) " +
					"VALUES(" + "'" +
					debiteur.getAanhef() + "','" +
					debiteur.getVoornaam() + "','" +
					debiteur.getTussenvoegsel() + "','" +
					debiteur.getNaam() + "','" +
					debiteur.getAdres() + "','" +
					debiteur.getWoonplaats() + "','" +
					debiteur.getPostcode() + "','" +
					debiteur.getEmail() + "','" +
					debiteur.getTelefoon() + "'," +
					debiteur.getLand().getId() + ")");
			}
		}
	}
	public void updateDebiteur(Debiteur debiteur)  {
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("UPDATE debiteur " +
				"SET aanhef='" + debiteur.getAanhef() + "', " +
					"voornaam='" + debiteur.getVoornaam() + "', " +
					"tussenvoegsel ='" + debiteur.getTussenvoegsel() + "', " +
					"naam='" + debiteur.getNaam() + "', " +
					"adres='" + debiteur.getAdres() + "', " +
					"woonplaats='" + debiteur.getWoonplaats() + "', " +
					"postcode='" + debiteur.getPostcode() + "', " +
					"email='" + debiteur.getEmail() + "', " +
					"telefoon='" + debiteur.getTelefoon() + "', " +
					"land_id=" + debiteur.getLand().getId() + " " +
					"WHERE id=" + debiteur.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}