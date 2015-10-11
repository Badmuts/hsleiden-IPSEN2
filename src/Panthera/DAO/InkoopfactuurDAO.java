package Panthera.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Panthera.Models.Inkoopfactuur;

/**
 * 
 * @author Roy
 *
 */
public class InkoopfactuurDAO extends DAO {
	private PreparedStatement newInkoopfactuur;
	
	public InkoopfactuurDAO() throws IllegalAccessException, InstantiationException, SQLException {
		super();
		prepareStatements();
	}
	
	public void prepareStatements() {
		try {
			newInkoopfactuur = conn.prepareStatement("INSERT INTO inkoopfactuur(factuurnummer, vervaldatum, status) VALUES(?,?,?);");
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
	public void setNewFactuurStatement() throws SQLException {
		newInkoopfactuur.setInt(1, 1);
		newInkoopfactuur.setDate(2, new Date(0)); //@TODO get the date in miliseconds.
		newInkoopfactuur.setString(3, "concept");
	}

}
