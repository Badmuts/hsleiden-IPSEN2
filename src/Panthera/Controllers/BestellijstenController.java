package Panthera.Controllers;

import Panthera.Views.BestellijstenSummaryView;

/**
 * 
 * @author Roy
 *
 */
public class BestellijstenController extends Controller {
	public BestellijstenController() {
		this.view = new BestellijstenSummaryView(this);
	}
	
	
}
