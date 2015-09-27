package Panthera.Views;

import Panthera.Panthera;
import Panthera.Controllers.BestellijstenController;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class BestellijstenAddView extends BorderPane implements Viewable {
	
	private Stage stage;
	private BestellijstenController bestellijstenController;
	
	public BestellijstenAddView(BestellijstenController bestellijstenController) {
		this.stage = Panthera.getInstance().getStage();
		this.bestellijstenController = bestellijstenController;
	}
	@Override
	public void show() {
		this.stage.setScene(new Scene(this, 800, 600));
		this.stage.show();
	}

}
