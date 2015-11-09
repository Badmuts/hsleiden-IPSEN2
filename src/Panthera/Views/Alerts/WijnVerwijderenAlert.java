package Panthera.Views.Alerts;

import Panthera.Controllers.ProductenController;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * Alert gebruikt wanneer een gebruiker een wijn wilt verwijderen. Als de gebruiker op 'Ja'
 * klikt wordt de `cmdDeleteProducts()` methode aangeroepen.
 *
 * Wanneer de gebruiker op 'Nee' klikt wordt de Alert gesloten.
 *
 * @author Daan Rosbergen
 */
public class WijnVerwijderenAlert extends Alert {

    private ProductenController productenController;
    private ButtonType yesButton;
    private ButtonType noButton;

    /**
     * Maakt een nieuwe Alert voor het verwijderen van een wijn. Ontvangt een ProductenController om
     * de knoppen in de alert te koppelen aan een actie in de controller.
     *
     * @param productenController
     */
    public WijnVerwijderenAlert(ProductenController productenController) {
        super(AlertType.CONFIRMATION);
        this.productenController = productenController;
        setTitle("Weet u zeker dat u deze wijn wilt verwijderen?");
        setHeaderText("Weet u zeker dat u deze wijn wilt verwijderen?");
        getButtonTypes().clear(); // Remove default buttons
        createYesButton();
        createNoButton();
    }

    /**
     * Opent de alert en koppelt de 'Ja' knop aan de `cmdDeleteProduct()` methode.
     */
    public void open() {
        Optional<ButtonType> result = super.showAndWait();
        if (result.get() == yesButton)
            this.productenController.cmdDeleteProduct();
    }

    /**
     * Maakt een 'Nee' knop en voegt deze toe aan de Alert.
     */
    private void createNoButton() {
        noButton = new ButtonType("Nee");
        getButtonTypes().add(noButton);
    }

    /**
     * Maakt een 'Ja' knop en voegt deze toe aan de Alert.
     */
    private void createYesButton() {
        yesButton = new ButtonType("Ja");
        getButtonTypes().add(yesButton);
    }

}
