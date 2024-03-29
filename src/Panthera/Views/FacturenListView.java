package Panthera.Views;

import Panthera.Controllers.FacturenController;



import Panthera.Factories.CheckBoxCellFactory;
import Panthera.Models.Debiteur;
import Panthera.Models.Factuur;
import Panthera.Panthera;

import com.itextpdf.text.pdf.PdfDocument;

import Panthera.Controllers.InkoopfactuurController;
import Panthera.Models.Factuur;
import Panthera.Panthera;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by Brandon on 23-Sep-15.
 */
public class FacturenListView extends BorderPane implements Viewable {


    /**
     * attributen
     */
    private FacturenController facturenController;
    private Stage stage = Panthera.getInstance().getStage();
    private TableView<Factuur> table;
    private ObservableList<Factuur> facturen = FXCollections.observableArrayList();
    private FilteredList<Factuur> filteredData;
    private TextField filterField;
    private HBox topContainer = new HBox(10);
    private InkoopfactuurController inkoopfactuurController;


    /**
     * @author Brandon van Wijk
     * @author Daan Rosbergen
     * Dit is de constructor. Deze zet de facturencontroller om te kunnen delegeren
     * Naar de controller. Ook wordt de header en table aangeroepen.
     * Vervolgens wordt in een aparte thread de tabel gevuld met alle facturen.
     * @param facturenController
     */
    public FacturenListView(FacturenController facturenController)  {
        setPadding(new Insets(22));
        topContainer.setPadding(new Insets(0, 0, 22, 0));
        this.facturenController = facturenController;
        this.inkoopfactuurController = new InkoopfactuurController();
        createHeader();
        createTableView();

        new Thread(() -> {
            this.facturen = this.facturenController.cmdGetFacturen();
            table.setItems(facturen);
            filterFacturen();
        }).start();

    }

    /**
     * @author Brandon van Wijk
     * @author Daan Rosbergen
     * Deze methode zorgt ervoor dat je in het zoekveld kan zoeken
     * Op bepaalde zoektermen zoals: Voornaam, Achternaam en factuurnummer.
     */
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

    /**
     * @author Brandon van Wijk
     * @author Daan Rosbergen
     * Deze methode roept alle methods aan om de header van het overzicht te maken.
     */
    private void createHeader() {
        createTitle();
        createTextField();
        createAddFactuurButton();
        createRemoveFactuurButton();
        CreateVerzendFactuurButton();
        createUpdateFactuurButton();
        createGenerateInkoopfactuurButton();
        setTop(topContainer);
    }
    
    /**
     * Creates the button to generate an inkoopfactuur.
     * bit of a long name.
     * @author Roy
     */
    private void createGenerateInkoopfactuurButton() {
    	System.out.println("test");
    	Button button = new Button("Inkoopfactuur");
        button.getStyleClass().addAll("btn", "btn-success");
    	button.setOnAction(e -> {
            inkoopfactuurController.generateInkoopfactuur(facturen);
        });
    	topContainer.getChildren().add(button);
    }

    /**
     * @author Brandon van Wijk
     * Deze methode maakt de verwijder button aan. Als er op deze
     * Button wordt geklikt dan wordt er gedelegeert naar de delete methode in
     * De controller.
     */
    private void createRemoveFactuurButton() {
        Button button = new Button("Verwijder factuur");
        button.setOnAction(event -> facturenController.cmcDeleteFactuur(facturen));
        button.getStyleClass().addAll("btn", "btn-danger");
        topContainer.getChildren().add(button);
    }

    /**
     * @author Brandon van Wijk
     * Deze methode maakt de verzend button aan om een factuur te kunnen versturen
     * Het event delegeert weer naar de controller die het verder afhandeld.
     */
    private void CreateVerzendFactuurButton() {
        Button button = new Button("Verzend factuur");
        button.getStyleClass().addAll("btn", "btn-success");
        button.setOnAction(event -> facturenController.cmdSendFactuur(facturen));
        topContainer.getChildren().add(button);
    }

    /**
     * @author Brandon van Wijk
     * Deze methode maakt de update button aan zodat de gebruiker de status van een
     * Factuur kan updaten. Het event delegeert weer naar de controller die het verder afhandeld.
     */
    private void createUpdateFactuurButton() {
        Button button = new Button("Verwerk betaling");
        button.getStyleClass().addAll("btn", "btn-success");
        button.setOnAction(event -> facturenController.cmdUpdateStatus(facturen, "Betaald"));
        topContainer.getChildren().add(button);
    }

    /**
     * @author Brandon van Wijk
     * @author Daan Rosbergen
     * Deze methode maakt de table aan die alle facturen laat zien in het overzicht.
     * De tablecolumns worden gevuld met properties die de get methode gebruikt
     * Van de modelklasse die gerepresenteerd wordt in de tableview.
     * In dit geval Factuur.
     */
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

        TableColumn<Factuur, String> naam = new TableColumn<>("Naam");
        naam.setCellValueFactory(param -> {
            String fullName = param.getValue().getDebiteur().getVoornaam()
                + " " + param.getValue().getDebiteur().getTussenvoegsel()
                + " " + param.getValue().getDebiteur().getNaam();
            return new SimpleObjectProperty<String>(fullName);
        });
        naam.getStyleClass().add("table-strong");

