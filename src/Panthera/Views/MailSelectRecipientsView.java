package Panthera.Views;

import Panthera.Controllers.MailController;
import Panthera.Models.Factuur;
import Panthera.Panthera;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MailSelectRecipientsView extends BorderPane implements Viewable {
    private final MailController mailController;
    private Stage stage = Panthera.getInstance().getStage();
    private TableView<Debiteur> table;

    public MailSelectRecipientsView(MailController mailController) {
        this.mailController = mailController;
        createTableView();
    }

    private void createTableView() {
        this.table = new TableView();
        TableColumn<Debiteur, CheckBox> checkbox = new TableColumn(" ");
        checkbox.setCellValueFactory(param -> {
            CheckBox checkBox = new CheckBox();
            Bindings.bindBidirectional(checkBox.selectedProperty(),
                param.getValue().checkedProperty());
            return new SimpleObjectProperty<>(checkBox);
        });
        TableColumn naam = new TableColumn("Naam");
        naam.setCellValueFactory(new PropertyValueFactory<Factuur, Integer>("naam"));
    }

    @Override public void show() {
        this.stage.setScene(new Scene(this));
        this.stage.show();
    }
}
