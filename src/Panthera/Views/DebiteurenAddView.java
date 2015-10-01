package Panthera.Views;

import Panthera.Controllers.DebiteurenController;
import Panthera.DAO.LandDAO;
import Panthera.Models.Debiteur;
import Panthera.Models.Land;
import Panthera.Panthera;
import javafx.beans.binding.Bindings;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

/**
 * 
 * @author Victor
 * 
 */
public class DebiteurenAddView extends GridPane implements Viewable {
	
	private Stage stage = Panthera.getInstance().getStage();
	private DebiteurenController debiteurenController;
	private Debiteur debiteur;
	private int row = 0;

	
	public DebiteurenAddView(DebiteurenController debiteurenController){
		this.debiteurenController = debiteurenController;
		this.debiteur = new Debiteur();
		Text title = new Text("Toevoegen Klant");
		add(title,0,row);
		row++;
	}

	public DebiteurenAddView(DebiteurenController debiteurenController, Debiteur rowData) {
		this.debiteurenController = debiteurenController;
		this.debiteur = rowData;
		createTitle();
		createForm();
		addButton();
	}


	private void createTitle() {
		Text title = new Text("Product toevoegen");
		title.setFont(Font.font(20));
		add(title, 0, row);
		row++;
	}

	public void addButton(){
		Button button = new Button("Opslaan");
		button.setOnAction(event -> addDebiteur());
		add(button, 0,row);
		row++;
	}
	public void addDebiteur(){
		debiteurenController.cmdAddDebiteur(debiteur);
	}

	public void createForm(){
		createField("Aanhef", debiteur.aanhefProperty());
		createField("Voornaam", debiteur.voornaamProperty());
		createField("Tussenvoegsel", debiteur.tussenvoegselProperty());
		createField("Naam", debiteur.naamProperty());
		createField("Adres", debiteur.adresProperty());
		createField("Woonplaats", debiteur.woonplaatsProperty());
		createField("Postcode", debiteur.postcodeProperty());
		createField("Email", debiteur.emailProperty());
		createField("Telefoon", debiteur.telefoonProperty(), new NumberStringConverter());
		createComboBox("Land");
	}
	private void createField(String name, Property property, StringConverter converter) {
		Label label = new Label(name);
		TextField textField = new TextField(name);
		Bindings.bindBidirectional(textField.textProperty(), property, converter);
		add(label, 0,row);
		add(textField, 1, row);
		row++;
	}

	private void createField(String name, Property property) {
		Label label = new Label(name);
		TextField textField = new TextField(name);
		Bindings.bindBidirectional(textField.textProperty(), property);

		add(label, 0, row);
		add(textField, 1, row);
		row++;
	}

	private void createComboBox(String name) {
        try {
            Label label = new Label(name);
            ChoiceBox<Land> choiceBox = new ChoiceBox<>(FXCollections.observableArrayList(new LandDAO().all()));
            choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                debiteur.setLand(newValue);
            });
            choiceBox.getSelectionModel().select(0);
            add(label, 0, row);
            add(choiceBox, 1, row);
            row++;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
}
