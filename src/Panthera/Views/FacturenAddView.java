package Panthera.Views;

import Panthera.Controllers.FacturenController;
import Panthera.Models.Factuur;
import Panthera.Panthera;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

/**
 * Created by Brandon on 24-Sep-15.
 */
public class FacturenAddView extends BorderPane implements Viewable {

    private FacturenController facturenController;
    private Stage stage = Panthera.getInstance().getStage();
    private int currentRow = 0;
    private Factuur factuur;

    public FacturenAddView(FacturenController facturenController) {
        this.facturenController = facturenController;
        this.factuur = new Factuur();
        createTitle();
        createForm();
        createSaveButton();
    }

    public FacturenAddView(FacturenController facturenController, Factuur factuur) {
        this.facturenController = facturenController;
        this.factuur = factuur;
        createTitle();
        createForm();
        createSaveButton();
    }

    private void createSaveButton() {
        Button button = new Button("Opslaan");
        button.setOnAction(event -> saveFactuur());
        add(button, 0, currentRow);
        currentRow++;
    }

    private void saveFactuur() {
        facturenController.cmdSaveFactuur(factuur);
    }

    private void createTitle() {
        Text title = new Text("Factuur toevoegen");
        title.setFont(Font.font(20));
        add(title, 0, currentRow);
        currentRow++;
    }

    @Override
    public void show() {
        this.stage.setScene(new Scene(this));
        this.stage.show();
    }

    private void createForm() {
        createField("Factuurnummer"), factuur.factuurnummerProperty(), new NumberStringConverter());
    }
}
