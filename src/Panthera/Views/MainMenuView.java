package Panthera.Views;

import Panthera.Controllers.Controller;
import Panthera.Controllers.MainMenuController;
import Panthera.Controllers.ProductenController;
import Panthera.Panthera;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by Daan on 22-Sep-15.
 */
public class MainMenuView extends BorderPane implements Viewable {

    private MainMenuController mainMenuController;
    private Stage stage = Panthera.getInstance().getStage();
    private VBox menu;
    private ArrayList<Button> buttons = new ArrayList<>();

    /**
     * Create main menu view, the first view the user will see.
     */
    public MainMenuView(MainMenuController mainMenuController) {
        this.mainMenuController = mainMenuController;
        this.createMenu();
        setCenter(menu);
    }

    /**
     * Creates menu container.
     */
    private void createMenu() {
        this.menu = new VBox(10);
        createMenuButtons(); // Temp solution for button list.
        this.menu.getChildren().addAll(buttons);
    }

    /**
     * Creates menu buttons
     */
    private void createMenuButtons() {
        try {
            buttons.add(new Button("Facturen"));
            buttons.add(createButton("Producten", new ProductenController()));
            buttons.add(new Button("Bestellijsten"));
            buttons.add(new Button("Debiteuren"));
            buttons.add(new Button("E-mail"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Button createButton(String name, Controller controller) {
        Button button = new Button(name);
        button.setOnAction(event -> controller.show());
        return button;
    }

    @Override public void show() {
        this.stage.setScene(new Scene(this));
        this.stage.show();
    }
}
