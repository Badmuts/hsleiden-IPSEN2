package Panthera.Controllers;

import Panthera.Models.Handleiding;
import Panthera.Views.HandleidingView;
import Panthera.Views.ProductenListView;

/**
 * Created by Thijs on 9/24/2015.
 */
public class HandleidingController extends Controller {

    private ProductenListView view;

    public HandleidingController(Handleiding handleiding) {
        view = new HandleidingView(this);
    }

//    // TODO
            public void cmdSetHandleiding(Handleiding handleiding) {
            HandleidingView hView = new HandleidingView(handleiding);

//    }
    // setView?

    // Controller heeft een show method die de view laat zien
    public void show(){
        this.view = view;
        // Die show method moeten factuur, product etc. aanroepen als er op de handleiding knop word gedrukt

     // if statement
        getHandleiding();

    }
}
