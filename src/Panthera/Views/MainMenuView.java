package Panthera.Views;

import Panthera.Controllers.Controller;
import Panthera.Controllers.MainMenuController;
import Panthera.Panthera;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * View containing menu buttons.
 *
 * @author Daan Rosbergen
 * Created by Daan on 22-Sep-15.
 */
public class MainMenuView extends BorderPane implements Viewable {

    private MainMenuController mainMenuController;
    private Stage stage = Panthera.getInstance().getStage();
    private HBox menu;
    private ArrayList<Button> buttons = new ArrayList<>();

    /**
     * Create main menu view, the first view the user will see.
     *
     * @author Daan Rosbergen
     */
    public MainMenuView(MainMenuController mainMenuController) {
        this.mainMenuController = mainMenuController;
        this.createMenu();
        setTop(menu);
        setPadding(new Insets(10));
    }

    /**
     * Creates menu container.
     *
     * @author Daan Rosbergen
     */
    private void createMenu() {
        this.menu = new HBox(10);
        this.menu.setAlignment(Pos.CENTER_RIGHT);
        createMenuButtons(); // Temp solution for button list.
        this.menu.getChildren().addAll(buttons);
    }

    /**
     * Creates menu buttons
     *
     * @author Daan Rosbergen
     */
    private void createMenuButtons() {
        try {
            buttons.add(createButton("Facturen", mainMenuController.cmdCreateFacturenController()));
            buttons.add(createButton("Wijnen", mainMenuController.cmdCreateProductenController()));
            buttons.add(createButton("Bestellijsten", mainMenuController.cmdCreateBestellijstenController()));
            buttons.add(createButton("E-mail", mainMenuController.cmdCreateMailController()));
            buttons.add(createButton("Leden", mainMenuController.cmdCreateDebiteurenController()));
            buttons.add(createButton("Instellingen", mainMenuController.cmdCreateSettingsController()));
//            buttons.get(0).getStyleClass().add("active"); // temp
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Helper function to create a button and map it to a controller.
     *
     * @author Daan Rosbergen
     * @param name          Name of button.
     * @param controller    Target controller.
     * @return Button       The button.
     */
    private Button createButton(String name, Controller controller) {
        Button button = new Button(name);
        button.setOnAction(event -> {
            controller.show();
            for (Button btn: buttons) {
                btn.getStyleClass().remove("active");
            }
            button.getStyleClass().add("active");
        });
        button.getStyleClass().add("btn");
        button.getStyleClass().add("btn-menu");
        return button;
    }

    /**
     * @author Daan Rosbergen
     */
    @Override public void show() {
        this.stage.setScene(new Scene(this));
        this.stage.show();
    }
}
