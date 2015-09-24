package Panthera.Controllers;

import Panthera.Views.Viewable;

/**
 * Main controller which implements a simple show method. Every Controller
 * should extend from this class.
 */
public class Controller {

    protected Viewable view;

    /**
     * Calls the show method on a Viewable.
     */
    public void show() {
        this.view.show();
    }



    public Controller setView(Viewable view) {
        this.view = view;
        return this;
    }

}
