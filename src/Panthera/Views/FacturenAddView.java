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
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by Brandon on 24-Sep-15.
 */
public class FacturenAddView extends GridPane implements Viewable {

    /**
     * attributen
     */
    private ObservableList<Product> producten = FXCollections.observableArrayList();
    private FacturenController facturenController;
    private Stage stage = Panthera.getInstance().getStage();
    private int currentRow = 0;
    private int columnIndex = 0;
    private ArrayList<ArrayList<Node>> nodes = new ArrayList<>();
    private Factuur factuur;
    private Bestellijst bestellijst = new Bestellijst();
    private TableView table = new TableView();

    /**
     *
     * @param facturenController
     * @throws Exception
     */
    public FacturenAddView(FacturenController facturenController) throws Exception {
        this.facturenController = facturenController;
        this.factuur = new Factuur();
        setPadding(new Insets(22));
        setupView();
    }

    /**
     *
     * @param facturenController
     * @param factuur
     */
    public FacturenAddView(FacturenController facturenController, Factuur factuur) {
        this.facturenController = facturenController;
        this.factuur = factuur;
        setupView();
        setPadding(new Insets(22));
        setHgap(10);
        setVgap(10);
        getStyleClass().addAll("factuur-gridpane");
    }


    /**
     * deze methode maakt de header aan op de pagina
     */
    private void setupView() {
        createTitle();
        createSaveButton();
        createForm();
        createTableView();
        table.setItems(producten);
        table.setEditable(true);
    }

