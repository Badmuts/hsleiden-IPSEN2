package Panthera.Views;
import Panthera.Controllers.FacturenController;
import Panthera.Factories.CheckBoxCellFactory;
import Panthera.Models.Factuur;
import Panthera.Panthera;

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
        setTop(topContainer);
    }

    private void createTableView() {
        this.table = new TableView<>();
//        TableColumn id = new TableColumn("ID");
//        id.setCellValueFactory(new PropertyValueFactory<Factuur, Integer>("id"));
        TableColumn factuurnummer = new TableColumn("Factuurnummer");
        factuurnummer.setCellValueFactory(new PropertyValueFactory<Factuur, Integer>("factuurnummer"));
        TableColumn factuurdatum = new TableColumn("Factuurdatum");
        factuurdatum.setCellValueFactory(new PropertyValueFactory<Factuur, Date>("factuurdatum"));
        TableColumn factuurexpdate = new TableColumn("Vervaldatum");
        factuurexpdate.setCellValueFactory(new PropertyValueFactory<Factuur, Date>("vervaldatum"));
        TableColumn status = new TableColumn("Status");
        status.setCellValueFactory(new PropertyValueFactory<Factuur, String>("status"));
        TableColumn checked = new TableColumn("checked");
        checked.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Factuur, CheckBox>, ObservableValue<CheckBox>>() {
            @Override
            public ObservableValue<CheckBox> call(TableColumn.CellDataFeatures<Factuur, CheckBox> param) {
                CheckBox checkBox = new CheckBox();
                Factuur factuur = param.getValue();

                for (Long value : facturen.checkedMessages) {
                    if(value.intValue()== factuur.getId() ){
                        System.out.print("Test");
                    }
                        checkBox.selectedProperty().setValue(Boolean.TRUE);
                    }

                return new SimpleObjectProperty<CheckBox>(checkBox);
            }
        });
                //checked.setCellValueFactory(new PropertyValueFactory<Factuur, Boolean>("checked"));
        //checked.setCellValueFactory(CheckBoxTableCell.forTableColumn(factuurnummer));

        this.table.getColumns().addAll(factuurnummer, factuurdatum, factuurexpdate, status, checked);
        setCenter(this.table);

        table.setOnMouseClicked(new EventHandler<MouseEvent>()

            {

                @Override
            public void handle(MouseEvent event) {
                Factuur factuur =   table.getSelectionModel().getSelectedItem();
                System.out.println(factuur.toString());
            }


        });

    }

    // Code moet nog werkend gemaakt worden !!

    /* final Button infoButton = new Button("Show details");
    topContainer.getChildren().add(infoButton);
    infoButton.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            for (Factuur f : table.getItems()) {
                System.out.printf("%s %s (%svegetarian)%n", f.getId(),
                        f.getFactuurnummer(), f.isChecked() ? "" : "not ");
                System.out.println(table.getSelectionModel().getSelectedItems());

            }
            System.out.println();
        }
    });

    public createGetFactuurDetailsButton() {
        final Button infoButton = new Button("Show details");
        infoButton.setOnAction(e -> ( for(Factuur f : table.getItems() ) ));
    }
   */

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
