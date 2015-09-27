package Panthera.Views;

import Panthera.Controllers.ProductenController;
import Panthera.DAO.LandDAO;
import Panthera.DAO.ProductDAO;
import Panthera.Models.Land;
import Panthera.Models.Product;
import Panthera.Panthera;
import javafx.beans.binding.Bindings;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.NumberStringConverter;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ProductenAddView extends GridPane implements Viewable {

    private ProductenController productenController;
    private Stage stage = Panthera.getInstance().getStage();
    private int currentRow = 0;
    private Product product;

    public ProductenAddView(ProductenController productenController) {
        this.productenController = productenController;
        this.product = new Product();
        createTitle();
        createForm();
        createSaveButton();
    }

    public ProductenAddView(ProductenController productenController, Product product) {
        this.productenController = productenController;
        this.product = product;
        createTitle();
        createForm();
        createSaveButton();
    }

    private void createSaveButton() {
        Button button = new Button("Opslaan");
        button.setOnAction(event -> saveProduct());
        add(button, 0, currentRow);
        currentRow++;
    }

    private void saveProduct() {
        productenController.cmdSaveProduct(product);
    }

    private void createTitle() {
        Text title = new Text("Product toevoegen");
        title.setFont(Font.font(20));
        add(title, 0, currentRow);
        currentRow++;
    }

    @Override public void show() {
        this.stage.setScene(new Scene(this));
        this.stage.show();
    }

    private void createForm() {
        createField("Productnummer", product.productnummerProperty(), new NumberStringConverter());
        createField("Naam", product.naamProperty());
        createField("Jaar", product.jaarProperty(), new NumberStringConverter());
        createField("Prijs", product.prijsProperty(), new DoubleStringConverter());
        createField("Type", product.typeProperty());
        createComboBox("Land");
    }

    private void createComboBox(String name) {
        try {
            Label label = new Label(name);
            ChoiceBox<Land> choiceBox = new ChoiceBox<>(FXCollections.observableArrayList(new LandDAO().all()));
            choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                product.setLand(newValue);
            });
            choiceBox.getSelectionModel().select(0);
            add(label, 0, currentRow);
            add(choiceBox, 1, currentRow);
            currentRow++;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createField(String name, Property property, StringConverter converter) {
        Label label = new Label(name);
        TextField textField = new TextField(name);
        Bindings.bindBidirectional(textField.textProperty(), property, converter);

        add(label, 0, currentRow);
        add(textField, 1, currentRow);
        currentRow++;
    }

    private void createField(String name, Property property) {
        Label label = new Label(name);
        TextField textField = new TextField(name);
        Bindings.bindBidirectional(textField.textProperty(), property);

        add(label, 0, currentRow);
        add(textField, 1, currentRow);
        currentRow++;
    }

//    public ArrayList<String> getFormData() {
//        ArrayList<String> formData = new ArrayList<>();
//        for (TextField text: getChildren()) {
//            formData.add(text.toString());
//        }
//        return formData;
//    }
}
