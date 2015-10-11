package Panthera.Views;

import Panthera.Controllers.MailController;
import Panthera.DAO.MailTemplatesDAO;
import Panthera.Models.Email;
import Panthera.Models.MailTemplate;
import Panthera.Panthera;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
public class MailUitnodigingView extends GridPane implements Viewable {
    private final MailController mailController;
    private Stage stage = Panthera.getInstance().getStage();
    private int currentRow = 0;
    private TextArea bericht;
    private Email email;
    private TextField onderwerp;

    /**
     *
     * @param mailController
     */
    public MailUitnodigingView(MailController mailController) {
        this.mailController = mailController;
        this.email = new Email();
        createForm();
        setPadding();
    }

    /**
     * This methods creates the views form.
     *
     * @author Daan Rosbergen
     */
    private void createForm() {
        try {
            createField("Onderwerp");
            createChoiceBox("Template", new MailTemplatesDAO().all(),
                    (observable, oldValue, newValue) -> updateBericht(observable, oldValue, newValue));
            createTextAreaField("Bericht");
            createButton("Ontvangers selecteren", event -> mailController.cmdShowSelectRecipients(onderwerp.getText(), bericht.getText()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createField(String name) {
        Label label = new Label(name);
        onderwerp = new TextField();
        onderwerp.textProperty().addListener((observable, oldValue, newValue) -> {
            this.email.setSubject(newValue);
        });
        addToForm(label, onderwerp);
    }

    /**
     * Sets padding to view and its rows.
     *
     * @author Daan Rosbergen
     */
    private void setPadding() {
        setHgap(5);
        setVgap(5);
        setPadding(new Insets(10));
    }

    /**
     * This method creates a Label and a ChoiceBox and adds a
     * ChangeListener to itself.
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
        // Bind changelistener
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
        this.email.setText(this.bericht.getText());
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
     * @param eventhandler  EventHandler for button action.
     */
    private void createButton(String name, EventHandler eventhandler) {
        Button button = new Button(name);
        button.setOnAction(eventhandler);
        addToForm(button);
    }

    /**
     * Adds 2 nodes to the view in the same row.
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