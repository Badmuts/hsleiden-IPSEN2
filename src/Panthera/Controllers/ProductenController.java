package Panthera.Controllers;

import Panthera.Views.ProductenListView;

public class ProductenController extends Controller {

    public ProductenController() {
        this.view = new ProductenListView(this);
    }

}
