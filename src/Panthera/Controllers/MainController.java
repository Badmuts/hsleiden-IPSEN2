package Panthera.Controllers;

import Panthera.Views.FacturenListView;
import Panthera.Views.MainView;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class MainController extends Controller {

    private StackPane subview;
    private MainMenuController mainMenuController;

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

    public void setSubview(Node newView) {
        Platform.runLater(() -> {
            subview.getChildren().clear();
            subview.getChildren().addAll(newView);
            subview.toFront();
        });
    }
}
