package Panthera.Views.Alerts;

import javafx.scene.control.Alert;

public class DatabaseErrorAlert extends Alert {

    public DatabaseErrorAlert(String errorMessage, Exception e) {
        super(AlertType.ERROR);
        setHeaderText("Database fout!");
        setContentText(errorMessage + "\n\n  Exception: " + e.toString());
    }

}
