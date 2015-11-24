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

/**
 * The main view of the app containing of a menu and a subview.
 *
 * @author Daan Rosbergen
 */
public class MainView extends BorderPane implements Viewable {

    private Stage stage = Panthera.getInstance().getStage();
    private MainMenuController mainMenuController;
    private StackPane headerContainer;
    private StackPane subviewContainer;

    /**
     * Creates a new main view.
     *
     * @author Daan Rosbergen
     * @param mainMenuController    MainMenuController  Controller for menu items.
     */
    public MainView(MainMenuController mainMenuController) {
        this.mainMenuController = mainMenuController;
        headerContainer = new StackPane();
        subviewContainer = new StackPane();
        setupView();
    }

    /**
     * Setup view (background, logo, subview container and menu).
     *
     * @author Daan Rosbergen
     */
    private void setupView() {
        createBackground();
        createSubviewContainer();
        createHeader();
        setTop(headerContainer);
    }

    /**
     * Creates header of the view:
     *  - Menu
     *  - Logo
     *
     *  @author Daan Rosbergen
     */
    private void createHeader() {
        addMenu();
        addLogo();
    }

    /**
     * Create subview container stackpane where other views will be shown.
     *
     * @author Daan Rosbergen
     */
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

    /**
     * Load logo and add it to the header.
     *
     * @author Daan Rosbergen
     */
    private void addLogo() {
        ImageView logo = new ImageView("Panthera/Resources/lions-app.png");
        logo.setFitWidth(90);
        logo.setPreserveRatio(true);
        headerContainer.getChildren().add(logo);
        StackPane.setAlignment(logo, Pos.TOP_LEFT);
        StackPane.setMargin(logo, new Insets(16, 10, 22, 84));
    }

    /**
     * Create new menu and add it to the view.
     *
     * @author Daan Rosbergen
     */
    private void addMenu() {
        MainMenuView mainMenuView = new MainMenuView(this.mainMenuController);
        headerContainer.getChildren().addAll(mainMenuView);
        StackPane.setMargin(mainMenuView, new Insets(25, 84, 0, 0));
    }

    /**
     * Create blue background rectangle.
     *
     * @author Daan Rosbergen
     */
    private void createBackground() {
        Rectangle rectangle = new Rectangle(stage.widthProperty().get(), 200);
        stage.widthProperty().addListener((observable, oldValue, newValue) -> {
            rectangle.setWidth(newValue.doubleValue());
        });
        rectangle.getStyleClass().add("header-background");
        headerContainer.getChildren().addAll(rectangle);
        StackPane.setAlignment(rectangle, Pos.TOP_CENTER);
    }

    /**
     * Retrieve the subviewcontainer to manipulate it.
     *
     * @author Daan Rosbergen
     * @return
     */
    public StackPane getSubviewContainer() {
        return subviewContainer;
    }

    /**
     * @author Daan Rosbergen
     */
    @Override public void show() {
        Scene scene = new Scene(this);
        this.stage.setScene(scene);
        this.stage.getScene().getStylesheets().add("Panthera/Resources/style.css");
        this.stage.show();
    }

}
