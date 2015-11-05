package Panthera.Controllers;

import Panthera.DAO.ProductDAO;
import Panthera.Models.Product;
import Panthera.Views.Alerts.DatabaseErrorAlert;
import Panthera.Views.Alerts.WijnVerwijderenAlert;
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
    private ObservableList<Product> products;
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
            new DatabaseErrorAlert("Wijnen kunnen niet worden opgehaald, probeer het opnieuw.", e).show();
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

            new DatabaseErrorAlert("Wijn kan niet worden opgeslagen, probeer het opnieuw.", e).show();

            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

            e.printStackTrace();
        }
    }

    public void cmdDeleteProduct() {
        try {
            for(Product product: products) {
                if (product.isActive()) {
                    dao.delete(product);
                    Platform.runLater(() -> products.remove(product));
                }
            }
        } catch (Exception e) {
            new DatabaseErrorAlert("Wijn kan niet worden verwijderd, probeer het opnieuw.", e).show();
            e.printStackTrace();
        }
    }

    public void cmdShowVerwijderenAlert() {
        new WijnVerwijderenAlert(this).open();
    }

    @Override
    public void show() {
        this.mainController.setSubview(new ProductenListView(this));
    }

    public MainController getMainController() {
        return mainController;
    }

    public void setProducts(ObservableList<Product> products) {
        this.products = products;
    }
}
