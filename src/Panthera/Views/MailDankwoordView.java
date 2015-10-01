package Panthera.Views;

import Panthera.Controllers.Controller;
import Panthera.Controllers.MailController;
import Panthera.DAO.MailTemplatesDAO;
import Panthera.Models.MailTemplate;
import Panthera.Panthera;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.List;

/**
 * View used for sending a thank you message to a Debiteur.
 *
 * @author Daan Rosbergen
 */
public class MailDankwoordView extends GridPane implements Viewable {
    private final MailController mailController;
    private Stage stage = Panthera.getInstance().getStage();
    private int currentRow = 0;
    private TextArea bericht;

    public MailDankwoordView(MailController mailController) {
        this.mailController = mailController;
        createForm();
    }

    /**
     * This methods creates the views form.
     *
     * @author Daan Rosbergen
     */
    private void createForm() {
        try {
            createChoiceBox("Template", new MailTemplatesDAO().all(),
                (observable, oldValue, newValue) -> updateBericht(observable, oldValue, newValue));
            createTextAreaField("Bericht");
            createButton("Ontvangers selecteren", mailController.cmdSelectRecipients());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method creates a Label and a ChoiceBox.
     *
     * @author Daan Rosbergen
     * @param name              Label name.
     * @param mails             List with MailTemplate models.
     * @param changeListener    When selection changes this listener will be called.
     */
    private void createChoiceBox(String name, List<MailTemplate> mails,
        ChangeListener<MailTemplate> changeListener) {
        Label label = new Label(name);
        ChoiceBox<MailTemplate> box = new ChoiceBox<>(FXCollections.observableArrayList(mails));
        box.getSelectionModel().selectedItemProperty().addListener(changeListener);
        addToForm(label, box);
    }

    /**
     * This method updates the text in the 'Bericht' TextArea.
     *
     * @author Daan Rosbergen
     * @param observable    MailTemplate observer.
     * @param oldValue      Old MailTemplate model.
     * @param newValue      New MailTemplate model.
     */
    private void updateBericht(ObservableValue<? extends MailTemplate> observable,
        MailTemplate oldValue, MailTemplate newValue) {
        this.bericht.setText(newValue.getContent());
    }

    /**
     * Creates a Label and TextArea and adds it to the view.
     *
     * @author Daan Rosbergen
     * @param name  Label name.
     */
    private void createTextAreaField(String name) {
        Label label = new Label(name);
        this.bericht = new TextArea();
        addToForm(label, this.bericht);
    }

    /**
     * Creates a Button and binds it to a method in the Controller.
     *
     * @param name          Name of the button.
     * @param controller    Controller action.
     */
    private void createButton(String name, Controller controller) {
        Button button = new Button(name);
        button.setOnAction(event -> controller.show());
        addToForm(button);
    }

    /**
     * Adds 2 nodes to the view.
     *
     * @param label
     * @param textArea
     */
    private void addToForm(Node label, Node textArea) {
        add(label, 0, currentRow);
        add(textArea, 1, currentRow);
        currentRow++;
    }

    /**
     * Adds a single Node to the right of the view.
     *
     * @param node
     */
    private void addToForm(Node node) {
        add(node, 1, currentRow);
        currentRow++;
    }

    /**
     * Viewable method shows this view.
     */
    @Override public void show() {
        this.stage.setScene(new Scene(this));
        this.stage.show();
    }
}
