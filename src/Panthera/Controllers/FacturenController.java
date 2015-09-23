package Panthera.Controllers;

import Panthera.Views.FacturenListView;

/**
 * Created by Brandon on 23-Sep-15.
 */
public class FacturenController extends Controller {

    public FacturenController() {
        this.view = new FacturenListView(this);
    }
}
