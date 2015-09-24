package Panthera.Views;
import Panthera.Controllers.FacturenController;
import Panthera.Models.Factuur;
import Panthera.Panthera;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;


import java.util.Date;

/**
 * Created by Brandon on 23-Sep-15.
 */
public class FacturenListView extends BorderPane implements Viewable {

    private FacturenController facturenController;
    private Stage stage = Panthera.getInstance().getStage();
    private TableView<Factuur> table;
    private ObservableList<Factuur> facturen;
    public final ObservableList<Long> checkedMessages = FXCollections
            .observableArrayList(new Long(1));
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
        setTop(topContainer);
    }

    private void createTableView() {
        this.table = new TableView<>();
        TableColumn id = new TableColumn("ID");
        id.setCellValueFactory(new PropertyValueFactory<Factuur, Integer>("id"));
        TableColumn factuurnummer = new TableColumn("Factuurnummer");
        factuurnummer.setCellValueFactory(new PropertyValueFactory<Factuur, Integer>("factuurnummer"));
        TableColumn factuurdatum = new TableColumn("Factuurdatum");
        factuurdatum.setCellValueFactory(new PropertyValueFactory<Factuur, Date>("factuurdatum"));
        TableColumn factuurexpdate = new TableColumn("Vervaldatum");
        factuurexpdate.setCellValueFactory(new PropertyValueFactory<Factuur, Date>("vervaldatum"));
        TableColumn status = new TableColumn("Status");
        status.setCellValueFactory(new PropertyValueFactory<Factuur, String>("status"));
        TableColumn checked = new TableColumn("checked");

        checked.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Factuur, CheckBox>, ObservableValue<CheckBox>>()
        {

            @Override
            public ObservableValue<CheckBox> call(TableColumn.CellDataFeatures<Factuur, CheckBox> param) {

                TableColumn.CellDataFeatures<Factuur, CheckBox> arg0) {
                    Factuur factuur = arg0.getValue();
                CheckBox checkBox = new CheckBox();
                for (Long value : model.checkedMessages) {
                    if(value.intValue() == Factuur.getId()) {
                        //zet de checkbox value op true
                        checkBox.selectedProperty().setValue(Boolean.TRUE);
                    }
                }
                return new SimpleObjectProperty<CheckBox>(checkBox);
            }
        }
        });

        this.table.getColumns().addAll(id, factuurnummer, factuurdatum, factuurexpdate, status, checked);
        setCenter(this.table);

    }

    private void createTitle() {
        Text title = new Text("Facturen");
        title.setFont(Font.font(22));
        topContainer.getChildren().add(title);
    }

    private void createAddFactuurButton() {
        Button button = new Button("Factuur toevoegen");
        button.setOnAction(e -> this.facturenController.setView(new FacturenAddView()).show());
        topContainer.getChildren().add(button);
    }


    @Override
    public void show() {

        this.stage.setScene(new Scene(this, 800, 600));
        this.stage.show();
    }
}
