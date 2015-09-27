package Panthera.Views;

import Panthera.Panthera;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by Brandon on 24-Sep-15.
 */
public class FacturenAddView extends BorderPane implements Viewable {

    private Stage stage = Panthera.getInstance().getStage();

    public FacturenAddView() {
        Text title = new Text("Factuur toevoegen");
        setTop(title);
    }

    @Override
    public void show() {
        this.stage.setScene(new Scene(this));
        this.stage.show();

    }
}
