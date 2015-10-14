package Panthera.Controllers;

import Panthera.Views.MainView;

/**
 * Created by Daan on 22-Sep-15.
 */
public class MainMenuController extends Controller {

    private MainController mainController;

    public MainMenuController(MainController mainController) {
        this.mainController = mainController;
//        mainController.setSubview(new MainView(this));
    }

    /**
     * Returns a new ProductenController
     *
     * @author Daan Rosbergen
     * @return ProductenController
     * @throws Exception
     */
    public Controller cmdCreateProductenController() throws Exception {
        return new ProductenController(this.mainController);
    }

    public Controller cmdCreateFacturenController() throws Exception {
        return new FacturenController(this.mainController);
    }
    
    public Controller cmdCreateBestellijstenController() throws Exception {
    	return new BestellijstenController(this.mainController);
    }

    public Controller cmdCreateDebiteurenController() {
        return new DebiteurenController(this.mainController);
    }

    public Controller cmdCreateMailController() {
        return new MailController(this.mainController);
    }
    public Controller cmdCreateSettingsController() {
    	return new SettingsController(this.mainController);
    }
}

