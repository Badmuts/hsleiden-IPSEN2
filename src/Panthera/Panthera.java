package Panthera;

import Panthera.Controllers.MainController;
import Panthera.Services.QueryUpdater;
import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Panthera extends Application {
    private static Panthera uniqueInstance;
    private Stage stage;

    /**
     * JavaFX Singleton constructor.
     *
     * @author Daan Rosbergen
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
     * This method bootstraps the JavaFX application.
     *
     * @author Daan Rosbergen
     * @param primaryStage JavaFX stage.
     * @throws Exception
     */
    @Override public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        this.stage.setTitle("Lions");
        this.stage.setMinWidth(1024);
        this.stage.setMinHeight(768);
        loadFont();
        new MainController().show();
        new QueryUpdater().update();
    }

    /**
     * Loads external fonts and makes them available in the application.
     *
     * @author Daan Rosbergen
     */
    private void loadFont() {
        Font.loadFont(getClass().getResourceAsStream("Panthera/Resources/avenir-next.ttc"), 12);
    }

    /**
     * Default main method which delegates to its super class.
     *
     * @author Daan Rosbergen
     * @param args Some commandline parameters.
     */
    public static void main(String args[]) {
        launch(args);
    }

    /**
     * Returns the instance of this (Panthera) object (because it is a Singleton).
     *
     * @author Daan Rosbergen
     * @return Panthera.Panthera Singleton Panthera.Panthera object.
     */
    public static Panthera getInstance() {
        return uniqueInstance;
    }

    /**
     * Returns the JavaFX stage.
     *
     * @author Daan Rosbergen
     * @return Stage The actual stage used.
     */
    public Stage getStage() {
        return stage;
    }
}
