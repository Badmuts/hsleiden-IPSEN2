package Panthera.Views;

import Panthera.Panthera;
import Panthera.Controllers.BestellijstenController;
import Panthera.Models.Bestellijst;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
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
	private Stage stage;
	private HBox topContainer = new HBox(10);
	private TableView<Bestellijst> table;
	
	public BestellijstenSummaryView(BestellijstenController bestellijstenController) {
		this.bestellijstenController = bestellijstenController;
		this.stage = Panthera.getInstance().getStage();
		createHeader();
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
		this.table = new TableView();
	}
	
	/**
	 * Create the header.
	 */
	public void createHeader() {
		createTile();
		setTop(topContainer);
		createAddBestellijstenButton();
	}
	
	/**
	 * Create the window title text and add it into topContainer.
	 */
	public void createTile() {
		Text title = new Text("Bestellijst Opstellen");
		title.setFont(Font.font(22));
		topContainer.getChildren().add(title);
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
