package Panthera.Views;

import Panthera.Controllers.HandleidingController;
import Panthera.Models.Handleiding;
import Panthera.Panthera;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by user on 9/24/2015.
 */
public class HandleidingView extends BorderPane implements Viewable {
    private HandleidingController handleidingController;
    private Stage stage = Panthera.getInstance().getStage();

    public HandleidingView(HandleidingController handleidingController, Handleiding handleiding) {
        this.handleidingController = handleidingController;
        Text title = new Text("Handleiding");
        setTop(title);
        getInhoud();
        this.stage.add(inhoud);
    }

    @Override
    public void show() {
        this.stage.setScene(new Scene(this));
        this.stage.show();
    }
}
