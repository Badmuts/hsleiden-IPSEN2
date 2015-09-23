package Panthera.Controllers;

import Panthera.DAO.ProductDAO;
import Panthera.Models.Product;
import Panthera.Views.ProductenListView;

import java.util.ArrayList;
import java.util.Collection;

public class ProductenController extends Controller {

    private ProductDAO dao;

    public ProductenController() throws Exception {
        this.view = new ProductenListView(this);
        this.dao = new ProductDAO();
    }

    public Collection<Product> cmdGetProducten() {
        ArrayList<Product> products = new ArrayList<>();
        try {
            products.addAll(dao.all());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
}
