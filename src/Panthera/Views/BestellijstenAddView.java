package Panthera.Views;

import Panthera.Panthera;
import Panthera.Controllers.BestellijstenController;
import Panthera.Controllers.ProductenController;
import Panthera.Models.Factuur;
import Panthera.Models.Product;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
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
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;


/**
 * Window for creating a new Bestellijst.
 * @author Roy
 *
 */
public class BestellijstenAddView extends BorderPane implements Viewable {
	
	private Stage stage;
	private BestellijstenController bestellijstenController;
	private ProductenController productenController;
	private ObservableList<Product> producten;
	private TableView<Product> table;
	private HBox topContainer = new HBox(10);
	public final ObservableList<Long> checkedMessages = FXCollections
	            .observableArrayList(new Long(1));
	
	
	
	public BestellijstenAddView(BestellijstenController bestellijstenController, ProductenController productenController) {
		this.stage = Panthera.getInstance().getStage();
		setPadding(new Insets(10));
		this.bestellijstenController = bestellijstenController;
		this.productenController = productenController;
		this.producten = this.productenController.cmdGetProducten();
		createHeader();
		createTableView();
		table.setItems(producten);
	}
	
	/**
	 * Create the header.
	 */
	public void createHeader() {
		createTitle();
		createOpslaanButton();
		createAnnuleerButton();
		setTop(topContainer);
	}
	
	/**
	 * Create the window title text and add it into topContainer.
	 */
	public void createTitle() {
		Text title = new Text("Bestellijst maken");
		title.getStyleClass().add("h1");
		//title.setFont(Font.font(22));
		topContainer.getChildren().add(title);
	}
	
	public void createAnnuleerButton() {
		Button button = new Button("Annuleer");
		button.getStyleClass().addAll("btn", "btn-danger");
		button.setOnAction(e -> this.bestellijstenController.setView(bestellijstenController.openBestellijstenSummaryView()).show());
		topContainer.getChildren().add(button);
	}
	
	public void createOpslaanButton() {
		Button button = new Button("Opslaan");
		button.getStyleClass().addAll("btn", "btn-success");
		button.setOnAction(e -> this.bestellijstenController.opslaanBestellijst(producten));
		topContainer.getChildren().add(button);
	}
	
	/**
	 * Create the product table with a checkbox.
	 */
	public void createTableView() {
		this.table = new TableView<>();
		  TableColumn<Product, CheckBox> checkbox = new TableColumn(" ");
	        checkbox.setCellValueFactory(param -> {
	                       CheckBox checkBox = new CheckBox();
	                        Bindings.bindBidirectional(checkBox.selectedProperty(), param.getValue().activeProperty());
	                        return new SimpleObjectProperty<>(checkBox);
	        });
		
		TableColumn productnummer = new TableColumn("productnummer");
		productnummer.setCellValueFactory(new PropertyValueFactory<Product, Integer>("productnummer"));		
		TableColumn naam = new TableColumn("naam");
		naam.setCellValueFactory(new PropertyValueFactory<Product, String>("naam"));		
		TableColumn jaar = new TableColumn("jaar");
		jaar.setCellValueFactory(new PropertyValueFactory<Product, Integer>("jaar"));		
		TableColumn prijs = new TableColumn("prijs");
		prijs.setCellValueFactory(new PropertyValueFactory<Product, Double>("prijs"));		
		TableColumn type = new TableColumn("type");
		type.setCellValueFactory(new PropertyValueFactory<Product, String>("type"));		
		TableColumn land = new TableColumn("land");
		land.setCellValueFactory(new PropertyValueFactory<Product, String>("land"));		
		TableColumn rang = new TableColumn("rang");
		rang.setCellValueFactory(new PropertyValueFactory<Product, Integer>("rang"));				
        
        
        
        this.table.getColumns().addAll(productnummer, naam, jaar, prijs, type, land, rang, checkbox);
		setCenter(this.table);
	}
	
	@Override
	public void show() {
		this.stage.setScene(new Scene(this, 800, 600));
		this.stage.show();
	}

}
