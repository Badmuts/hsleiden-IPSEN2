package Panthera.Controllers;

import Panthera.DAO.ProductDAO;
import Panthera.Models.Product;
import Panthera.Services.Validators.ProductValidator;
import Panthera.Views.ProductenListView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.util.ArrayList;

public class ProductenController extends Controller {

    private final MainController mainController;
    private ProductDAO dao;
    private String[] requiredFields = {"Productnummer", "Naam", "Jaar", "Prijs", "Type", "Land"};

    public ProductenController(MainController mainController) throws Exception {
        dao = new ProductDAO();
        this.mainController = mainController;
    }

    /**
     * Get ObservableList with products for the view (max 25).
     *
     * @author Daan Rosbergen
     * @return ObservableList<Product>
     *     Collection of Product models.
     */
    public ObservableList<Product> cmdGetProducten() {
        ArrayList<Product> products = new ArrayList<>();
        try {
            products.addAll(dao.all());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return FXCollections.observableArrayList(products);
    }

    public void cmdSaveProduct(Product product) {
        try {
            new ProductValidator(product).validate();
            dao.save(product);
            mainController.setSubview(new ProductenListView(this));
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            e.printStackTrace();
        }
    }

    public void cmdDeleteProduct(ObservableList<Product> products) {
        try {
            for(Product product: products) {
                if (product.isActive()) {
                    dao.delete(product);
                    Platform.runLater(() -> products.remove(product));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void show() {
        this.mainController.setSubview(new ProductenListView(this));
    }

    public MainController getMainController() {
        return mainController;
    }
}
