package Panthera.Controllers;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

import com.itextpdf.text.DocumentException;

import Panthera.DAO.InkoopfactuurDAO;
import Panthera.Models.Factuur;
import Panthera.Models.Inkoopfactuur;
import Panthera.Models.InkoopfactuurPdf;
import Panthera.Models.InkoopfactuurRegel;

/**
 * 
 * @author Roy
 *
 */
public class InkoopfactuurController extends Controller {
	private InkoopfactuurDAO dao;
	private Inkoopfactuur inkoopfactuur;
	private List<InkoopfactuurRegel> inkoopfactuurRegels;
	
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
			this.inkoopfactuur = dao.createInkoopfactuur();
			this.inkoopfactuurRegels = dao.linkProducts(inkoopfactuur, facturen);
			generatePDF(inkoopfactuurRegels);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void generatePDF(List<InkoopfactuurRegel> inkoopfactuurRegels) {
		try {
			new InkoopfactuurPdf().create(inkoopfactuurRegels);
		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		}
	}

}
