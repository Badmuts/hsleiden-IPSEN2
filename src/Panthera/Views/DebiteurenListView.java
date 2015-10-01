import Panthera.Panthera;
import Panthera.Controller.DebiteurenController;
import Panthera.Models.Debiteur;
import Panthera.Views.DebiteurenAddView;
import Panthera.Views.Viewable;
import Panthera.Controllers.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class DebiteurenListView extends BorderPane implements Viewable {
	
	private DebiteurenController dctrl;
	private Stage stage = Panthera.getInstance().getStage();
	private TableView table;
	private ObservableList<Debiteur> debiteuren;
	private HBox topContainer = new HBox(10);
	
	public DebiteurenListView(DebiteurenController dctrl){
		this.dctrl = dctrl;
		this.debiteuren = this.ctrl.cmdGetDebiteuren();
		createHeader();
		createTableView();
		table.setItems(debiteuren)
	}
	private void createTableView() {
		table = new TableView();
		TableColumn<Debiteuren, CheckBox> checkbox = new TableColumn(" ");
		checkbox.setCellValueFactory(param -> {
			CheckBox checkbox = new CheckBox();
			Bindings.bindBidirectional(checkbox.selectedProperty(), param.getValue().activePropery());
			return new SimpleObjectProperty<>(checkBox);
		});
		TableColumn<Debiteur, String> aanhef = new TableColumn("Aanhef");
		aanhef.setCellValueFactory(new PropertyValueFactory<>("aanhef"));
		TableColumn<Debiteur, String> voornaam = new TableColumn("Voornaam");
		voornaam.setCellValueFactory(new PropertyValueFactory<>("voornaam"));
		TableColumn<Debiteur, String> tussenvoegsel = new TableColumn("Tussenvoegsel");
		tussenvoegsel.setCellValueFactory(new PropertyValueFactory<>("tussenvoegsel"));
		TableColumn<Debiteur, String> naam = new TableColumn("Naam");
		naam.setCellValueFactory(new PropertyValueFactory<>("naam"));
		TableColumn<Debiteur, String> adres = new TableColumn("Adres");
		adres.setCellValueFactory(new PropertyValueFactory<>("adres"));
		TableColumn<Debiteur, String> woonplaats = new TableColumn("Woonplaats");
		woonplaats.setCellValueFactory(new PropertyValueFactory<>("woonplaats"));
		TableColumn<Debiteur, String> postcode = new TableColumn("Postcode");
		postcode.setCellValueFactory(new PropertyValueFactory<>("Postcode"));
		TableColumn<Debiteur, Integer> telefoon = new TableColumn("Telefoon");
		telefoon.setCellValueFactory(new PropertyValueFactory<>("telefoon"));
		TableColumn<Debiteur, String> land = new TableColumn("Land");
		land.setCellValueFactory(new PropertyValueFactory<>("land"));
		addClicklistener();
		table.getColumns().addAll(checkbox, aanhef, voornaam, tussenvoegsel, naam, adres, woonplaats, postcode, telefoon, land);
		setCenter(table);
	}
	public void createHeader() {
		createTitle();
		addDebiteurButton();
		removeDebiteurButton();
		setTop(topContainer);
	}
	public void addDebiteurButton(){
		Button button = new Button("Nieuwe Klant");
		button.setOnAction(e -> this.dctrl.setView(new DebiteurenAddView(this.dctrl)).show());
        topContainer.getChildren().add(button);
	}
    private void removeDebiteurButton() {
        Button button = new Button("Klant verwijderen");
        button.setOnAction(event -> dctrl.cmdDeleteDebiteur(debiteuren));
        topContainer.getChildren().add(button);
    }
    private void createTitle() {
        Text title = new Text("Klanten");
        title.setFont(Font.font(22));
        topContainer.getChildren().add(title);
    }
    private void addClicklistener() {
        table.setRowFactory( tv -> {
            TableRow<Debiteur> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Debiteur rowData = row.getItem();
                    dctrl.setView(new DebiteurenAddView(dctrl, rowData)).show();
                }
            });
            return row ;
        });
    }
    public void show() {
        this.stage.setScene(new Scene(this, 800, 600));
        this.stage.show();
    }

}
