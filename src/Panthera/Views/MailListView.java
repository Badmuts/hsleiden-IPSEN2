package Panthera.Views;

import Panthera.Controllers.MailController;
import Panthera.Panthera;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by Daan on 30-Sep-15.
 */
public class MailListView extends BorderPane implements Viewable {
    private final Stage stage = Panthera.getInstance().getStage();
    private final MailController mailController;
    private VBox topContainer = new VBox(10);

    public MailListView(MailController mailController) {
        this.mailController = mailController;

        setPadding(new Insets(10));
        createHeader();
    }

    private void createHeader() {
        createTitle();
        createDankwoordButton();
        createUitnodigingButton();
        setTop(topContainer);
    }

    private void createTitle() {
        Text title = new Text("Email");
        title.setFont(Font.font(22));
        topContainer.getChildren().add(title);
    }
    private void createUitnodigingButton() {
        Button button = new Button("Verstuur uitnodiging");
        button.setOnAction(event -> this.mailController.cmdShowUitnodigingView());
        topContainer.getChildren().add(button);
    }

    private void createDankwoordButton() {
        Button button = new Button("Verstuur dankwoord");
        button.setOnAction(event -> this.mailController.cmdShowDankwoordView());
        topContainer.getChildren().add(button);
    }

    @Override
    public void show() {
        this.stage.setScene(new Scene(this));
        this.stage.show();
    }
}