        TableColumn<Factuur, String> factuurdatum = new TableColumn("Factuurdatum");
        factuurdatum.setCellValueFactory(param -> {
            SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM y");
            java.util.Date date = new java.util.Date(param.getValue().getFactuurdatum().getTime());
            String finalDate = dateFormat.format(date);
            return new SimpleObjectProperty<String>(finalDate);
        });

        TableColumn<Factuur, String> bedrag = new TableColumn("Bedrag");
        bedrag.setCellValueFactory(param -> {
            DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.GERMAN);
            otherSymbols.setDecimalSeparator(',');
            otherSymbols.setGroupingSeparator('.');
            DecimalFormat df = new DecimalFormat("0.00", otherSymbols);
            return new SimpleObjectProperty<String>("\u20ac " + df.format(param.getValue().getBedrag()));
        });
        bedrag.getStyleClass().addAll("table-strong", "table-text-right");

        TableColumn status = new TableColumn("Status");
        status.setCellValueFactory(new PropertyValueFactory<Factuur, String>("status"));

        factuurnummer.prefWidthProperty().bind(table.widthProperty().divide(6));
        factuurdatum.prefWidthProperty().bind(table.widthProperty().divide(6));
        naam.prefWidthProperty().bind(table.widthProperty().divide(4));
        bedrag.prefWidthProperty().bind(table.widthProperty().divide(8));
        status.prefWidthProperty().bind(table.widthProperty().divide(6));

        addClicklistener();
        this.table.getColumns().addAll(checkbox, factuurnummer, factuurdatum, naam, bedrag, status);

        createSelectAllButton();
        setCenter(this.table);
    }

    /**
     * @author Daan Rosbergen
     * @author Brandon van Wijk && Daan Rosbergen
     * Deze methode zorgt ervoor dat als je dubbelklikt op een factuur in het overzicht
     * De bijbehorende pdf dan geopend wordt zodat je de factuur kan inkijken.
     */
    private void addClicklistener() {
        table.setRowFactory(tv -> {
            TableRow<Factuur> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Factuur rowData = row.getItem();

                    for (int i = 1; i < facturen.size(); i++) {
                        if (rowData.getId() == facturen.get(i).getId()) {
                            System.out.println(rowData.getId() + "    " + facturen.get(i).getId());
                            String pdfFile = facturen.get(i).getPdfPath();
                            System.out.println(facturen.get(i).getPdfPath());

                            //String pdfFile = "C:\\Users\\Brandon\\Desktop\\20151025-Wijk.pdf";


                            System.out.println("pdfpath " + pdfFile);
                            if (pdfFile.toString().endsWith(".pdf")) {
                                try {
                                    Runtime.getRuntime().exec(new String[]{"rundll32", "url.dll,FileProtocolHandler", pdfFile});
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    Runtime.getRuntime().exec(new String[] {"/usr/bin/open",  pdfFile});
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            i++;
                        }
                    }
                }
            });
            return row;
        });
    }

    /**
     * @author Brandon van Wijk
     * Deze methode zorgt ervoor dat je met 1 select all checkbox alle
     * specifieke checkboxes die bij een factuur hoort kan aanvinken.
     * Zo kan je gemakkelijk alles in 1 keer selecteren.
     */
    public void createSelectAllButton() {

        CheckBox cb = new CheckBox("Selecteer alles");
        cb.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> ov,
                                Boolean old_val, Boolean new_val) {
                if (new_val) {
                    for (Factuur factuur : facturen) {
                        factuur.checkedProperty().set(new_val);
                    }
                } else {
                    for (Factuur factuur : facturen) {
                        factuur.checkedProperty().set(false);
                    }
                }
            }
        });

        topContainer.getChildren().add(cb);
    }

    /**
     * @author Brandon van Wijk
     * @author Daan Rosbergen
     * Deze methode maakt het texveld aan dat gebruikt wordt met zoeken.
     */
    private void createTextField() {
        this.filterField = new TextField();
        filterField.promptTextProperty().setValue("Zoeken...");
        topContainer.getChildren().add(this.filterField);
        setAlignment(filterField, Pos.CENTER_RIGHT);
    }

    /**
     * @author Brandon van Wijk
     * @author Daan Rosbergen
     * Deze methode maakt de titel aan voor het overzicht.
     */
    private void createTitle() {
        Text title = new Text("Facturen");
        title.getStyleClass().add("h1");
        topContainer.getChildren().add(title);
        topContainer.setAlignment(Pos.CENTER_RIGHT);
    }

    /**
     * @author Brandon van Wijk
     * Deze methode maakt de nieuwe factuur button aan.
     * Het event delegeert naar de controller die het verder afhandeld.
     */
    private void createAddFactuurButton() {
        Button button = new Button("Nieuwe factuur");
        button.setOnAction(event -> {
            try {
                this.facturenController.cmdShowFactuurAddView();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        button.getStyleClass().addAll("btn", "btn-primary");
        topContainer.getChildren().add(button);
    }


    /**
     * @author Daan Rosbergen
     */
    @Override
    public void show() {
//        this.stage.setScene(new Scene(this, 800, 600));
//        this.stage.show();
    }
}
