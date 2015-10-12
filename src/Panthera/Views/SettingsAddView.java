package Panthera.Views;

import Panthera.Controllers.SettingsController;
import Panthera.Models.Settings;
import javafx.beans.binding.Bindings;
import javafx.beans.property.Property;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
		createTitle();
		createForm();
		updateButton();
	}

	public SettingsAddView(SettingsController settingsController, Settings settings) {
		this.settingsController = settingsController;
		this.settings = settings;
		createTitle();
		createForm();
		updateButton();
	}

	public void updateButton() {
		Button button = new Button("Opslaan");
		button.setOnAction(event -> updateSettings());
		add(button, 0, row);
		row++;
	}

	public void updateSettings() {
		settingsController.cmdUpdateSettings(settings);
	}

	private void createTitle() {
		Text title = new Text("Wijzigen Instellingen");
		title.setFont(Font.font(20));
		add(title, 0, row);
		row++;
	}

	public void createForm() {

		createField("Bedrijfsnaam", settings.bedrijfsnaamProperty());
		createField("Adres", settings.adresProperty());
		createField("Telefoon", settings.telefoonProperty());
		createField("E-mail adres", settings.mailadresProperty());
		createField("kvk-nummer", settings.kvkProperty());
		createField("BTW-nummer", settings.BTWNummerProperty());
		createField("iBan-nummer", settings.iBanProperty());
		createField("BIC", settings.BICProperty());
	}

	private void createField(String name, Property property) {
		Label label = new Label(name);
		TextField textField = new TextField(name);
		Bindings.bindBidirectional(textField.textProperty(), property);

		add(label, 0, row);
		add(textField, 1, row);
		row++;
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

}
