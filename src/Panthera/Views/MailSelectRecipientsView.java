package Panthera.Views;

import Panthera.Controllers.MailController;
import Panthera.DAO.DebiteurDAO;
import Panthera.Models.Debiteur;
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
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * View used to select recipients of the email.
 *
 * @author Daan Rosbergen
 */
public class MailSelectRecipientsView extends BorderPane implements Viewable {
    private String bericht;
    private String onderwerp;
    private ObservableList<Debiteur> debiteuren;
    private MailController mailController;
    private Stage stage = Panthera.getInstance().getStage();
    private TableView table;
    private HBox topContainer = new HBox(10);
    private TextField filterField;

    /**
     *
     *
     * @param mailController    MailController  Controller used for the view.
     * @param onderwerp         String          Subject of the email.
     * @param bericht           String          Message of the email.
     */
    public MailSelectRecipientsView(MailController mailController, String onderwerp, String bericht) {
        this.mailController = mailController;
        this.onderwerp = onderwerp;
        this.bericht = bericht;
        try {
            debiteuren = FXCollections.observableArrayList(new DebiteurDAO().getAllDebiteuren());
        } catch (Exception e) {
            e.printStackTrace();
        }
        createHeader();
        createTableView();

        setPadding(new Insets(10));

        createButton("Verstuur", event -> mailController.cmdSendDankwoord(debiteuren, onderwerp, bericht));
        setTop(topContainer);
        table.setItems(debiteuren);
        filterList();

        for (Debiteur debiteur: debiteuren) {
            debiteur.activeProperty().addListener((observable, oldValue, newValue) -> {
                debiteur.setActive(newValue);
            });
        }
    }

    /**
     * Create header.
     */
    private void createHeader() {
        createTitle();
        createSearchField();
        setTop(topContainer);
    }

    /**
     * Create searchfield.
     */
    private void createSearchField() {
        filterField = new TextField();
        filterField.promptTextProperty().setValue("Zoeken...");
        topContainer.getChildren().addAll(filterField);
    }

    /**
     * Create title.
     */
    private void createTitle() {
        Text title = new Text("Selecteer ontvangers");
        title.getStyleClass().add("h1");
        topContainer.getChildren().add(title);
        topContainer.setAlignment(Pos.CENTER_RIGHT);
    }

    /**
     * Filter list according to value form the searchfield.
     */
    private void filterList() {
        FilteredList<Debiteur> filteredData = new FilteredList<Debiteur>(this.debiteuren, p -> true);

        this.filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(debiteur -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (debiteur.getNaam().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (debiteur.getVoornaam().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (debiteur.getEmail().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (debiteur.getWoonplaats().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<Debiteur> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(this.table.comparatorProperty());

        this.table.setItems(sortedData);
    }

    /**
     * Creates a Button and binds it to a method in the Controller.
     *
     * @param name          Name of the button.
     * @param eventhandler  EventHandler for button action.
     */
    private void createButton(String name, javafx.event.EventHandler eventhandler) {
        Button button = new Button(name);
        button.getStyleClass().addAll("btn", "btn-success");
        button.setOnAction(eventhandler);
        setBottom(button);
    }

    /**
     * Create tableview.
     */
    private void createTableView() {
        table = new TableView();
        TableColumn<Debiteur, CheckBox> checkbox = new TableColumn(" ");
        checkbox.setCellValueFactory(param -> {
            CheckBox checkBox = new CheckBox();
            Bindings.bindBidirectional(checkBox.selectedProperty(), param.getValue().activeProperty());
            return new SimpleObjectProperty<>(checkBox);
        });
        TableColumn<Debiteur, String> aanhef = new TableColumn("Aanhef");
        aanhef.setCellValueFactory(new PropertyValueFactory<>("aanhef"));
        TableColumn<Debiteur, String> voornaam = new TableColumn("Voornaam");
        voornaam.setCellValueFactory(new PropertyValueFactory<>("voornaam"));
        TableColumn<Debiteur, String> tussenvoegsel = new TableColumn("Tussenvoegsel");
        tussenvoegsel.setCellValueFactory(new PropertyValueFactory<>("tussenvoegsel"));
        TableColumn<Debiteur, String> naam = new TableColumn("Naam");
        naam.setCellValueFactory(new PropertyValueFactory<>("naam"));
        TableColumn<Debiteur, String> adres = new TableColumn("Adres");
        adres.setCellValueFactory(new PropertyValueFactory<>("adres"));
        TableColumn<Debiteur, String> woonplaats = new TableColumn("Woonplaats");
        woonplaats.setCellValueFactory(new PropertyValueFactory<>("woonplaats"));
        TableColumn<Debiteur, String> postcode = new TableColumn("Postcode");
        postcode.setCellValueFactory(new PropertyValueFactory<>("Postcode"));
        TableColumn<Debiteur, Integer> telefoon = new TableColumn("Telefoon");
        telefoon.setCellValueFactory(new PropertyValueFactory<>("telefoon"));
        TableColumn<Debiteur, String> land = new TableColumn("Land");
        land.setCellValueFactory(new PropertyValueFactory<>("land"));

        createSelectAllButton();

        table.getColumns().addAll(checkbox, aanhef, voornaam, tussenvoegsel, naam, adres, woonplaats, postcode, telefoon, land);
        setCenter(table);

    }

    /**
     * Create select all button.
     */
    public void createSelectAllButton() {

        CheckBox cb = new CheckBox("Selecteer alles");
        cb.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> ov,
                                Boolean old_val, Boolean new_val) {
                if (new_val) {
                    for (Debiteur d : debiteuren) {
                        d.activeProperty().set(new_val);
                    }
                }
                else {
                    for (Debiteur d : debiteuren) {
                        d.activeProperty().set(false);
                    }
                }
            }
        });

        topContainer.getChildren().add(cb);
    }

    /**
     * Viewable method shows this view.
     */
    @Override public void show() {
        this.stage.setScene(new Scene(this));
        this.stage.show();
    }
}
