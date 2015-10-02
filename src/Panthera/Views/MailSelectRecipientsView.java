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
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MailSelectRecipientsView extends BorderPane implements Viewable {
    private String bericht;
    private String onderwerp;
    private ObservableList<Debiteur> debiteuren;
    private MailController mailController;
    private Stage stage = Panthera.getInstance().getStage();
    private TableView table;
    private VBox topContainer = new VBox(10);

    public MailSelectRecipientsView(MailController mailController, String onderwerp, String bericht) {
        this.mailController = mailController;
        this.onderwerp = onderwerp;
        this.bericht = bericht;
        try {
            debiteuren = FXCollections.observableArrayList(new DebiteurDAO().getAllDebiteuren());
        } catch (Exception e) {
            e.printStackTrace();
        }

        createTableView();

        setPadding(new Insets(10));

        createButton("Verstuur", event -> mailController.cmdSendDankwoord(debiteuren, onderwerp, bericht));
        setTop(topContainer);
        table.setItems(debiteuren);

        for (Debiteur debiteur: debiteuren) {
            debiteur.activeProperty().addListener((observable, oldValue, newValue) -> {
                debiteur.setActive(newValue);
            });
        }
    }

    /**
     * Creates a Button and binds it to a method in the Controller.
     *
     * @param name          Name of the button.
     * @param eventhandler  EventHandler for button action.
     */
    private void createButton(String name, javafx.event.EventHandler eventhandler) {
        Button button = new Button(name);
        button.setOnAction(eventhandler);
        setBottom(button);
    }

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
    public void createSelectAllButton() {

        CheckBox cb = new CheckBox("Select all");
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
