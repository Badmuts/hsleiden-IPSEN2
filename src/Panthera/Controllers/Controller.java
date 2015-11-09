package Panthera.Controllers;

import Panthera.Views.Viewable;

/**
 * Main controller which implements a simple show method. Every Controller
 * should extend from this class.
 *
 * @author Daan Rosbergen
 */
public class Controller {

    protected Viewable view;

    /**
     * Calls the show method on a Viewable.
     */
    public void show() {
        this.view.show();
    }

    /**
     * Set view and return itself for methodchaning.
     *
     * @author  Daan Rosbergen
     * @param   view        new view.
     * @return  Controller  return self.
     */
    public Controller setView(Viewable view) {
        this.view = view;
        return this;
    }

    /**
     * Returns the current view.
     *
     * @return Viewable Viewable object (most likely a JavaFX node).
     */
    public Viewable getView() {
        return this.view;
    }

}
