package Panthera.DAO;


import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Panthera.Models.Debiteur;
import Panthera.Models.Event;
/**
 * In deze klasse zijn de queries om met de database te communiceren
 * @author Victor
 *
 */
public class DebiteurDAO extends DAO {
	
	public DebiteurDAO() throws Exception {
		super();
	}
	/**
	 * 
	 * @param id
	 * @return debiteur
	 * @throws Exception
	 */

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
	/**
	 * Geeft alle debiteuren terug die in de database staan.
	 * @return debiteuren arrayList met alle debiteuren opgeslagen in de database
	 * @throws Exception
	 */

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
	/**
	 * Verwijdert alle gegevens uit de database van een bepaalde debiteur.
	 * @param debiteur
	 * @throws Exception
	 */
	public void deleteDebiteur(Debiteur debiteur) throws Exception {
		Statement stmt = conn.createStatement();
		stmt.executeUpdate("DELETE FROM debiteur " +
			"WHERE id=" + debiteur.getId());
	}
	/**
	 * Voegt een nieuw debiteur toe aan de database aan de hand van een debiteur object.
	 * @param debiteur
	 * @throws Exception
	 */
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
	/**
	 * Werkt gegevens van een debiteur bij in de database
	 * aan de hand van de desbetreffende debieur object met nieuw gegevens
	 * @param debiteur
	 */
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