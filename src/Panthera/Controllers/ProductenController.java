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

/**
 * Controller used to create, edit and delete products.
 *
 * @author Daan Rosbergen
 */
public class ProductenController extends Controller {

    private final MainController mainController;
    private ProductDAO dao;
    private ObservableList<Product> products;

    /**
     * Creates a ProducDAO to retrieve product records in the DB as Models and saves the
     * MainController.
     *
     * @author Daan Rosbergen
     * @param mainController    MainController     The MainController.
     * @throws Exception        Exception          Database Error.
     */
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

    /**
     * Save a Product model to the DB via the ProductDAO.
     *
     * @author Daan Rosbergen
     * @param product Product   A product model.
     */
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

    /**
     * Removes selected products in the list (products which are marked active) from the DB.
     *
     * @author Daan Rosbergen
     */
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

    /**
     * Show popup message to confirm the removal of selected products.
     *
     * @author Daan Rosbergen
     */
    public void cmdShowVerwijderenAlert() {
        new WijnVerwijderenAlert(this).open();
    }

    /**
     * Shows the default view: ProductenListView.
     *
     * @author Daan Rosbergen
     */
    @Override
    public void show() {
        this.mainController.setSubview(new ProductenListView(this));
    }

    /**
     * Retrieve the MainController.
     *
     * @author Daan Rosbergen
     * @return MainController   The MainController.
     */
    public MainController getMainController() {
        return mainController;
    }

    /**
     * Set a ObservableList to the controller, used to check if products are marked as active.
     *
     * @author Daan Rosbergen
     * @param products ObservableList<Product>  List of observable product.
     */
    public void setProducts(ObservableList<Product> products) {
        this.products = products;
    }
}
