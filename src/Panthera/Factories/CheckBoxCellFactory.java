package Panthera.Factories;

import Panthera.Models.Factuur;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.CheckBoxTableCell;

import javax.security.auth.callback.Callback;

/**
 * Created by Brandon on 27-Sep-15.
 */
public class CheckBoxCellFactory implements Callback {

    public TableCell call(Object param) {
        CheckBoxTableCell<Factuur,Boolean> checkboxCell = new CheckBoxTableCell<>();
        return checkboxCell;
    }
}
