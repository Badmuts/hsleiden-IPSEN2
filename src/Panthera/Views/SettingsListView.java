package Panthera.Views;

import Panthera.Panthera;

import Panthera.Controllers.SettingsController;
import Panthera.Models.Settings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * 
 * @author Victor
 *
 */

public class SettingsListView extends BorderPane implements Viewable {

	private SettingsController settingsController;
	private Stage stage = Panthera.getInstance().getStage();
	private TableView<Settings> table;
	private ObservableList<Settings> settings = FXCollections.observableArrayList();
	private HBox topContainer = new HBox(10);

	public SettingsListView(SettingsController settingsController) {
		topContainer.setPadding(new Insets(0, 0, 10, 0));
		;
		this.settingsController = settingsController;
		this.settings = this.settingsController.cmdGetSettings();

		createHeader();
		createTableView();
		table.setItems(settings);
	}

	public void createHeader() {
		createTitle();
		updateButton();
		setTop(topContainer);

	}

	public void createTitle() {
		Text title = new Text("Lions");
		title.setFont(Font.font(22));
		topContainer.getChildren().add(title);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void createTableView() {
		table = new TableView();
		TableColumn<Settings, String> bedrijfsnaam = new TableColumn("Bedrijfsnaam");
		bedrijfsnaam.setCellValueFactory(new PropertyValueFactory<>("bedrijfsnaam"));
		TableColumn<Settings, String> adres = new TableColumn("Adres");
		adres.setCellValueFactory(new PropertyValueFactory<>("adres"));
		TableColumn<Settings, Integer> telefoon = new TableColumn("Telefoon");
		telefoon.setCellValueFactory(new PropertyValueFactory<>("telefoon"));
		TableColumn<Settings, String> mailadres = new TableColumn("Mail");
		mailadres.setCellValueFactory(new PropertyValueFactory<>("mailadres"));
		TableColumn<Settings, Integer> kvk = new TableColumn("Kvk-nummer");
		kvk.setCellValueFactory(new PropertyValueFactory<>("kvk"));
		TableColumn<Settings, Integer> btwnummer = new TableColumn("BTW-nummer");
		btwnummer.setCellValueFactory(new PropertyValueFactory<>("BTWNummer"));
		TableColumn<Settings, String> iban = new TableColumn("iBan");
		iban.setCellValueFactory(new PropertyValueFactory<>("iBan"));
		TableColumn<Settings, String> bic = new TableColumn("BIC");
		bic.setCellValueFactory(new PropertyValueFactory<>("BIC"));
		addClicklistener();
		table.getColumns().addAll(bedrijfsnaam, adres, telefoon, mailadres, kvk, btwnummer, iban, bic);
		setCenter(table);
	}

	private void addClicklistener() {
		table.setRowFactory(tv -> {
			TableRow<Settings> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					Settings rowData = row.getItem();
					settingsController.getMainController().setSubview(new SettingsAddView(settingsController, rowData));
				}
			});
			return row;
		});
	}
	
	private void updateButton(){
		Button button = new Button("Nieuw");
		button.setOnAction(e -> this.settingsController.getMainController().setSubview(new SettingsAddView(settingsController)));
		topContainer.getChildren().add(button);
	}

	public void show() {
		this.stage.setScene(new Scene(this, 800, 600));
		this.stage.show();
	}

}
