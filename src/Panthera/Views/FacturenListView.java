package Panthera.Views;
import Panthera.Controllers.FacturenController;

import Panthera.Factories.CheckBoxCellFactory;
import Panthera.Models.Debiteur;

import Panthera.Models.Factuur;
import Panthera.Panthera;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Date;



/**
 * Created by Brandon on 23-Sep-15.
 */
public class FacturenListView extends BorderPane implements Viewable {

    private FacturenController facturenController;
    private Stage stage = Panthera.getInstance().getStage();
    private TableView<Factuur> table;
    private ObservableList<Factuur> facturen = FXCollections.observableArrayList();
    private FilteredList<Factuur> filteredData;
    private TextField filterField;
    private HBox topContainer = new HBox(10);



    public FacturenListView(FacturenController facturenController)  {

        this.facturenController = facturenController;
        this.facturen = this.facturenController.cmdGetFacturen();
        createHeader();
        createTableView();
        table.setItems(facturen);
        filterFacturen();

    }

    private void filterFacturen() {
        this.filteredData = new FilteredList<Factuur>(this.facturen, p -> true);

        this.filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            this.filteredData.setPredicate(factuur -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (factuur.getStatus().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (factuur.getDebiteur().getVoornaam().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (factuur.getDebiteur().getNaam().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (Integer.toString(factuur.getFactuurnummer()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<Factuur> sortedData = new SortedList<>(this.filteredData);
        sortedData.comparatorProperty().bind(this.table.comparatorProperty());

        this.table.setItems(sortedData);
    }

    private void createHeader() {
        createTitle();
        createAddFactuurButton();
        createRemoveFactuurButton();
        createTextField();
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
        TableColumn<Factuur, String> voornaam = new TableColumn("Voornaam");
        voornaam.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getDebiteur().getVoornaam()));
        TableColumn<Factuur, String> tussenvoegsel = new TableColumn("Tussenvoegsel");
        tussenvoegsel.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getDebiteur().getTussenvoegsel()));
        TableColumn<Factuur, String> achternaam = new TableColumn("Achternaam");
        achternaam.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getDebiteur().getNaam()));
        TableColumn factuurdatum = new TableColumn("Factuurdatum");
        factuurdatum.setCellValueFactory(new PropertyValueFactory<Factuur, Date>("factuurdatum"));
        TableColumn factuurexpdate = new TableColumn("Vervaldatum");
        factuurexpdate.setCellValueFactory(new PropertyValueFactory<Factuur, Date>("vervaldatum"));
        TableColumn<Factuur, Double> bedrag = new TableColumn("Bedrag");
        bedrag.setCellValueFactory(new PropertyValueFactory<Factuur, Double>(""));
        TableColumn status = new TableColumn("Status");
        status.setCellValueFactory(new PropertyValueFactory<Factuur, String>("status"));



        this.table.getColumns().addAll(checkbox, factuurnummer, voornaam, tussenvoegsel, achternaam, factuurdatum, factuurexpdate, status);



        createSelectAllButton();
        setCenter(this.table);


    }

    public void createSelectAllButton() {

        CheckBox cb = new CheckBox("Select all");
        cb.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> ov,
                                Boolean old_val, Boolean new_val) {
                if (new_val) {
                    for (Factuur factuur : facturen) {
                        factuur.checkedProperty().set(new_val);
                    }
                }
                else {
                    for (Factuur factuur : facturen) {
                        factuur.checkedProperty().set(false);
                    }
                }
            }
        });

        topContainer.getChildren().add(cb);
    }

    private void createTextField() {
        this.filterField = new TextField();
        topContainer.getChildren().add(this.filterField);

    }
    private void createTitle() {
        Text title = new Text("Facturen");
        title.setFont(Font.font(22));
        topContainer.getChildren().add(title);
    }

    private void createAddFactuurButton() {
        Button button = new Button("Factuur toevoegen");
        button.setOnAction(event -> {
            try {
                this.facturenController.cmdShowFactuurAddView();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        topContainer.getChildren().add(button);
    }


    @Override
    public void show() {

        this.stage.setScene(new Scene(this, 1024, 768));
        this.stage.show();
    }
}
