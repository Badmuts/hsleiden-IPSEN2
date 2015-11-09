package Panthera.Views;

import Panthera.Controllers.ProductenController;
import Panthera.DAO.LandDAO;
import Panthera.Models.Land;
import Panthera.Models.Product;
import Panthera.Panthera;
import javafx.beans.binding.Bindings;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.NumberStringConverter;

import java.util.ArrayList;

/**
 * View used to add or edit a product.
 *
 * @author Daan Rosbergen
 */
public class ProductenAddView extends GridPane implements Viewable {

    private ProductenController productenController;
    private Stage stage = Panthera.getInstance().getStage();
    private int currentRow = 0;
    private Product product;

    /**
     * Creates a view to add a product.
     *
     * @param productenController
     */
    public ProductenAddView(ProductenController productenController) {
        this.productenController = productenController;
        this.product = new Product();
        setPadding(new Insets(10));
        createTitle();
        createForm();
        createSaveButton();
    }

    /**
     * Creates a view to edit a product.
     *
     * @param productenController
     * @param product
     */
    public ProductenAddView(ProductenController productenController, Product product) {
        this.productenController = productenController;
        this.product = product;
        setPadding(new Insets(10));
        createTitle();
        createForm();
        createSaveButton();
    }

    /**
     * Creates save button.
     */
    private void createSaveButton() {
        Button button = new Button("Opslaan");
        button.setOnAction(event -> saveProduct());
        button.getStyleClass().addAll("btn", "btn-success");
        add(button, 0, currentRow);
        currentRow++;
    }

    /**
     * Save product to DB.
     */
    private void saveProduct() {
        productenController.cmdSaveProduct(product);
    }

    /**
     * Create view title.
     */
    private void createTitle() {
        Text title = new Text("Wijn toevoegen");
        title.getStyleClass().add("h1");
        title.setFont(Font.font(20));
        add(title, 0, currentRow);
        currentRow++;
    }

    @Override public void show() {
        this.stage.setScene(new Scene(this));
        this.stage.show();
    }

    /**
     * Create form to edit or add a product.
     */
    private void createForm() {
        createField("Wijnnummer", product.productnummerProperty(), new NumberStringConverter());
        createField("Naam", product.naamProperty());
        createField("Jaar", product.jaarProperty(), new NumberStringConverter());
        createField("Prijs", product.prijsProperty(), new DoubleStringConverter());
        createField("Type", product.typeProperty());
        createComboBox("Land");
    }

    /**
     * Create a combobox to select a country.
     *
     * @param name
     */
    private void createComboBox(String name) {
        try {
            Label label = new Label(name);
            ArrayList<Land> landen = new LandDAO().all();
            ChoiceBox<Land> choiceBox = new ChoiceBox<>(FXCollections.observableArrayList(landen));
            choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                product.setLand(newValue);
            });
            // TODO: Better fix this.
            for (Land land: landen) {
                  choiceBox.getSelectionModel().select(land);
                }

            add(label, 0, currentRow);
            add(choiceBox, 1, currentRow);
            currentRow++;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create a field (label and textfield) with converter.
     *
     * @param name
     * @param property
     * @param converter
     */
    private void createField(String name, Property property, StringConverter converter) {
        Label label = new Label(name);
        TextField textField = new TextField(name);
        Bindings.bindBidirectional(textField.textProperty(), property, converter);

        add(label, 0, currentRow);
        add(textField, 1, currentRow);
        currentRow++;
    }

    /**
     * Create a field (label and textfield)
     *
     * @param name
     * @param property
     */
    private void createField(String name, Property property) {
        Label label = new Label(name);
        TextField textField = new TextField(name);
        Bindings.bindBidirectional(textField.textProperty(), property);

        add(label, 0, currentRow);
        add(textField, 1, currentRow);
        currentRow++;
    }
}
