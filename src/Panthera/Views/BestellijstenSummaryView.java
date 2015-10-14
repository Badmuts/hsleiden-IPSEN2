package Panthera.Views;

import Panthera.Controllers.BestellijstenController;
import Panthera.Controllers.MainController;
import Panthera.Controllers.PrintController;
import Panthera.Models.Bestellijst;
import Panthera.Panthera;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

import java.util.Date;
import java.util.List;

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
	private MainController mainController;
	
	public BestellijstenSummaryView(BestellijstenController bestellijstenController, MainController mainController) {
		this.bestellijstenController = bestellijstenController;
		this.printController = new PrintController();
		this.stage = Panthera.getInstance().getStage();
		this.bestellijsten = bestellijstenController.cmdGetBestellijsten();
		this.mainController = mainController;
		createHeader();
		createTableView();
		table.setItems(bestellijsten);
		setPadding(new Insets(22));
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
		createAddBestellijstenButton();
		createVerwijderButton();
		createPrintButton();
	}
	
	/**
	 * Create the window title text and add it into topContainer.
	 */
	public void createTile() {
		Text title = new Text("Bestellijsten overzicht");
		title.getStyleClass().add("h1");
		title.setFont(Font.font(22));
		topContainer.getChildren().add(title);
		topContainer.setAlignment(Pos.CENTER_RIGHT);
	}
	
	public void createPrintButton() {
		Button button = new Button("Print");
		button.setOnAction(e -> {
			//create a new list of bestellijst objects by letting bestellijstenController filter this.bestellijsten.
			List<Bestellijst> filteredBestellijsten = bestellijstenController.filterUnselectedBestellijsten((List)bestellijsten);
			this.printController.print(filteredBestellijsten, mainController);
		});
		topContainer.getChildren().add(button);
	}
	
	public void createVerwijderButton() {
		Button button = new Button("Verwijder");
		button.setOnAction(e -> this.bestellijstenController.verwijder(bestellijsten));
		button.getStyleClass().addAll("btn", "btn-danger");
		topContainer.getChildren().add(button);
	}
		
	/**
	 * Create the AddBestelLijstenButton and add it into topContainer.
	 */
	public void createAddBestellijstenButton() {
		Button button = new Button("Bestellijst toevoegen");
		button.setOnAction(e -> this.bestellijstenController.getMainController().setSubview(bestellijstenController.openBestellijstenAddView()));
		button.getStyleClass().addAll("btn", "btn-primary");
		topContainer.getChildren().add(button);
	}

}
