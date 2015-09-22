import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Panthera extends Application {

    private static Panthera uniqueInstance;
    private Stage stage;

    /**
     * JavaFX Singleton constructor.
     */
    public Panthera() {
        super();
        synchronized(Panthera.class){
            if(uniqueInstance != null) throw new UnsupportedOperationException(
                    getClass()+" is singleton but constructor called more than once");
            uniqueInstance = this;
        }
    }

    /**
     * Returns the instance of this object (because it is a Singleton)
     *
     * @return Panthera Singleton Panthera object.
     */
    public Panthera getInstance() {
        return uniqueInstance;
    }

    /**
     * This method bootstraps the JavaFX application.
     *
     * @param primaryStage JavaFX stage.
     * @throws Exception
     */
    @Override public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        this.stage.setTitle("Panthera");
        this.stage.setScene(new Scene(new TextField("Panthera")));
        this.stage.show();
    }

    /**
     * Default main method which delegates to its super class.
     *
     * @param args Some commandline parameters.
     */
    public static void main(String args[]) {
        launch(args);
    }
}
