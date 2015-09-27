package Panthera.Controllers;

import Panthera.Views.BestellijstenAddView;
import Panthera.Views.BestellijstenSummaryView;

/**
 * 
 * @author Roy
 *
 */
public class BestellijstenController extends Controller {
	private ProductenController productenController;
	
	public BestellijstenController() {
		this.view = new BestellijstenSummaryView(this);
		try {
			this.productenController = new ProductenController();
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
	
	public void opslaanBestellijst() {
		System.out.println("Opslaan");
	}
}
