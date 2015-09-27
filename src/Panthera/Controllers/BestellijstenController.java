package Panthera.Controllers;

import Panthera.Views.BestellijstCreateView;

/**
 * 
 * @author Roy
 *
 */
public class BestellijstenController extends Controller {
	public BestellijstenController() {
		this.view = new BestellijstCreateView(this);
	}
}
