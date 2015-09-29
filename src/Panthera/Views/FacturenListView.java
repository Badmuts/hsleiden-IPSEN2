package Panthera.Views;
import Panthera.Controllers.FacturenController;
import Panthera.Factories.CheckBoxCellFactory;
import Panthera.Models.Factuur;
import Panthera.Panthera;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;


import javax.swing.table.TableModel;
import java.util.Date;

/**
 * Created by Brandon on 23-Sep-15.
 */
public class FacturenListView extends BorderPane implements Viewable {

    private FacturenController facturenController;
    private Stage stage = Panthera.getInstance().getStage();
    private TableView<Factuur> table;
    public final ObservableList<Long> checkedMessages = FXCollections
            .observableArrayList(new Long(1));

    private ObservableList<Factuur> facturen;
    private HBox topContainer = new HBox(10);



    public FacturenListView(FacturenController facturenController)  {

        this.facturenController = facturenController;
        this.facturen = this.facturenController.cmdGetFacturen();
        createHeader();
        createTableView();
        table.setItems(facturen);

    }

    private void createHeader() {
        createTitle();
        createAddFactuurButton();
        createRemoveFactuurButton();
        setTop(topContainer);
    }

    private void createRemoveFactuurButton() {
        Button button = new Button("Factuur verwijderen");
        button.setOnAction(event -> facturenController.cmcDeleteFactuur(facturen));
        topContainer.getChildren().add(button);
    }

    private void createTableView() {
        this.table = new TableView<>();
        TableColumn<Factuur, CheckBox> checkbox = new TableColumn(" ");
        checkbox.setCellValueFactory(param -> {
                       CheckBox checkBox = new CheckBox();
                        Bindings.bindBidirectional(checkBox.selectedProperty(), param.getValue().checkedProperty());
                        return new SimpleObjectProperty<>(checkBox);
        });
        TableColumn factuurnummer = new TableColumn("Factuurnummer");
        factuurnummer.setCellValueFactory(new PropertyValueFactory<Factuur, Integer>("factuurnummer"));
        TableColumn factuurdatum = new TableColumn("Factuurdatum");
        factuurdatum.setCellValueFactory(new PropertyValueFactory<Factuur, Date>("factuurdatum"));
        TableColumn factuurexpdate = new TableColumn("Vervaldatum");
        factuurexpdate.setCellValueFactory(new PropertyValueFactory<Factuur, Date>("vervaldatum"));
        TableColumn status = new TableColumn("Status");
        status.setCellValueFactory(new PropertyValueFactory<Factuur, String>("status"));

        this.table.getColumns().addAll(checkbox, factuurnummer, factuurdatum, factuurexpdate, status);
        setCenter(this.table);


    }


    private void createTitle() {
        Text title = new Text("Facturen");
        title.setFont(Font.font(22));
        topContainer.getChildren().add(title);
    }

    private void createAddFactuurButton() {
        Button button = new Button("Factuur toevoegen");
        button.setOnAction(e -> this.facturenController.setView(new FacturenAddView(this.facturenController)).show());
        topContainer.getChildren().add(button);
    }


    @Override
    public void show() {

        this.stage.setScene(new Scene(this, 800, 600));
        this.stage.show();
    }
}
