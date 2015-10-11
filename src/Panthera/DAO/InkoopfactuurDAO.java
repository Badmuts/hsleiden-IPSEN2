package Panthera.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Panthera.Models.Factuur;
import Panthera.Models.Inkoopfactuur;

/**
 * 
 * @author Roy
 *
 */
public class InkoopfactuurDAO extends DAO {
	private PreparedStatement newInkoopfactuur;
	private PreparedStatement linkProduct;
	
	public InkoopfactuurDAO() throws IllegalAccessException, InstantiationException, SQLException {
		super();
		prepareStatements();
	}
	
	public void prepareStatements() {
		try {
			newInkoopfactuur = conn.prepareStatement("INSERT INTO inkoopfactuur(factuurnummer, vervaldatum, status) VALUES(?,?,?);");
			linkProduct = conn.prepareStatement("UPDATE inkoopproduct SET aantal = ? WHERE product_id = ?; INSERT INTO inkoopproduct (factuur_id, product_id, aantal) SELECT ?, ?, ? WHERE NOT EXISTS ( SELECT product_id FROM inkoopproduct WHERE factuur_id = ? AND product_id = ? );");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Create a new inkoopfactuur record in database,
	 * fill inkoopfactuur object with it and return it.
	 * @return
	 */
	public Inkoopfactuur createInkoopfactuur() throws SQLException {
		Inkoopfactuur factuur = new Inkoopfactuur();
		setNewFactuurStatement();
		ResultSet result = newInkoopfactuur.executeQuery();
		while(result.next()) {
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
		newInkoopfactuur.setInt(1, 1);
		newInkoopfactuur.setDate(2, new Date(0)); //@TODO get the date in miliseconds.
		newInkoopfactuur.setString(3, "concept");
	}
	
	private void setNewLinkStatement(int aantal, int product_id, int factuur_id) throws SQLException {
		linkProduct.setInt(1, 7); //aantal
		linkProduct.setInt(2, 3); //product_id
		linkProduct.setInt(3, 1); //factuur_id
		linkProduct.setInt(4, 3); //product_id
		linkProduct.setInt(5,  7); //aantal
		linkProduct.setInt(6, 1); //factuur_id
		linkProduct.setInt(6, 3); //product_id
	}
	
	public void linkProducts(Inkoopfactuur inkoopfactuur, List<Factuur> facturen) {
		for(Factuur factuur : facturen) {
			//dificult query to link products to inkoopfactuur in a neat way.
		}
	}

}
