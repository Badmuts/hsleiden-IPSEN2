package Panthera.Views;

import Panthera.Panthera;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;

/**
 * Created by Daan on 22-Sep-15.
 */
public class MainMenuView extends BorderPane {

    private Stage stage = Panthera.getInstance().getStage();
    private VBox menu;

    public MainMenuView() {
        super();
        this.createMenu();
        setCenter(menu);
        this.stage.setScene(new Scene(this));
        this.stage.show();
    }

    /**
     *
     */
    private void createMenu() {
        this.menu = new VBox(10);
        // Temp solution for button list.
        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add(new Button("Facturen"));
        buttons.add(new Button("Bestellijsten"));
        buttons.add(new Button("Debiteuren"));
        buttons.add(new Button("E-mail"));
        for (Button button: buttons)
            this.menu.getChildren().add(button);
    }

}
