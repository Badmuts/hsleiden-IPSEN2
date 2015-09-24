package Panthera.Controllers;

import Panthera.DAO.ProductDAO;
import Panthera.Models.Product;
import Panthera.Views.ProductenListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class ProductenController extends Controller {

    private ProductDAO dao;

    public ProductenController() throws Exception {
        dao = new ProductDAO();
        view = new ProductenListView(this);
    }

    /**
     * Get ObservableList with products for the view (max 25).
     *
     * @author Daan Rosbergen
     * @return ObservableList<Product> Collection of Product models.
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
}
