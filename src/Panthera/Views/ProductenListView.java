package Panthera.Views;

import Panthera.Controllers.ProductenController;
import Panthera.Panthera;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ProductenListView extends BorderPane implements Viewable {

    private ProductenController productenController;
    private Stage stage = Panthera.getInstance().getStage();

    public ProductenListView(ProductenController productenController) {
        this.productenController = productenController;
        Text title = new Text("Producten");
        title.setFont(Font.font(22));
        setTop(title);
    }

    /**
     * Shows the view.
     */
    public void show() {
        this.stage.setScene(new Scene(this));
        this.stage.show();
    }
}
