package Panthera.Views;
import Panthera.Controllers.FacturenController;
import Panthera.DAO.FactuurDAO;
import Panthera.Models.Factuur;
import Panthera.Panthera;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.sql.SQLException;
import java.util.Date;


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
        this.facturen = facturenController.cmdGetFacturen();

        createTitle();
        createTableView();
        table.setItems(facturen);
    }

    private void createTitle() {
        Text title = new Text("Facturen");
        title.setFont(Font.font(22));
        setTop(title);
    }


    private void createTableView() {
        table = new TableView<>();
        TableColumn factuurnummer = new TableColumn("Factuurnummer");
        factuurnummer.setCellValueFactory(new PropertyValueFactory<Factuur, Integer>("factuurnummer"));
        TableColumn FirstNameCol = new TableColumn("Voornaam");
        TableColumn SuffixCol = new TableColumn("Tussenvoegsel");
        TableColumn LastNameCol = new TableColumn("Achternaam");
        TableColumn factuurdatum = new TableColumn("Factuurdatum");
        factuurdatum.setCellValueFactory(new PropertyValueFactory<Factuur, Date>("factuurdatum"));
        TableColumn factuurexpdate = new TableColumn("Vervaldatum");
        factuurexpdate.setCellValueFactory(new PropertyValueFactory<Factuur, Date>("vervaldatum"));
        TableColumn status = new TableColumn("Status");
        status.setCellValueFactory(new PropertyValueFactory<Factuur, String>("status"));

        table.getColumns().addAll(factuurnummer, FirstNameCol, SuffixCol, LastNameCol, factuurdatum, factuurexpdate, status);
        setCenter(table);

    }


    @Override
    public void show() {

        this.stage.setScene(new Scene(this, 800, 600));
        this.stage.show();
    }
}
