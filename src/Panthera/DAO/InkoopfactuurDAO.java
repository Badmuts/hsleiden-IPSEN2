package Panthera.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Panthera.Models.Factuur;
import Panthera.Models.Inkoopfactuur;
import Panthera.Models.Product;

/**
 * 
 * @author Roy
 *
 */
public class InkoopfactuurDAO extends DAO {
	private PreparedStatement getFactuurnummer;
	private PreparedStatement newInkoopfactuur;
	private PreparedStatement getInkoopfactuur;
	private PreparedStatement linkProduct;
	private PreparedStatement getPendingProducts;
	
	public InkoopfactuurDAO() throws IllegalAccessException, InstantiationException, SQLException {
		super();
		prepareStatements();
	}
	
	public void prepareStatements() {
		try {
			getFactuurnummer = conn.prepareStatement("SELECT MAX(factuurnummer) AS factuurnummer FROM inkoopfactuur;");
			newInkoopfactuur = conn.prepareStatement("INSERT INTO inkoopfactuur(factuurnummer, vervaldatum, status) VALUES(?,?,?);");
			getInkoopfactuur = conn.prepareStatement("SELECT * FROM inkoopfactuur WHERE id = ( SELECT MAX(id) AS id FROM inkoopfactuur );");
			linkProduct = conn.prepareStatement("INSERT INTO inkoopproduct (factuur_id, product_id, aantal) VALUES(?, ?, ?);");
			getPendingProducts = conn.prepareStatement("SELECT product.naam, product.id, SUM(aantal) AS aantal FROM tbl_order, product, factuur WHERE product.id = tbl_order.product_id AND factuur.id = tbl_order.factuur_id AND factuur.status = 'pending' GROUP BY product.naam, product.id;");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Create a new inkoopfactuur record in database,
	 * fill inkoopfactuur object with it and return it.
	 * @return Inkoopfactuur
	 */
	public Inkoopfactuur createInkoopfactuur() throws SQLException {
		Inkoopfactuur factuur = new Inkoopfactuur();
		setNewFactuurStatement();
		newInkoopfactuur.executeUpdate();
		ResultSet result = getInkoopfactuur.executeQuery();
		while(result.next()) {
			factuur.setId(result.getInt("id"));
			factuur.setFactuurnummer(result.getInt("factuurnummer"));
			factuur.setVervaldatum(result.getDate("vervaldatum"));
			factuur.setStatus(result.getString("status"));
		}
		return factuur;
	}
	
	/**
	 * Set newInkoopFactuur parameters.
	 * @throws SQLException
	 */
	private void setNewFactuurStatement() throws SQLException {
		int factuurnummer = 0;
		ResultSet result = getFactuurnummer.executeQuery();
		while(result.next()) {
			factuurnummer = result.getInt("factuurnummer") + 1;
		}
		newInkoopfactuur.setInt(1, factuurnummer);
		newInkoopfactuur.setDate(2, new Date(0)); //@TODO get the date in miliseconds.
		newInkoopfactuur.setString(3, "concept");
	}
	
	/**
	 * Set linkProduct parameters.
	 * @param factuur_id
	 * @param product_id
	 * @param aantal
	 * @throws SQLException
	 */
	private void setLinkProductStatement(int factuur_id, int product_id, int aantal) throws SQLException {
		linkProduct.setInt(1, factuur_id); //factuur_id
		linkProduct.setInt(2, product_id); //product_id
		linkProduct.setInt(3, aantal); //aantal
	}
		
	/**
	 * Link product and aantal to inkoopfactuur
	 * Inserts record in inkoopproduct(koppeltabel).
	 * @param inkoopfactuur
	 * @param facturen
	 * @throws SQLException 
	 */
	public void linkProducts(Inkoopfactuur inkoopfactuur, List<Factuur> facturen) throws SQLException {
		ResultSet result = getPendingProducts.executeQuery();
		System.out.println(inkoopfactuur.getId());
		while(result.next()) {
			setLinkProductStatement(inkoopfactuur.getId(), result.getInt("id"), result.getInt("aantal"));
			linkProduct.executeUpdate();
		}
	}

}
