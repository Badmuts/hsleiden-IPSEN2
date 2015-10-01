package Panthera.Views;

import Panthera.Controllers.MailController;
import Panthera.Panthera;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MailSelectRecipientsView extends BorderPane implements Viewable {
    private final MailController mailController;
    private Stage stage = Panthera.getInstance().getStage();

    public MailSelectRecipientsView(MailController mailController) {
        this.mailController = mailController;
        createTableView();
        setPadding(new Insets(10));
    }

    private void createTableView() {
//        this.table = new TableView<>()
    }

    /**
     * Viewable method shows this view.
     */
    @Override public void show() {
        this.stage.setScene(new Scene(this));
        this.stage.show();
    }
}
