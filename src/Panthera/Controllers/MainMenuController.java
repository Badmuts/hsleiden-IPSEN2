package Panthera.Controllers;

import Panthera.Views.MainMenuView;

/**
 * Created by Daan on 22-Sep-15.
 */
public class MainMenuController extends Controller {

    public MainMenuController() {
        this.view = new MainMenuView(this);
    }

    /**
     * Returns a new ProductenController
     *
     * @author Daan Rosbergen
     * @return ProductenController
     * @throws Exception
     */
    public Controller cmdCreateProductenController() throws Exception {
        return new ProductenController();
    }

    public Controller cmdCreateFacturenController() throws Exception {
        return new FacturenController();
    }
    
    public Controller cmdCreateBestellijstenController() throws Exception {
    	return new BestellijstenController();
    }

    public Controller cmdCreateDebiteurenController() {
        return new DebiteurenController();
    }
}

