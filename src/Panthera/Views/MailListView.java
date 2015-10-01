package Panthera.Views;

import Panthera.Controllers.MailController;
import Panthera.Panthera;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Created by Daan on 30-Sep-15.
 */
public class MailListView extends BorderPane implements Viewable {
    private final Stage stage = Panthera.getInstance().getStage();
    private final MailController mailController;

    public MailListView(MailController mailController) {
        this.mailController = mailController;
        Button button = new Button("Verstuur dankwoord");
        button.setOnAction(event -> this.mailController.cmdShowDankwoordView());
        setCenter(button);
        setPadding(new Insets(10));
    }

    @Override
    public void show() {
        this.stage.setScene(new Scene(this));
        this.stage.show();
    }
}
