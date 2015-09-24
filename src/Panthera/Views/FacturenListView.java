package Panthera.Views;
import Panthera.Controllers.FacturenController;
import Panthera.Models.Factuur;
import Panthera.Panthera;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.Text;



/**
 * Created by Brandon on 23-Sep-15.
 */
public class FacturenListView extends BorderPane implements Viewable {

    private FacturenController facturenController;
    private Stage stage = Panthera.getInstance().getStage();
    private TableView<Factuur> table;
    private ObservableList<Factuur> facturen;



    public FacturenListView(FacturenController facturenController)  {

        this.facturenController = facturenController;
        this.facturen = this.facturenController.cmdGetFacturen();
        createTitle();
        createTableView();
        table.setItems(facturen);
    }

    private void createTableView() {
        table = new TableView<>();
        TableColumn factuurnummer = new TableColumn("Factuurnummer");
        factuurnummer.setCellValueFactory(new PropertyValueFactory<Factuur, Integer>("factuurnummer"));
        TableColumn factuurdatum = new TableColumn("Factuurdatum");
        factuurdatum.setCellValueFactory(new PropertyValueFactory<Factuur, String>("factuurdatum"));
        TableColumn factuurexpdate = new TableColumn("Vervaldatum");
        factuurexpdate.setCellValueFactory(new PropertyValueFactory<Factuur, String>("vervaldatum"));
        TableColumn status = new TableColumn("Status");
        status.setCellValueFactory(new PropertyValueFactory<Factuur, String>("status"));
        table.getColumns().addAll(factuurnummer, factuurdatum, factuurexpdate, status);
        setCenter(table);

    }

    private void createTitle() {
        Text title = new Text("Facturen");
        title.setFont(Font.font(22));
        setTop(title);
    }


    @Override
    public void show() {

        this.stage.setScene(new Scene(this, 800, 600));
        this.stage.show();
    }
}
