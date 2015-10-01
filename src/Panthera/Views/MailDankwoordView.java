package Panthera.Views;

import Panthera.Controllers.Controller;
import Panthera.Controllers.MailController;
import Panthera.Panthera;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MailDankwoordView extends GridPane implements Viewable {
    private final MailController mailController;
    private Stage stage = Panthera.getInstance().getStage();
    private int currentRow = 0;

    public MailDankwoordView(MailController mailController) {
        this.mailController = mailController;
        createForm();
    }

    private void createForm() {
        createField("Bericht");
        createButton("Ontvangers selecteren", mailController.cmdSelectRecipients());
    }

    private void createField(String name) {
        Label label = new Label(name);
        TextArea textArea = new TextArea();
        add(label, 0, currentRow);
        add(textArea, 1, currentRow);
        currentRow++;
    }

    private void createButton(String name, Controller controller) {
        Button button = new Button(name);
        button.setOnAction(event -> controller.show());
        add(button, 0, currentRow);
        currentRow++;
    }

    @Override public void show() {
        this.stage.setScene(new Scene(this));
        this.stage.show();
    }
}
