package Panthera.Controllers;

import Panthera.Views.FacturenListView;
import Panthera.Views.MainView;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

/**
 * Controller used to show subviews. Every controller has a instance of this.
 *
 * @author Daan Rosbergen
 */
public class MainController extends Controller {

    private StackPane subview;
    private MainMenuController mainMenuController;

    /**
     * Creates a new MainView and loads the FacturenListView.
     *
     * @author Daan Rosbergen
     */
    public MainController() {
    	  this.mainMenuController = new MainMenuController(this);
        this.view = new MainView(mainMenuController);
        MainView mainView = (MainView)this.view;
        this.subview = mainView.getSubviewContainer();
        try {
			      setSubview(new FacturenListView(new FacturenController(this)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Set the current subview.
     *
     * @author Daan Rosbergen
     * @param newView   Node    New subview to be displayed.
     */
    public void setSubview(Node newView) {
        Platform.runLater(() -> {
            subview.getChildren().clear();
            subview.getChildren().addAll(newView);
            subview.toFront();
        });
    }
    
}
