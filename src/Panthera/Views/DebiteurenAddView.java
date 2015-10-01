package Panthera.Views;

import Panthera.Panthera;
import javafx.stage.Stage;

/**
 * 
 * @author Victor
 * 
 */
public class DebiteurenAddView extends GridPane implements Viewable {
	
	private Stage stage = Panthera.getInstance().getStage();
	private DebiteurenController dctrl;
	private Debiteur debiteur;
	private int row;

	
	public DebiteurenAddView(DebiteurenController dctrl){
		this.dctrl = dctrl;
		this.debiteur = new Debiteur();
		Text title = new Text("Toevoegen Klant");
		add(title,0,row);
		row++;
	}
	
	public void addButton(){
		Button button = new Button("Toevoegen");
		button.setOnAction(event -> addDebiteur());
		add(button, 0,row);
		row++;
	}
	public void addDebiteur(){
		dctrl.cmdAddDebiteur(debiteur);
	}
	public void createForm(){
		createField("Aanhef", debiteur.getAanhef());
		createField("Voornaam", debiteur.getVoornaam());
		createField("Tussenvoegsel", debiteur.getTussenvoegsel());
		createField("Naam", debiteur.getNaam());
		createField("Adres", debiteur.getAdres());
		createField("Woonplaats", debiteur.getWoonplaats());
		createField("Postcode", debiteur.getPostcode);
		createField("Email", debiteur.getEmail());
		createField("Telefoon", debiteur.getTelefoon());
		createComboBox("Land");
	}
	private void createField(String name, Property property) {
		Label label = new Label(name);
		TextField textfield = new TextField(name);
		Bindings.bindBidirectional(textField.textProperty(), property);
		add(label, 0,row);
		add(textField, 1 row);
		row++;
	}
	private void createComboBox(String name) {
        try {
            Label label = new Label(name);
            ChoiceBox<Land> choiceBox = new ChoiceBox<>(FXCollections.observableArrayList(new LandDAO().all()));
            choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                product.setLand(newValue);
            });
            choiceBox.getSelectionModel().select(0);
            add(label, 0, row);
            add(choiceBox, 1, row);
            row++;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
}
