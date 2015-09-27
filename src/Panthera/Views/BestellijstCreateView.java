package Panthera.Views;

import Panthera.Panthera;
import Panthera.Controllers.BestellijstenController;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class BestellijstCreateView extends BorderPane implements Viewable{
	private BestellijstenController bestellijstenController;
	private Stage stage;
	
	public BestellijstCreateView(BestellijstenController bestellijstenController) {
		this.bestellijstenController = bestellijstenController;
		this.stage = Panthera.getInstance().getStage();
	}
	
	@Override
	public void show() {
		this.stage.setScene(new Scene(this, 800, 600));
		this.stage.show();
		
	}

}
