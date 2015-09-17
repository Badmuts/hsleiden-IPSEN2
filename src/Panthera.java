import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Panthera extends Application {

    private Stage stage;

    public Panthera getInstance() {
        return this;
    }

    @Override public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        this.stage.setScene(new Scene(new TextField("Panthera")));
        System.out.println("Panthera2");
    }
}
