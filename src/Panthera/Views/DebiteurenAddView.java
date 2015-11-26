package Panthera.Views;

import Panthera.Controllers.DebiteurenController;
import Panthera.DAO.LandDAO;
import Panthera.Models.Debiteur;
import Panthera.Models.Land;
import Panthera.Panthera;
import javafx.beans.binding.Bindings;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * 
 * @author Victor
 * @author Brandon
 * 
 */
public class DebiteurenAddView extends GridPane implements Viewable {

	private DebiteurenController debiteurenController;
	private Stage stage = Panthera.getInstance().getStage();
	private int row = 0;
	private Debiteur debiteur;

	/**
	 * @author Victor
	 * Maakt een nieuw DebiteurenAddView aan en voegt een debiteurenController eraan toe.
	 * @param debiteurenController
	 */
	public DebiteurenAddView(DebiteurenController debiteurenController){
      setPadding(new Insets(10));
		this.debiteurenController = debiteurenController;
		this.debiteur = new Debiteur();
		createTitle();
		createForm();
		addButton();
	}
	/**
	 * @author Victor
	 * Maakt een nieuw DebiteurenAddView aan voor een bestaande debiteur en voegt een debiteurenController eraan toe.
	 * 
	 * @param debiteurenController
	 * @param rowData
	 */
	public DebiteurenAddView(DebiteurenController debiteurenController, Debiteur rowData) {
		this.debiteurenController = debiteurenController;
		this.debiteur = rowData;
		createTitle();
		createForm();
		addButton();
	}
	/**
	 * @author Victor
	 * Maakt de knop Opslaan en voegt een listener eraan toe.
	 * listener roept de methode addDebiteur() aan.
	 */
	public void addButton(){
		Button button = new Button("Opslaan");
		button.getStyleClass().addAll("btn", "btn-success");
		button.setOnAction(event -> addDebiteur());
		add(button, 0,row);
		row++;
	}
	/**
	 * @author Victor
	 * Roept cmdAddDebiteur() in DebiteurenController aan en geeft de variabele debiteur mee.
	 */
	public void addDebiteur(){
		debiteurenController.cmdAddDebiteur(debiteur);
	}
	/**
	 * @author Victor
	 * Maakt titel aan.
	 */
	private void createTitle() {
		Text title = new Text("Toevoegen Lid");
		title.getStyleClass().addAll("h1");
		title.setFont(Font.font(20));
		add(title, 0, row);
		row++;
	}
	/**
	 * @author Victor
	 * @author Brandon
	 * Maakt overzicht van gegevens aan.
	 */
	public void createForm(){
		createField("Aanhef", debiteur.aanhefProperty());
		createField("Voornaam", debiteur.voornaamProperty());
		createField("Tussenvoegsel", debiteur.tussenvoegselProperty());
		createField("Naam", debiteur.naamProperty());
		createField("Adres", debiteur.adresProperty());
		createField("Woonplaats", debiteur.woonplaatsProperty());
		createField("Postcode", debiteur.postcodeProperty());
		createField("Email", debiteur.emailProperty());
		createField("Telefoon", debiteur.telefoonProperty());
		createComboBox("Land");
	}
	/**
	 * 
	 * Maakt een combobox aan van landen mbv LandDAO
	 * @param name
	 */
	private void createComboBox(String name) {
		try {
			Label label = new Label(name);
			ArrayList<Land> landen = new LandDAO().all();
			ChoiceBox<Land> choiceBox = new ChoiceBox<>(FXCollections.observableArrayList(landen));
			choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
				debiteur.setLand(newValue);
			});
			// TODO: Better fix this.

			for (Land land: landen) {
					choiceBox.getSelectionModel().select(land);
			}
			add(label, 0, row);
			add(choiceBox, 1, row);
			row++;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @author Victor
	 * @author Brandon
	 * Maakt velden voor de overzicht aan met een label en textfield met een property
	 * @param name	naam van de label
	 * @param property	property van het object
	 */
	private void createField(String name, Property property) {
		Label label = new Label(name);
		TextField textField = new TextField(name);
		Bindings.bindBidirectional(textField.textProperty(), property);

		add(label, 0, row);
		add(textField, 1, row);
		row++;
	}
	/**
	 * Toont view
	 */
	@Override
	public void show() {
		// TODO Auto-generated method stub
		this.stage.setScene(new Scene(this));
		this.stage.show();
		
	}
}
