package Panthera.Controllers;

import Panthera.Views.MainView;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class MainController extends Controller {

    private StackPane subview;

    public MainController() {
        this.view = new MainView(new MainMenuController(this));
        MainView mainView = (MainView)this.view;
        this.subview = mainView.getSubviewContainer();
    }

    public void setSubview(Node subview) {
        this.subview.getChildren().clear();
        this.subview.getChildren().addAll(subview);
    }
}
