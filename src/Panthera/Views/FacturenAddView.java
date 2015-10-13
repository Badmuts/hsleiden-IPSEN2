package Panthera.Views;

import Panthera.Controllers.FacturenController;
import Panthera.DAO.BestellijstDAO;
import Panthera.DAO.DebiteurDAO;
import Panthera.Models.*;
import Panthera.PDFModels.FactuurPdf;
import Panthera.Panthera;
import javafx.beans.binding.Bindings;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.time.LocalDate;
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
        setPadding(new Insets(22));
        setupView();
    }

    public FacturenAddView(FacturenController facturenController, Factuur factuur) {
        this.facturenController = facturenController;
        this.factuur = factuur;
        setupView();
    }

    private void setupView() {
        createTitle();
        createForm();
        createTableView();
        createSaveButton();
        table.setItems(producten);
        table.setEditable(true);
    }

    private void createSaveButton() {
        Button button = new Button("Opslaan");
        button.setOnAction(event -> {
            try {
                saveFactuur();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        add(button, 0, currentRow);
        currentRow++;
    }

    private void saveFactuur() throws Exception {
        new FactuurPdf(factuur, factuur.getFactuurregels(), factuur.getDebiteur());
        facturenController.cmdSaveFactuur(factuur);
    }

    private void createTitle() {
        Text title = new Text("Factuur toevoegen");
        title.getStyleClass().addAll("h1");
        add(title, 0, currentRow);
        currentRow++;
    }

    @Override
    public void show() {
        this.stage.setScene(new Scene(this, 1024, 768));
        this.stage.show();
    }
    private void createDateField(String name, Property property) {
        Label label = new Label(name);
        DatePicker datePicker = new DatePicker(LocalDate.now());
        factuur.setFactuurdatum(java.sql.Date.valueOf(datePicker.getValue()));

        datePicker.setOnAction(event -> {
            property.setValue(java.sql.Date.valueOf(datePicker.getValue()));
        });

        add(label, 0, currentRow);
        add(datePicker, 1, currentRow);
        currentRow++;

    }
    private void createDateFieldVervalDatum(String name, Property property) {
        Label label = new Label(name);
        DatePicker datePicker = new DatePicker(LocalDate.now().plusDays(30));
        factuur.setVervaldatum(java.sql.Date.valueOf(datePicker.getValue()));

        datePicker.setOnAction(event -> {
            property.setValue(java.sql.Date.valueOf(datePicker.getValue()));
        });

        add(label, 0, currentRow);
        add(datePicker, 1, currentRow);
        currentRow++;

    }
    private void createForm() {
        createField("Factuurnummer", factuur.factuurnummerProperty(), new IntegerStringConverter());
        createComboBox("Lid");
        createDateField("Factuurdatum", factuur.factuurdatumProperty());
        createDateFieldVervalDatum("Vervaldatum", factuur.vervaldatumProperty());
        createTextArea("Opmerking", factuur.opmerkingProperty());
        createComboBoxBestellijst("Bestellijst");
    }

    private void createComboBox(String name) {
        try {
            Label label = new Label(name);
            ArrayList<Debiteur> debiteuren = new DebiteurDAO().getAllDebiteuren();
            ChoiceBox<Debiteur> choiceBox = new ChoiceBox<>(FXCollections.observableArrayList(debiteuren));
            choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                factuur.setDebiteur(newValue);
            });

            int i = 0;
            for (Debiteur debiteur: debiteuren) {
//                if(debiteur.getId() == factuur.getDebiteur().getId())
                choiceBox.getSelectionModel().select(debiteur);
            }
            add(label, 0, currentRow);
            add(choiceBox, 1, currentRow);
            currentRow++;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        private void createComboBoxBestellijst(String name) {
        try {
            Label label = new Label(name);
            ArrayList<Bestellijst> bestellijsten = new BestellijstDAO().allWithProducten();
            System.out.println("Bestelijlijsten aantal: " + bestellijsten.size());
            ChoiceBox<Bestellijst> choiceBox = new ChoiceBox<>(FXCollections.observableArrayList(bestellijsten));
            choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                bestellijst = newValue;
                //System.out.println(bestellijst.getProducten());
                producten.setAll(FXCollections.observableArrayList(newValue.getProducten()));
                table.setItems(producten);
            });

//            choiceBox.getSelectionModel().select(0);

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

        /*
        * TODO: aantal producten * prijs laten zien
        */
        TableColumn<Factuurregel, Double> totaal = new TableColumn("Totaal");
        totaal.setCellValueFactory(new PropertyValueFactory<Factuurregel, Double>("subtotaal"));


        TableColumn<Product, String> type = new TableColumn("Type");
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        TableColumn<Product, String> land = new TableColumn("Land");
        land.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getLand().getNaam()));
//        addClicklistener();
        TableColumn<Product, String> aantal = new TableColumn("Aantal");
        aantal.setCellFactory(TextFieldTableCell.forTableColumn());
        aantal.setCellValueFactory(param -> new SimpleStringProperty("0"));

        aantal.setOnEditCommit(event -> {
            int aantalProducten = Integer.parseInt(event.getNewValue());
            Product product = event.getRowValue();
            Factuurregel factuurregel = new Factuurregel(aantalProducten, product);
            factuur.addFactuurregel(factuurregel);
            aantal.setCellValueFactory(param -> new SimpleStringProperty(String.valueOf(aantalProducten)));
        });

        TextField aantalProducten = new TextField();
        table.getColumns().addAll(aantal, productnummer, naam, jaar, prijs, type, land);

        add(table, 1, currentRow);
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

    public void createTextArea(String name, Property property) {
        Label label = new Label(name);
        TextArea textArea = new TextArea(name);
        textArea.setMinWidth(50);
        textArea.setPrefWidth(50);
        textArea.setMaxWidth(400);
        Bindings.bindBidirectional(textArea.textProperty(), property);

        add(label, 0, currentRow);
        add(textArea, 1, currentRow);
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

