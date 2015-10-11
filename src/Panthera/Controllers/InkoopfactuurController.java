package Panthera.Controllers;

import java.sql.SQLException;
import java.util.List;

import Panthera.DAO.InkoopfactuurDAO;
import Panthera.Models.Factuur;
import Panthera.Models.Inkoopfactuur;

public class InkoopfactuurController extends Controller {
	private InkoopfactuurDAO dao;
	private Inkoopfactuur inkoopfactuur;
	
	public InkoopfactuurController() {
		try {
			this.dao = new InkoopfactuurDAO();
		} catch (IllegalAccessException | InstantiationException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Create new inkoopfactuur and link products in inkoopproduct table.
	 * @param facturen
	 */
	public void generateInkoopfactuur(List<Factuur> facturen) {
		try {
			inkoopfactuur = dao.createInkoopfactuur();
			dao.linkProducts(inkoopfactuur, facturen);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