    /**
     *  deze methode delegeert naar de savefactuur methode in deze klasse
     */
    private void createSaveButton() {
        Button button = new Button("Opslaan");
        button.getStyleClass().addAll("btn", "btn-primary");
        button.setOnAction(event -> {
            try {
                saveFactuur();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        setHalignment(button, HPos.RIGHT);
        add(button, 2, currentRow);
        currentRow++;
    }


    /**
     * deze methode maakt een nieuwe factuur pdf aan en slaat de factuur en factuurrgels op in de database
     * @throws Exception
     */
    private void saveFactuur() throws Exception {
        new FactuurPdf(factuur, factuur.getFactuurregels(), factuur.getDebiteur());
        facturenController.cmdSaveFactuur(factuur);
        factuur.setFactuurregels(factuur.getFactuurregels());
    }

    /**
     * deze methode maakt de titel aan

     */
    private void createTitle() {
        Text title = new Text("Nieuwe Factuur");
        title.getStyleClass().addAll("h1");
        add(title, 0, currentRow, 2, 1);
    }

    @Override
    public void show() {
        this.stage.setScene(new Scene(this, 1024, 768));
        this.stage.show();
    }

    /**
     * deze methode maakt het datumveld aan voor factuurdatum dat gebruikt wordt als je een factuur gaat toevoegen
     * @param name
     * @param property
     */
    private void createDateField(String name, Property property) {
        Label label = new Label(name);
        DatePicker datePicker = new DatePicker(LocalDate.now());
        datePicker.setMaxWidth(Double.MAX_VALUE);
        setHgrow(datePicker, Priority.ALWAYS);

        factuur.setFactuurdatum(java.sql.Date.valueOf(datePicker.getValue()));

        datePicker.setOnAction(event -> {
            property.setValue(java.sql.Date.valueOf(datePicker.getValue()));
        });
        ArrayList<Node> fields = new ArrayList<>();
        fields.add(label);
        fields.add(datePicker);
        nodes.add(fields);
    }


    /**
     * deze methode maakt het datumveld aan voor vervaldatum dat gebruikt wordt als je een factuur gaat toevoegen
     * @param name
     * @param property
     */
    private void createDateFieldVervalDatum(String name, Property property) {
        Label label = new Label(name);
        DatePicker datePicker = new DatePicker(LocalDate.now().plusDays(30));
        datePicker.setMaxWidth(Double.MAX_VALUE);
        setHgrow(datePicker, Priority.ALWAYS);
        factuur.setVervaldatum(java.sql.Date.valueOf(datePicker.getValue()));

        datePicker.setOnAction(event -> {
            property.setValue(java.sql.Date.valueOf(datePicker.getValue()));
        });

        ArrayList<Node> fields = new ArrayList<>();
        fields.add(label);
        fields.add(datePicker);
        nodes.add(fields);
    }

    /**
     * deze methode is om alle inputfields netjes op een rij te zetten op de toevoegen pagina
     * @param nodes
     */
    private void addNode(ArrayList<ArrayList<Node>> nodes) {
        for(ArrayList<Node> node: nodes) {
            if (columnIndex == 3) {
                columnIndex = 0;
                currentRow+=2;
            }
            add(node.get(0), columnIndex, currentRow);
            add(node.get(1), columnIndex, currentRow+1);
            columnIndex++;
        }
    }


    /**
     * deze methode maakt het formulier aan dat ingevuld moet wworden bij het aanmaken van een factuur
     */
    private void createForm() {
        createField("Factuurnummer", factuur.factuurnummerProperty(), new IntegerStringConverter());
        createDateField("Factuurdatum", factuur.factuurdatumProperty());
        createDateFieldVervalDatum("Vervaldatum", factuur.vervaldatumProperty());
        createComboBox("Lid");
        createComboBoxBestellijst("Bestellijst");
        createTextArea("Opmerking", factuur.opmerkingProperty());
        addNode(nodes);
    }

    /**
     * deze methode maakt de combobox aan waar je een debiteur mee selecteert
     * @param name
     */
    private void createComboBox(String name) {
        try {
            Label label = new Label(name);
            ArrayList<Debiteur> debiteuren = new DebiteurDAO().getAllDebiteuren();
            ChoiceBox<Debiteur> choiceBox = new ChoiceBox<>(FXCollections.observableArrayList(debiteuren));
            choiceBox.setMaxWidth(Double.MAX_VALUE);
            setHgrow(choiceBox, Priority.ALWAYS);
            choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                factuur.setDebiteur(newValue);
            });

            int i = 0;
            for (Debiteur debiteur: debiteuren) {
//                if(debiteur.getId() == factuur.getDebiteur().getId())
                choiceBox.getSelectionModel().select(debiteur);
            }
            ArrayList<Node> fields = new ArrayList<>();
            fields.add(label);
            fields.add(choiceBox);
            nodes.add(fields);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *deze methode maakt een combobo aan om een bestellijst te kunnen selecteren
     * @param name
     */
    private void createComboBoxBestellijst(String name) {
        try {
            Label label = new Label(name);
            ArrayList<Bestellijst> bestellijsten = new BestellijstDAO().allWithProducten();
            ChoiceBox<Bestellijst> choiceBox = new ChoiceBox<>(FXCollections.observableArrayList(bestellijsten));
            choiceBox.setMaxWidth(Double.MAX_VALUE);
            setHgrow(choiceBox, Priority.ALWAYS);
            choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                bestellijst = newValue;
                //System.out.println(bestellijst.getProducten());
                producten.setAll(FXCollections.observableArrayList(newValue.getProducten()));
                table.setItems(producten);
            });

            choiceBox.getSelectionModel().select(0);

            ArrayList<Node> fields = new ArrayList<>();
            fields.add(label);
            fields.add(choiceBox);
            nodes.add(fields);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * deze methode maakt de tabel aan die de producten laat zien die bij de gekozen bestellijst horen
     */
    private void createTableView() {
        table = new TableView();

        TableColumn<Product, Integer> productnummer = new TableColumn("Productnummer");
        productnummer.setCellValueFactory(new PropertyValueFactory<>("productnummer"));
        TableColumn<Product, String> naam = new TableColumn("Naam");
        naam.setCellValueFactory(new PropertyValueFactory<>("naam"));
        TableColumn<Product, Integer> jaar = new TableColumn("Jaar");
        jaar.setCellValueFactory(new PropertyValueFactory<>("jaar"));
        TableColumn<Product, Double> prijs = new TableColumn("Prijs");
        prijs.setCellValueFactory(new PropertyValueFactory<>("prijs"));

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

        add(table, 0, currentRow+2, 3, 1);
        currentRow++;
    }

    /**
     * deze methode is om een veld aan te maken dat ingevuld kan worden. bijv factuurnummer

     * @param name
     * @param property
     * @param stringConverter
     */
    private void createField(String name, Property property, StringConverter stringConverter) {
        Label label = new Label(name);
        TextField textField = new TextField(name);
        setHgrow(textField, Priority.ALWAYS);
        Bindings.bindBidirectional(textField.textProperty(), property, stringConverter);

        ArrayList<Node> fields = new ArrayList<>();
        fields.add(label);
        fields.add(textField);
        nodes.add(fields);
    }


    /**
     * deze methode maakt een textarea aan dei gebruikt wordt om een opmerking in te vullen bij een factuur
     * @param name
     * @param property
     */
    public void createTextArea(String name, Property property) {
        Label label = new Label(name);
        TextArea textArea = new TextArea(name);
        setHgrow(textArea, Priority.ALWAYS);
        textArea.setMinWidth(50);
        textArea.setMaxHeight(30);
        textArea.setPrefWidth(50);
        textArea.setMaxWidth(400);
        Bindings.bindBidirectional(textArea.textProperty(), property);

        ArrayList<Node> fields = new ArrayList<>();
        fields.add(label);
        fields.add(textArea);
        nodes.add(fields);
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

