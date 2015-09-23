package Panthera.Views;
import Panthera.Controllers.FacturenController;
import Panthera.Panthera;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


/**
 * Created by Brandon on 23-Sep-15.
 */
public class FacturenListView extends BorderPane implements Viewable {

    private FacturenController facturenController;
    private Stage stage = Panthera.getInstance().getStage();

    public FacturenListView(FacturenController facturenController) {

        this.facturenController = facturenController;
        Text title = new Text("Facturen");
        title.setFont(Font.font(22));
        setTop(title);
    }

    @Override
    public void show() {
        this.stage.setScene(new Scene(this));
        this.stage.show();
    }
}
