package Panthera.Views;

import java.util.Date;

import Panthera.Panthera;
import Panthera.Controllers.BestellijstenController;
import Panthera.Controllers.PrintController;
import Panthera.Models.Bestellijst;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Bestellijst view,
 * window to add, and import bestellijsten.
 * @author Roy
 *
 */
public class BestellijstenSummaryView extends BorderPane implements Viewable{
	private BestellijstenController bestellijstenController;
	private PrintController printController;
	private Stage stage;
	private HBox topContainer = new HBox(10);
	private TableView<Bestellijst> table;
	private ObservableList<Bestellijst> bestellijsten;
	
	public BestellijstenSummaryView(BestellijstenController bestellijstenController) {
		this.bestellijstenController = bestellijstenController;
		this.printController = new PrintController();
		this.stage = Panthera.getInstance().getStage();
		this.bestellijsten = bestellijstenController.cmdGetBestellijsten();
		createHeader();
		createTableView();
		table.setItems(bestellijsten);
	}
	
	@Override
	public void show() {
		this.stage.setScene(new Scene(this, 800, 600));
		this.stage.show();
	}
		
	/**
	 * Create bestellijst table.
	 */
	public void createTableView() {
		this.table = new TableView<>();
		  TableColumn<Bestellijst, CheckBox> checkbox = new TableColumn(" ");
	        checkbox.setCellValueFactory(param -> {
	                       CheckBox checkBox = new CheckBox();
	                        Bindings.bindBidirectional(checkBox.selectedProperty(), param.getValue().activeProperty());
	                        return new SimpleObjectProperty<>(checkBox);
	        });
		
		TableColumn id = new TableColumn("id");
		id.setCellValueFactory(new PropertyValueFactory<Bestellijst, Integer>("id"));
		TableColumn name = new TableColumn("name");
		name.setCellValueFactory(new PropertyValueFactory<Bestellijst, String>("naam"));
		TableColumn date = new TableColumn("date");
		date.setCellValueFactory(new PropertyValueFactory<Bestellijst, Date>("date"));
		
		this.table.getColumns().addAll(id, name, date, checkbox);
		setCenter(this.table);
	}
	
	/**
	 * Create the header.
	 */
	public void createHeader() {
		createTile();
		setTop(topContainer);
		createTerugButton();
		createAddBestellijstenButton();
		createVerwijderButton();
		createPrintButton();
	}
	
	/**
	 * Create the window title text and add it into topContainer.
	 */
	public void createTile() {
		Text title = new Text("Bestellijsten overzicht");
		title.setFont(Font.font(22));
		topContainer.getChildren().add(title);
	}
	
	public void createPrintButton() {
		Button button = new Button("Print");
		button.setOnAction(e -> this.printController.print(bestellijsten));
		topContainer.getChildren().add(button);
	}
	
	public void createVerwijderButton() {
		Button button = new Button("Verwijder");
		button.setOnAction(e -> this.bestellijstenController.verwijder(bestellijsten));
		topContainer.getChildren().add(button);
	}
	
	public void createTerugButton() {
		Button button = new Button("Terug");
		button.setOnAction(e -> this.bestellijstenController.mainMenu());
		topContainer.getChildren().add(button);
	}
	
	/**
	 * Create the AddBestelLijstenButton and add it into topContainer.
	 */
	public void createAddBestellijstenButton() {
		Button button = new Button("Bestellijst toevoegen");
		button.setOnAction(e -> this.bestellijstenController.setView(bestellijstenController.openBestellijstenAddView()).show());
		topContainer.getChildren().add(button);
	}

}
