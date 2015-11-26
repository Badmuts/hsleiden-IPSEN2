package Panthera.Controllers;

import Panthera.Models.Factuur;

/**
 * Controller used for delagating menu actions.
 *
 * @author Daan Rosbergen
 * Created by Daan on 22-Sep-15.
 */
public class MainMenuController extends Controller {

    private MainController mainController;

    /**
     * Creates a new MainMenuController and saves a instance of MainController.
     *
     * @param mainController    MainController  MainController.
     */
    public MainMenuController(MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * Returns a new ProductenController.
     *
     * @author Daan Rosbergen
     * @return ProductenController
     * @throws Exception
     */
    public Controller cmdCreateProductenController() throws Exception {
        return new ProductenController(this.mainController);
    }

    /**
     * Returns a new FacturenController.
     *
     * @return FacturenController
     * @throws Exception
     */
    public Controller cmdCreateFacturenController() throws Exception {
        return new FacturenController(this.mainController, new Factuur());
    }

    /**
     * Returns a new BesetelijstenController.
     *
     * @return  BesetelijstenController
     * @throws  Exception
     */
    public Controller cmdCreateBestellijstenController() throws Exception {
    	return new BestellijstenController(this.mainController);
    }

    /**
     * Returns a new DebiteurenController.
     * @author Victor
     * @return  DebiteurenController
     */
    public Controller cmdCreateDebiteurenController() {
        return new DebiteurenController(this.mainController);
    }

    /**
     * Returns a new MailController.
     *
     * @author Daan Rosbergen
     * @return  MailController
     */
    public Controller cmdCreateMailController() {
        return new MailController(this.mainController);
    }

    /**
     * Returns a new SettingsController.
     * @author Victor
     * @return
     */
    public Controller cmdCreateSettingsController() {
    	return new SettingsController(this.mainController);
    }
}

