package Panthera.Views;

import Panthera.Controllers.SettingsController;
import Panthera.Models.Settings;
import javafx.beans.binding.Bindings;
import javafx.beans.property.Property;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * 
 * @author Victor
 *
 */
public class SettingsAddView extends GridPane implements Viewable {

	private SettingsController settingsController;
	private int row = 0;
	private Settings settings;

	public SettingsAddView(SettingsController settingsController) {
		this.settingsController = settingsController;
		this.settings = new Settings();
		setPadding(new Insets(10));
		createTitle();
		createForm();
		saveButton();
	}

	public SettingsAddView(SettingsController settingsController, Settings settings) {
		this.settingsController = settingsController;
		this.settings = settings;
		createTitle();
		createForm();
		saveButton();
		removeButton();
	}
	/**
	 * Maakt opslaanknop aan en voegt listener eraan toe.
	 * Listener zorgt ervoor dat saveSettings() aangeroepen wordt.
	 */

	public void saveButton() {
		Button button = new Button("Opslaan");
		button.getStyleClass().addAll("btn", "btn-success");
		button.setOnAction(event -> saveSettings());
		add(button, 0, row);
		row++;
	}
	/**
	 * Maakt verwijderknop aan en voegt listener eraan toe.
	 * Listener zorgt ervoor dat removeSettings() aangeroepen wordt
	 */

	public void removeButton() {
		Button button = new Button("Verwijder");
		button.getStyleClass().addAll("btn", "btn-danger");
		button.setOnAction(event -> removeSettings());
		add(button, 3, row - 1);
		row++;
	}
	/**
	 * Roept de methode cmdSaveSettings in de settingsController aan en geeft het object settings mee.
	 */

	public void saveSettings() {
		settingsController.cmdSaveSettings(settings);
	}
	/**
	 * Roept de methode cmdRemoveSettings in de settingsController aan en geeft het object settings mee.
	 */

	public void removeSettings() {
		settingsController.cmdRemoveSettings(settings);
	}
	/**
	 * Maakt titel aan.
	 */

	private void createTitle() {
		Text title = new Text("Wijzigen Instellingen");
		title.getStyleClass().addAll("h1");
		title.setFont(Font.font(20));
		add(title, 0, row);
		row++;
	}
	/**
	 * Maakt overzicht van gegevens aan.
	 */

	public void createForm() {

		createField("Bedrijfsnaam", settings.bedrijfsnaamProperty());
		createField("Adres", settings.adresProperty());
		createField("Telefoon", settings.telefoonProperty());
		createField("kvk-nummer", settings.kvkProperty());
		createField("BTW-nummer", settings.BTWNummerProperty());
		createField("iBan-nummer", settings.iBanProperty());
		createField("BIC", settings.BICProperty());
		createField("E-mail adres", settings.mailadresProperty());
		createField("Wachtwoord", settings.passwordProperty());
		createField("Host", settings.hostProperty());
		createField("Port", settings.portProperty());
	}
	/**
	 * Maakt velden voor de overzicht aan met een label en textfield met een property
	 * @param name	naam van de label
	 * @param property	property van het object
	 */

	private void createField(String name, Property property) {
		if (name == "Wachtwoord") {
			Label label = new Label(name);
			PasswordField passwordField = new PasswordField();
			Bindings.bindBidirectional(passwordField.textProperty(), property);

			add(label, 0, row);
			add(passwordField, 1, row);
			row++;
		} else {
			Label label = new Label(name);
			TextField textField = new TextField(name);
			Bindings.bindBidirectional(textField.textProperty(), property);

			add(label, 0, row);
			add(textField, 1, row);
			row++;
		}
	}
	/**
	 * Toont view.
	 */

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

}
