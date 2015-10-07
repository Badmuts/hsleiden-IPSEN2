package Panthera.Views;

import Panthera.Controllers.MainMenuController;
import Panthera.Panthera;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MainView extends BorderPane implements Viewable {

    private Stage stage = Panthera.getInstance().getStage();
    private MainMenuController mainMenuController;
    private StackPane headerContainer = new StackPane();
    private StackPane subviewContainer = new StackPane();

    public MainView(MainMenuController mainMenuController) {
        this.mainMenuController = mainMenuController;
        setupView();
    }

    private void setupView() {
        createBackground();
        createSubviewContainer();
        createHeader();
        setTop(headerContainer);
    }

    private void createHeader() {
        addMenu();
        addLogo();
    }

    private void createSubviewContainer() {
        subviewContainer = new StackPane();
        subviewContainer.getStyleClass().add("subview-container");
        headerContainer.getChildren().add(subviewContainer);
        StackPane.setAlignment(subviewContainer, Pos.BOTTOM_CENTER);
        StackPane.setMargin(subviewContainer, new Insets(128, 84, 20, 84));
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addLogo() {
        ImageView logo = new ImageView("Panthera/Resources/lions-app.png");
        logo.setFitWidth(90);
        logo.setPreserveRatio(true);
        headerContainer.getChildren().add(logo);
        StackPane.setAlignment(logo, Pos.TOP_LEFT);
        StackPane.setMargin(logo, new Insets(16, 10, 22, 84));
    }

    private void addMenu() {
        MainMenuView mainMenuView = new MainMenuView(this.mainMenuController);
        headerContainer.getChildren().addAll(mainMenuView);
        StackPane.setMargin(mainMenuView, new Insets(25, 84, 0, 0));
    }

    private void createBackground() {
        Rectangle rectangle = new Rectangle(stage.widthProperty().get(), 200);
        stage.widthProperty().addListener((observable, oldValue, newValue) -> {
            rectangle.setWidth(newValue.doubleValue());
        });
        rectangle.getStyleClass().add("header-background");
        headerContainer.getChildren().addAll(rectangle);
        StackPane.setAlignment(rectangle, Pos.TOP_CENTER);
    }

    public StackPane getSubviewContainer() {
        return subviewContainer;
    }

    @Override public void show() {
        Scene scene = new Scene(this);
        this.stage.setScene(scene);
        this.stage.getScene().getStylesheets().add("Panthera/Resources/style.css");
        this.stage.show();
    }

}
