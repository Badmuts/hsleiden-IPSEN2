package Panthera.Views;

import Panthera.Panthera;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ProductenAddView extends BorderPane implements Viewable {

    private Stage stage = Panthera.getInstance().getStage();

    public ProductenAddView() {

    }

    @Override public void show() {
        this.stage.setScene(new Scene(this));
        this.stage.show();
    }

}
