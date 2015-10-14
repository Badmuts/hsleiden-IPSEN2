package Panthera.Views;

import Panthera.Panthera;
import Panthera.Controllers.PrintController;
import Panthera.Models.Debiteur;
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

public class PrintSelectieView extends BorderPane implements Viewable{
	private PrintController printController;
	private Stage stage;
	private ObservableList<Debiteur> debiteuren;
	
	public PrintSelectieView(PrintController printController, ObservableList<Debiteur> debiteuren) {
		this.printController = printController;
		this.stage = Panthera.getInstance().getStage();
		this.debiteuren = debiteuren;
		createHeader();
		createBody();
	}
	
	/**
	 * Create header and add it to view.
	 */
	public void createHeader() {
		HBox box = new HBox();
		Button printBtn = new Button("Print");
		printBtn.getStyleClass().addAll("btn", "btn-primary");
		Button printAllBtn = new Button("Print allemaal");
		printAllBtn.getStyleClass().addAll("btn", "btn-primary");
		Button cancelBtn = new Button("Annuleer");
		cancelBtn.getStyleClass().addAll("btn", "btn-danger");
		box.getChildren().addAll(createTitle(), printBtn, printAllBtn, cancelBtn);
		
		printBtn.setOnAction(e -> {
			printController.printSelected(debiteuren);
		});
		
		printAllBtn.setOnAction(e -> {
			printController.printAll(debiteuren);
		});
		
		cancelBtn.setOnAction(e -> {
			printController.cancel();
		});
		
		setTop(box);
	}
	
	/**
	 * Create Text title and return it.
	 * @return
	 */
	public Text createTitle() {
		Text title = new Text("Selecteer Gasten");
		title.getStyleClass().add("h1");
		title.setFont(Font.font(22));
		return title;		
	}
	
	/**
	 * Create table body from debiteur list.
	 */
	public void createBody() {
		TableView<Debiteur> table = new TableView<>();
		TableColumn<Debiteur, String> naam = new TableColumn<>("Gast");
		TableColumn<Debiteur, CheckBox> checkbox = new TableColumn<>(" ");
		table.getColumns().addAll(naam, checkbox);
		naam.setCellValueFactory(new PropertyValueFactory<Debiteur, String>("naam"));
		checkbox.setCellValueFactory(e -> {
			CheckBox checkBox = new CheckBox();
			Bindings.bindBidirectional(checkBox.selectedProperty(), e.getValue().activeProperty());
			return new SimpleObjectProperty<>(checkBox);
		});
		table.setItems(debiteuren);
		setCenter(table);
	}
	
	@Override
	public void show() {
		this.stage.setScene(new Scene(this, 800, 600));
		this.stage.show();
	}

}
