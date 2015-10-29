package Panthera.Views;

import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import Panthera.Models.Land;
import Panthera.Panthera;
import Panthera.Controllers.DebiteurenController;
import Panthera.DAO.EventDAO;
import Panthera.Models.Debiteur;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class DebiteurenListView extends BorderPane implements Viewable {
	
	private DebiteurenController debiteurenController;
	private Stage stage = Panthera.getInstance().getStage();
	private TableView<Debiteur> table;
	private ObservableList<Debiteur> debiteuren = FXCollections.observableArrayList();
	private FilteredList<Debiteur> filteredData;
	private TextField filterField;
	private HBox topContainer = new HBox(10);
	private EventDAO eventDao;

	public DebiteurenListView(DebiteurenController debiteurenController) {
      setPadding(new Insets(22));
      topContainer.setPadding(new Insets(0, 0, 10, 0));
		this.debiteurenController = debiteurenController;
		this.debiteuren = this.debiteurenController.cmdGetDebiteuren();
		try {
			this.eventDao = new EventDAO();
			eventDao.setAanwezig(debiteuren);
		} catch (IllegalAccessException | InstantiationException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		createHeader();
		createTableView();
		table.setItems(debiteuren);
		filterDebiteuren();
	}

	private void createImportButton() throws Exception {
		Button button = new Button("Importeer leden");
		button.setOnAction(event -> {
			try {
				importeerLeden();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		button.getStyleClass().addAll("btn", "btn-danger");
		topContainer.getChildren().add(button);
	}

	public void importeerLeden() throws Exception {

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Bookmark Files", "*.xls"));
		File selectedFile = fileChooser.showOpenDialog(stage);

		FileInputStream fstream = new FileInputStream(selectedFile);

		//Instantiating a Workbook object
		Workbook workbook = new Workbook(fstream);

		//Accessing the first worksheet in the Excel file
		Worksheet worksheet = workbook.getWorksheets().get(0);

		//Exporting the contents of 7 rows and 2 columns starting from 1st cell to Array.
		Object dataTable [][] =  worksheet.getCells().exportArray(4,0,7,10);

		for (int i = 0 ; i < dataTable.length ; i++)
		{
			System.out.println("["+ i +"]: "+ Arrays.toString(dataTable[i]));
			debiteuren.add(new Debiteur((String) dataTable[i][0], (String) dataTable[i][1],(String) dataTable[i][2], (String) dataTable[i][3],  (String) dataTable[i][4],  (String) dataTable[i][5], (String) dataTable[i][6],(String) dataTable[i][7], (String) dataTable[i][8], new Land(3, "Nederland")));
		}
		//Closing the file stream to free all resources
		fstream.close();

		for(Debiteur debiteur: debiteuren) {
			this.debiteurenController.cmdAddDebiteur(debiteur);
		}
	}


	private void filterDebiteuren() {

		this.filteredData = new FilteredList<Debiteur>(this.debiteuren, p -> true);
		this.filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			this.filteredData.setPredicate(debiteur -> {
				if(newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();
				if(debiteur.getVoornaam().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				} else if(debiteur.getNaam().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				} else if(debiteur.getWoonplaats().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				}
				return false;
			});
		});

		SortedList<Debiteur> sortedData = new SortedList<>(this.filteredData);
		sortedData.comparatorProperty().bind(this.table.comparatorProperty());

		this.table.setItems(sortedData);
	}


	private void createFilterField() {
		this.filterField = new TextField();
		topContainer.getChildren().add(this.filterField);

	}

	public void createHeader()  {
		createTitle();;
		addDebiteurButton();
		removeDebiteurButton();
		try {
			createImportButton();
		} catch (Exception e) {
			e.printStackTrace();
		}
		createFilterField();
		setTop(topContainer);
	}
	
	private void removeDebiteurButton() {
		Button button = new Button("Lid verwijderen");
		button.setOnAction(event -> debiteurenController.cmdDeleteDebiteur(debiteuren));
		button.getStyleClass().addAll("btn", "btn-danger");
		topContainer.getChildren().add(button);
	}

	public void addDebiteurButton(){
		Button button = new Button("Nieuw Lid");
		button.setOnAction(e -> this.debiteurenController.getMainController().setSubview((new DebiteurenAddView(this.debiteurenController))));
		button.getStyleClass().addAll("btn", "btn-primary");
		topContainer.getChildren().add(button);
	}


	private void createTableView() {
		table = new TableView();
		TableColumn<Debiteur, CheckBox> checkbox = new TableColumn(" ");
		checkbox.setCellValueFactory(param -> {
			CheckBox checkBox = new CheckBox();
			Bindings.bindBidirectional(checkBox.selectedProperty(), param.getValue().activeProperty());
			return new SimpleObjectProperty<>(checkBox);
		});
		TableColumn<Debiteur, CheckBox> checkbox2 = new TableColumn("Aanwezig");
		checkbox2.prefWidthProperty().bind(table.widthProperty().divide(10));
		checkbox2.setCellValueFactory(param -> {
			CheckBox checkBox = new CheckBox();
			
			if(param.getValue().isPresentBool()) {
				checkBox.setSelected(true);
			}
			//Bindings.bindBidirectional(checkBox.selectedProperty(), param.getValue().isPresent());
			checkBox.setOnAction(e -> {
				if(checkBox.isSelected()) {
					debiteurenController.setPresent(param.getValue());
				} else {
					debiteurenController.setNotPresent(param.getValue());
				}
			});
			return new SimpleObjectProperty<>(checkBox);
		});
		
		TableColumn<Debiteur, String> aanhef = new TableColumn("Aanhef");
		aanhef.setCellValueFactory(new PropertyValueFactory<>("aanhef"));
		aanhef.prefWidthProperty().bind(table.widthProperty().divide(12));
		TableColumn<Debiteur, String> voornaam = new TableColumn("Voornaam");
		voornaam.setCellValueFactory(new PropertyValueFactory<>("voornaam"));
		voornaam.prefWidthProperty().bind(table.widthProperty().divide(10));
		TableColumn<Debiteur, String> tussenvoegsel = new TableColumn("Tussenvoegsel");
		tussenvoegsel.setCellValueFactory(new PropertyValueFactory<>("tussenvoegsel"));
		tussenvoegsel.prefWidthProperty().bind(table.widthProperty().divide(10));
		TableColumn<Debiteur, String> naam = new TableColumn("Achternaam");
		naam.setCellValueFactory(new PropertyValueFactory<>("naam"));
		naam.prefWidthProperty().bind(table.widthProperty().divide(10));
		TableColumn<Debiteur, String> adres = new TableColumn("Adres");
		adres.setCellValueFactory(new PropertyValueFactory<>("adres"));
		adres.prefWidthProperty().bind(table.widthProperty().divide(10));
		TableColumn<Debiteur, String> woonplaats = new TableColumn("Woonplaats");
		woonplaats.setCellValueFactory(new PropertyValueFactory<>("woonplaats"));
		woonplaats.prefWidthProperty().bind(table.widthProperty().divide(10));
		TableColumn<Debiteur, String> postcode = new TableColumn("Postcode");
		postcode.setCellValueFactory(new PropertyValueFactory<>("Postcode"));
		postcode.prefWidthProperty().bind(table.widthProperty().divide(10));
		TableColumn<Debiteur, Integer> telefoon = new TableColumn("Telefoon");
		telefoon.setCellValueFactory(new PropertyValueFactory<>("telefoon"));
		telefoon.prefWidthProperty().bind(table.widthProperty().divide(10));
		TableColumn<Debiteur, String> land = new TableColumn("Land");
		land.setCellValueFactory(new PropertyValueFactory<>("land"));
		land.prefWidthProperty().bind(table.widthProperty().divide(10));
		
		addClicklistener();
		table.getColumns().addAll(checkbox, aanhef, voornaam, tussenvoegsel, naam, adres, woonplaats, postcode, telefoon, land, checkbox2);
		setCenter(table);
	}

    private void addClicklistener() {
        table.setRowFactory( tv -> {
            TableRow<Debiteur> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Debiteur rowData = row.getItem();
                    debiteurenController.getMainController().setSubview(new DebiteurenAddView(debiteurenController, rowData));
                }
            });
            return row ;
        });
    }

	private void createTitle() {
		Text title = new Text("Leden");
		title.setFont(Font.font(22));
		title.getStyleClass().add("h1");
		topContainer.getChildren().add(title);
		topContainer.setAlignment(Pos.CENTER_RIGHT);
	}

    public void show() {
        this.stage.setScene(new Scene(this, 800, 600));
        this.stage.show();
    }

}
