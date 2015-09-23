package Panthera;

import Panthera.Controllers.MainMenuController;
import Panthera.DAO.FactuurDAO;
import Panthera.Models.Factuur;
import javafx.application.Application;
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
     * This method bootstraps the JavaFX application.
     *
     * @param primaryStage JavaFX stage.
     * @throws Exception
     */
    @Override public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        this.stage.setTitle("Panthera");
        new MainMenuController().show();
        FactuurDAO factuurDAO = new FactuurDAO();
        Factuur factuur = factuurDAO.getFactuur(1);
        System.out.print(factuur);
    }

    /**
     * Default main method which delegates to its super class.
     *
     * @param args Some commandline parameters.
     */
    public static void main(String args[]) {
        launch(args);
    }

    /**
     * Returns the instance of this (Panthera.Panthera) object (because it is a Singleton).
     *
     * @return Panthera.Panthera Singleton Panthera.Panthera object.
     */
    public static Panthera getInstance() {
        return uniqueInstance;
    }

    /**
     * Returns the JavaFX stage.
     *
     * @return Stage The actual stage used.
     */
    public Stage getStage() {
        return stage;
    }
}
