package Panthera.DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Panthera.Models.Inkoopfactuur;

public class InkoopfactuurDAO extends DAO {
	private PreparedStatement newInkoopfactuur;
	
	public InkoopfactuurDAO() throws IllegalAccessException, InstantiationException, SQLException {
		super();
		prepareStatements();
	}
	
	public void prepareStatements() {
		try {
			newInkoopfactuur = conn.prepareStatement("INSERT INTO inkoopfactuur(factuurnummer, factuurdatum, vervaldatum, status) VALUES(?,?,?,?);");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Inkoopfactuur createInkoopfactuur() {
		Inkoopfactuur factuur = new Inkoopfactuur();
		
		return factuur;
	}

}
