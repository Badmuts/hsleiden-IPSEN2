package Panthera.Views;

import Panthera.Controllers.ProductenController;
import Panthera.Models.Product;
import Panthera.Panthera;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ProductenListView extends BorderPane implements Viewable {

    private ProductenController productenController;
    private Stage stage = Panthera.getInstance().getStage();
    private TableView<Product> table;
    private ObservableList<Product> products;

    public ProductenListView(ProductenController productenController) {
        this.productenController = productenController;
        this.products = productenController.cmdGetProducten();
        createTitle();
        createTableView();
        table.setItems(products);
    }

    private void createTableView() {
        table = new TableView<>();
        // TODO: Refactor this
        TableColumn productnummer = new TableColumn("Productnummer");
        productnummer.setCellValueFactory(new PropertyValueFactory<Product, Integer>("productnummer"));
        TableColumn naam = new TableColumn("Naam");
        naam.setCellValueFactory(new PropertyValueFactory<Product, String>("naam"));
        TableColumn jaar = new TableColumn("Jaar");
        jaar.setCellValueFactory(new PropertyValueFactory<Product, Integer>("jaar"));
        TableColumn prijs = new TableColumn("Prijs");
        prijs.setCellValueFactory(new PropertyValueFactory<Product, Double>("prijs"));
        TableColumn type = new TableColumn("Type");
        type.setCellValueFactory(new PropertyValueFactory<Product, String>("type"));
        table.getColumns().addAll(productnummer, naam, jaar, prijs, type);
        setCenter(table);
    }

    private void createTitle() {
        Text title = new Text("Producten");
        title.setFont(Font.font(22));
        setTop(title);
    }

    /**
     * Shows the view.
     */
    public void show() {
        this.stage.setScene(new Scene(this, 800, 600));
        this.stage.show();
    }
}
