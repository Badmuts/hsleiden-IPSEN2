package Panthera.Controllers;

import java.util.ArrayList;
import java.util.List;

import Panthera.Panthera;
import Panthera.DAO.BestellijstDAO;
import Panthera.Models.Product;
import Panthera.Views.BestellijstenAddView;
import Panthera.Views.BestellijstenSummaryView;
import javafx.stage.Stage;

/**
 * Handles bestelijst logic.
 * @author Roy
 *
 */
public class BestellijstenController extends Controller {
	private ProductenController productenController;
	private BestellijstDAO dao;
	private Stage stage;
	
	public BestellijstenController() {
		this.view = new BestellijstenSummaryView(this);
		this.stage = Panthera.getInstance().getStage();
		try {
			this.productenController = new ProductenController();
			this.dao = new BestellijstDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public BestellijstenAddView openBestellijstenAddView() {
		return new BestellijstenAddView(this, productenController);
	}
	
	public BestellijstenSummaryView openBestellijstenSummaryView() {
		return new BestellijstenSummaryView(this);
	}
	
	public void opslaanBestellijst(List<Product> producten) {
		producten = filterUnselected(producten);
		dao.saveNewBestellijst(producten);
		setView(new BestellijstenSummaryView(this)).show();
	}
	
	/**
	 * Returns a list of all selected products.
	 * @return List<Product>
	 */
	public List<Product> filterUnselected(List<Product> producten) {
		List<Product> filteredList = new ArrayList<>();
		for(Product product : producten) {
			if(product.isActive())
				filteredList.add(product);
		}
		return filteredList;
	}
}
