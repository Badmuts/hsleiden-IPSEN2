package Panthera.Controllers;

import Panthera.Views.MainMenuView;

/**
 * Created by Daan on 22-Sep-15.
 */
public class MainMenuController extends Controller {

    public MainMenuController() {
        this.view = new MainMenuView(this);
    }

}

