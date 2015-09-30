package Panthera.Views;

import Panthera.Controllers.FacturenController;
import Panthera.DAO.BestellijstDAO;
import Panthera.Models.Bestellijst;
import Panthera.Models.Factuur;
import Panthera.Models.Product;
import Panthera.Panthera;
import com.oracle.webservices.internal.api.message.PropertySet;
import javafx.beans.binding.Bindings;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.NumberStringConverter;

import java.util.ArrayList;

/**
 * Created by Brandon on 24-Sep-15.
 */
public class FacturenAddView extends GridPane implements Viewable {

    private ObservableList<Product> producten = FXCollections.observableArrayList();
    private FacturenController facturenController;
    private Stage stage = Panthera.getInstance().getStage();
    private int currentRow = 0;
    private Factuur factuur;
    private Bestellijst bestellijst = new Bestellijst();
    private TableView table = new TableView();

    public FacturenAddView(FacturenController facturenController) throws Exception {
        this.facturenController = facturenController;
        this.factuur = new Factuur();
//        this.producten = FXCollections.observableArrayList(new BestellijstDAO().all().get(0).getProducten());
        createTitle();
        createForm();
        createTableView();
        createSaveButton();
        this.table.setItems(producten);
    }

    public FacturenAddView(FacturenController facturenController, Factuur factuur) {
        this.facturenController = facturenController;
        this.factuur = factuur;
        createTitle();
        createForm();
        createSaveButton();
    }

    private void createSaveButton() {
        Button button = new Button("Opslaan");
        button.setOnAction(event -> saveFactuur());
        add(button, 0, currentRow);
        currentRow++;
    }

    private void saveFactuur() {
        facturenController.cmdSaveFactuur(factuur);
    }

    private void createTitle() {
        Text title = new Text("Factuur toevoegen");
        title.setFont(Font.font(20));
        add(title, 0, currentRow);
        currentRow++;
    }

    @Override
    public void show() {
        this.stage.setScene(new Scene(this));
        this.stage.show();
    }

    private void createForm() {
        createField("Factuurnummer", factuur.factuurnummerProperty(), new NumberStringConverter());
//        createComboBox("Debiteur");
        createField("Factuurdatum", factuur.factuurdatumProperty(), new DateStringConverter());
        createField("Vervaldatum", factuur.vervaldatumProperty(), new DateStringConverter());
        createField("Status", factuur.statusProperty());
        createComboBoxBestellijst("Bestellijst");
    }

//    private void createComboBox(String name) {
//        try {
//            Label label = new Label(name);
//            ArrayList<Debiteur> debiteuren = new DebiteurDAO.all();
//            ChoiceBox<Debiteur> choiceBox = new ChoiceBox<>(FXCollections.observableArrayList(debiteuren));
//            choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, NewValue) -> {
//                factuur.setDebiteur(newValue);
//            });
//
//            int i = 0;
//            for (Debitteur debiteur: debiteuren) {
//                if(debiteur.getId() == factuur.getDebiteur().getId())
//                    choiceBox.getSelectionModel().select(i);
//                i++;
//            }
//            add(label, 0, currentRow);
//            add(choiceBox, 1, currentRow);
//            currentRow++;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

        private void createComboBoxBestellijst(String name) {
        try {
            Label label = new Label(name);
            ArrayList<Bestellijst> bestellijsten = new BestellijstDAO().all();
            System.out.println("Bestelijlijsten aantal: " + bestellijsten.size());
            ChoiceBox<Bestellijst> choiceBox = new ChoiceBox<>(FXCollections.observableArrayList(bestellijsten));
            choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                bestellijst = newValue;
                //System.out.println(bestellijst.getProducten());
                producten.setAll(FXCollections.observableArrayList(newValue.getProducten()));
            });

            choiceBox.getSelectionModel().select(0);

            add(label, 0, currentRow);
            add(choiceBox, 1, currentRow);
            currentRow++;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createTableView() {
        table = new TableView();
//        TableColumn<Product, CheckBox> checkbox = new TableColumn(" ");
//        checkbox.setCellValueFactory(param -> {
//            CheckBox checkBox = new CheckBox();
//            Bindings.bindBidirectional(checkBox.selectedProperty(), param.getValue().activeProperty());
//            return new SimpleObjectProperty<>(checkBox);
//        });
        TableColumn<Product, Integer> productnummer = new TableColumn("Productnummer");
        productnummer.setCellValueFactory(new PropertyValueFactory<>("productnummer"));
        TableColumn<Product, String> naam = new TableColumn("Naam");
        naam.setCellValueFactory(new PropertyValueFactory<>("naam"));
        TableColumn<Product, Integer> jaar = new TableColumn("Jaar");
        jaar.setCellValueFactory(new PropertyValueFactory<>("jaar"));
        TableColumn<Product, Double> prijs = new TableColumn("Prijs");
        prijs.setCellValueFactory(new PropertyValueFactory<>("prijs"));
        TableColumn<Product, String> type = new TableColumn("Type");
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        TableColumn<Product, String> land = new TableColumn("Land");
        land.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getLand().getNaam()));
//        addClicklistener();
        table.getColumns().addAll(productnummer, naam, jaar, prijs, type, land);
        add(table, 0, currentRow);
        currentRow++;
    }

    private void createField(String name, Property property, StringConverter stringConverter) {
        Label label = new Label(name);
        TextField textField = new TextField(name);
        Bindings.bindBidirectional(textField.textProperty(), property, stringConverter);

        add(label, 0, currentRow);
        add(textField, 1, currentRow);
        currentRow++;
    }

    private void createField(String name, Property property) {
        Label label = new Label(name);
        TextField textField = new TextField(name);
        Bindings.bindBidirectional(textField.textProperty(), property);

        add(label, 0, currentRow);
        add(textField, 1, currentRow);
        currentRow++;
    }
}
