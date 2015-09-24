package Panthera.Views;

import Panthera.Panthera;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ProductenAddView extends BorderPane implements Viewable {

    private Stage stage = Panthera.getInstance().getStage();

    public ProductenAddView() {
        Text title = new Text("Product toevoegen");
        setTop(title);
    }

    @Override public void show() {
        this.stage.setScene(new Scene(this));
        this.stage.show();
    }

}
