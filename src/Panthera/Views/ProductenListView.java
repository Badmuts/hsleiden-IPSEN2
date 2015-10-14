package Panthera.Views;

import Panthera.Controllers.ProductenController;
import Panthera.Models.Product;
import Panthera.Panthera;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ProductenListView extends BorderPane implements Viewable {

    private ProductenController productenController;
    private Stage stage = Panthera.getInstance().getStage();
    private TableView table;
    private ObservableList<Product> products;
    private HBox topContainer = new HBox(10);

    public ProductenListView(ProductenController productenController) {
        this.productenController = productenController;
        createHeader();
        createTableView();

        // Threads are really easy :O
        new Thread(() -> {
            this.products = this.productenController.cmdGetProducten();
            table.setItems(products);
        }).start();

        setPadding(new Insets(22));
        topContainer.setPadding(new Insets(0, 0, 10, 0));
        topContainer.setAlignment(Pos.CENTER_RIGHT);
    }

    /**
     * Creates header with title and buttons.
     *
     * TODO: Extract to Header class for reuse.
     *
     * @author Daan Rosbergen
     */
    private void createHeader() {
        createTitle();
        createAddProductButton();
        createRemoveProductButton();
        setTop(topContainer);
    }

    private void createRemoveProductButton() {
        Button button = new Button("Wijnen verwijderen");
        button.setOnAction(event -> productenController.cmdDeleteProduct(products));
        button.getStyleClass().addAll("btn", "btn-danger");
        topContainer.getChildren().add(button);
    }

    /**
     * Creates TableView.
     *
     * TODO: Refactor this
     *
     * @autor Daan Rosbergen
     */
    private void createTableView() {
        table = new TableView();
        TableColumn<Product, CheckBox> checkbox = new TableColumn(" ");
        checkbox.setCellValueFactory(param -> {
            CheckBox checkBox = new CheckBox();
            Bindings.bindBidirectional(checkBox.selectedProperty(), param.getValue().activeProperty());
            return new SimpleObjectProperty<>(checkBox);
        });
        TableColumn<Product, Integer> productnummer = new TableColumn("Productnummer");
        productnummer.setCellValueFactory(new PropertyValueFactory<>("productnummer"));
        productnummer.prefWidthProperty().bind(table.widthProperty().divide(8));
        TableColumn<Product, String> naam = new TableColumn("Naam");
        naam.setCellValueFactory(new PropertyValueFactory<>("naam"));
        naam.prefWidthProperty().bind(table.widthProperty().divide(6));
        TableColumn<Product, Integer> jaar = new TableColumn("Jaar");
        jaar.setCellValueFactory(new PropertyValueFactory<>("jaar"));
        jaar.prefWidthProperty().bind(table.widthProperty().divide(8));
        TableColumn<Product, Double> prijs = new TableColumn("Prijs");
        prijs.setCellValueFactory(new PropertyValueFactory<>("prijs"));
        prijs.prefWidthProperty().bind(table.widthProperty().divide(8));
        TableColumn<Product, String> type = new TableColumn("Type");
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        type.prefWidthProperty().bind(table.widthProperty().divide(6));
        TableColumn<Product, String> land = new TableColumn("Land");
        land.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getLand().getNaam()));
        land.prefWidthProperty().bind(table.widthProperty().divide(6));
        addClicklistener();
        table.getColumns().addAll(checkbox, productnummer, naam, jaar, prijs, type, land);
        setCenter(table);
    }

    private void addClicklistener() {
        table.setRowFactory( tv -> {
            TableRow<Product> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Product rowData = row.getItem();
                    productenController.getMainController().setSubview(new ProductenAddView(productenController, rowData));
                }
            });
            return row ;
        });
    }

    /**
     * Creates title
     *
     * TODO: Extract to Header class for reuse.
     *
     * @author Daan Rosbergen
     */
    private void createTitle() {
        Text title = new Text("Wijnen");
        title.setFont(Font.font(28));
        title.getStyleClass().addAll("h1");
        topContainer.getChildren().add(title);
    }

    /**
     * Creates 'Product toevoegen' button.
     *
     * TODO: Extract to Header class for reuse.
     *
     * @author Daan Rosbergen
     */
    private void createAddProductButton() {
        Button button = new Button("Nieuwe wijn");
        button.setOnAction(e -> this.productenController.getMainController().setSubview(new ProductenAddView(this.productenController)));
        button.getStyleClass().addAll("btn", "btn-primary");
        topContainer.getChildren().add(button);
    }

    /**
     * Shows the view.
     *
     * @author Daan Rosbergen
     */
    public void show() {
        this.stage.setScene(new Scene(this, 800, 600));
        this.stage.show();
    }
}
