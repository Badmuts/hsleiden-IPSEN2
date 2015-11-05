package Panthera.Views.Alerts;

import javafx.scene.control.Alert;

/**
 * Class gebruikt om database foutmeldingen te tonen. Er wordt een leesbaar bericht getoont voor
 * de gebruiker en een StackTrace van de Exception voor de programmeur.
 *
 * @author Daan Rosbergen
 */
public class DatabaseErrorAlert extends Alert {

    /**
     * Maakt een Alert en toont een leesbare foutmelding voor de gebruiker een een StackTrace voor
     * de programmeur.
     * 
     * @param errorMessage  Leesbare foutmelding voor de gebruiker.
     * @param e             Exception voor de programmeur.
     */
    public DatabaseErrorAlert(String errorMessage, Exception e) {
        super(AlertType.ERROR);
        setHeaderText("Database fout!");
        setContentText(errorMessage + "\n\n  Exception: " + e.toString());
    }

}
