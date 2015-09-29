package Panthera.Views;

import Panthera.Controllers.HandleidingController;
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

    // TODO: handleiding model toevoegen



    // TODO: handleiding text tonen
    public void showHandleiding() {
        //TODO: print handleiding uit textfile
    }

    public HandleidingView(HandleidingController handleidingController) {
        this.handleidingController = handleidingController;
        Text title = new Text("Handleiding");
        setTop(title);
    }

    @Override
    public void show() {
        this.stage.setScene(new Scene(this));
        this.stage.show();
    }
}
