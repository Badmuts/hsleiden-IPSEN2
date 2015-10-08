package Panthera.Controllers;

import Panthera.Views.MainView;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class MainController extends Controller {

    private StackPane subview;

    public MainController() {
        this.view = new MainView(new MainMenuController(this));
        MainView mainView = (MainView)this.view;
        this.subview = mainView.getSubviewContainer();
    }

    public void setSubview(Node newView) {
        Platform.runLater(() -> {
            subview.getChildren().clear();
            subview.getChildren().addAll(newView);
            subview.toFront();
        });
    }
}
