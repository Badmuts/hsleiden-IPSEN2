package Panthera.DAO;


import Panthera.Models.Bestellijst;
import Panthera.Models.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Roy
 *
 */
public class BestellijstDAO extends DAO {

	public BestellijstDAO() throws IllegalAccessException, InstantiationException, SQLException {
		super();
		// TODO Auto-generated constructor stub
	}

	public Bestellijst get(int id) throws Exception {
		Statement stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery("SELECT * FROM bestellijst b, product_to_bestellijst pb, product p WHERE b.id=" + id + " AND pb.product_id=p.id");
		Bestellijst bestellijst = new Bestellijst();
		while(result.next()) {
			bestellijst.setId(result.getInt("id"));
			bestellijst.setDate(result.getDate("date"));
            bestellijst.setName(result.getString("name"));
			bestellijst.addProduct(new Product(
					result.getInt("id"),
					result.getInt("productnummer"),
					result.getString("naam"),
					result.getInt("jaar"),
					result.getDouble("prijs"),
					result.getString("type"),
					new LandDAO().get(result.getInt("land_id"))));
		}
		return bestellijst;
	}

	public ArrayList<Bestellijst> allWithProducten() throws Exception {
		ArrayList<Bestellijst> bestellijsten = new ArrayList<>();
		Statement stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery("SELECT b.id AS bid, b.date, b.name, p.id AS pid, p.productnummer, p.naam, p.jaar, p.prijs, p.type, p.land_id FROM bestellijst b, product_to_bestellijst pb, product p WHERE pb.bestellijst_id=b.id AND pb.product_id=p.id");
        int lastId = 0;
        Bestellijst bestellijst = new Bestellijst();
		while(result.next()) {

            // Als het laatste id niet gelijk is aan het huidige id
            // leeg dan het bestellijst object.
            // Anders, blijf het bestellijst object vullen met producten.
            if (lastId != result.getInt("bid") && lastId != 0) {
                bestellijsten.add(bestellijst);
                bestellijst = new Bestellijst();
            }

            bestellijst.setId(result.getInt("bid"));
            bestellijst.setDate(result.getDate("date"));
            bestellijst.setName(result.getString("name"));
            bestellijst.addProduct(new Product(
                    result.getInt("pid"),
                    result.getInt("productnummer"),
                    result.getString("naam"),
                    result.getInt("jaar"),
                    result.getDouble("prijs"),
                    result.getString("type"),
                    new LandDAO().get(result.getInt("land_id"))));
            lastId = result.getInt("bid");

		}
        bestellijsten.add(bestellijst);
		return bestellijsten;
	}

	public void saveNewBestellijst(List<Product> producten) {
		try {
			//Get the new bestellijst_id.
			final int bestellijst_id = getNewBestellijstId();
			//Create new bestellijst record.
			newBestellijst(bestellijst_id);

			//Now add each product to this bestellijst_id in bestellijst table.
			insertBestellijstRecords(bestellijst_id, producten);

		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			System.out.println("Wrong bestellijst id.");
			ex.printStackTrace();
		}
	}

	/**
	 * Create a new bestellijst record in database.
	 *
	 * @param bestellijst_id
	 * @throws SQLException
	 */
	public void newBestellijst(int bestellijst_id) throws SQLException {
		Statement stmt = conn.createStatement();
		String query = ("INSERT INTO bestellijst(name) VALUES(" +
				"'bestellijst_" + bestellijst_id + "')");
		System.out.println(query);
		stmt.executeUpdate(query);
	}

	/**
	 * Insert new bestellijst.
	 *
	 * @throws SQLException
	 */
	public void insertBestellijst(int bestellijst_id) throws SQLException {
		Statement stmt = conn.createStatement();
		String query = "INSERT INTO bestellijst(name)" +
				" VALUES(bestellijst_" + bestellijst_id + ")";
		stmt.executeQuery(query);

	}

	/**
	 * Insert each product in bestellijst table with given bestellijst_id.
	 *
	 * @param bestellijst_id
	 * @param producten
	 * @throws SQLException
	 */
	public void insertBestellijstRecords(int bestellijst_id, List<Product> producten) throws SQLException {
		Statement stmt = conn.createStatement();
		for (Product product : producten) {
			String query = (
					"INSERT INTO product_to_bestellijst(bestellijst_id, product_id)" +
							"VALUES(" +
							"'" + bestellijst_id + "', " +
							"'" + product.getId() + "'" +

							")");
			System.out.println(query);
			stmt.executeUpdate(query);
		}
	}

	/**
	 * Get a new free bestellijst_id to use.
	 *
	 * @return int bestellijst_id
	 * @throws SQLException
	 * @throws Exception
	 */
	public int getNewBestellijstId() throws SQLException, Exception {
		int id = 1;
		Statement stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery("SELECT MAX(id) AS id FROM bestellijst");
		while (result.next()) {
			id = result.getInt("id");
		}
		//id never is 0, means it's not set.
		if (id < 0) {
			throw new Exception();
		}
		//New bestellijst_id should be one higher than the current max id.
		id += 1;
		return id;
	}

	
//	public List<Bestellijst> all() throws Exception {
//		ArrayList<Bestellijst> bestellijsten = new ArrayList<>();
//		Statement stmt = conn.createStatement();
//		String query = "SELECT DISTINCT(id), date FROM bestellijst";
//		ResultSet result = stmt.executeQuery(query);
//		while(result.next()) {
//			bestellijsten.add(new Bestellijst());
//		}
//		return bestellijsten;
//	}
	

	public List<Bestellijst> all() throws Exception {
		ArrayList<Bestellijst> bestellijsten = new ArrayList<>();
		Statement stmt = conn.createStatement();

		String query = ("SELECT * FROM bestellijst");
		ResultSet result = stmt.executeQuery(query);
		while (result.next()) {
			bestellijsten.add(new Bestellijst(result.getInt("id"), result.getString("name"), result.getDate("date")));

		}
		return bestellijsten;
	}


	public void deleteBestellijsten(List<Bestellijst> bestellijsten) throws SQLException {
		Statement stmt = conn.createStatement();
		for (Bestellijst bestellijst : bestellijsten) {
			String query = ("DELETE FROM bestellijst_set WHERE "
					+ "bestellijst_id = " + bestellijst.getId() + "\n;");
			query += ("DELETE FROM bestellijst WHERE "
					+ "id = " + bestellijst.getId() + ";");
			stmt.execute(query);

		}

	}

}
