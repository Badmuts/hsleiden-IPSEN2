package Panthera.Views;

import Panthera.Controllers.FacturenController;
import Panthera.Models.Factuur;
import Panthera.Panthera;
import com.oracle.webservices.internal.api.message.PropertySet;
import javafx.beans.binding.Bindings;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.NumberStringConverter;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Brandon on 24-Sep-15.
 */
public class FacturenAddView extends GridPane implements Viewable {

    private FacturenController facturenController;
    private Stage stage = Panthera.getInstance().getStage();
    private int currentRow = 0;
    private Factuur factuur;

    public FacturenAddView(FacturenController facturenController) {
        this.facturenController = facturenController;
        this.factuur = new Factuur();
        createTitle();
        createForm();
        createSaveButton();
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
        createComboBox("Debiteur");
        createField("Factuurdatum", factuur.factuurdatumProperty(), new DateStringConverter());
        createField("Vervaldatum", factuur.vervaldatumProperty(), new DateStringConverter());
        createField("Status", factuur.statusProperty());
    }

    private void createComboBox(String name) {
        try {
            Label label = new Label(name);
            ArrayList<Debiteur> debiteuren = new DebiteurDAO.all();
            ChoiceBox<Debiteur> choiceBox = new ChoiceBox<>(FXCollections.observableArrayList(debiteuren));
            choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, NewValue) -> {
                factuur.setDebiteur(newValue);
            });

            int i = 0;
            for (Debitteur debiteur: debiteuren) {
                if(debiteur.getId() == factuur.getDebiteur().getId())
                    choiceBox.getSelectionModel().select(i);
                i++;
            }
            add(label, 0, currentRow);
            add(choiceBox, 1, currentRow);
            currentRow++;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createField(String name, Property property, StringConverter stringConverter) {
        Label label = new Label(name);
        TextField textField = new TextField(name);
        Bindings.bindBidirectional(textField.textPropery(), property, converter);

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
