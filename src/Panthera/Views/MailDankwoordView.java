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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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
    private VBox topContainer = new VBox(10);
    private Email email;
    private TextField onderwerp;

    /**
     *
     * @author Daan Rosbergen
     * @param mailController
     */
    public MailDankwoordView(MailController mailController) {
        this.mailController = mailController;
        this.email = new Email();
        createHeader();
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

    /**
     * Creates a form field.
     *
     * @author Daan Rosbergen
     * @param name  String  Name of the field.
     */
    private void createField(String name) {
        Label label = new Label(name);
        onderwerp = new TextField();
        onderwerp.textProperty().addListener((observable, oldValue, newValue) -> {
            this.email.setSubject(newValue);
        });
        addToForm(label, onderwerp);
    }

    /**
     * Create header of the view.
     *
     * @author Daan Rosbergen
     */
    private void createHeader() {
        createTitle();
        add(topContainer,0, currentRow);
        currentRow++;
    }

    /**
     * Create title of the view.
     *
     * @author Daan Rosbergen
     */
    private void createTitle() {
        Text title = new Text("Verstuur dankwoord");
        title.getStyleClass().add("h1");
        topContainer.getChildren().add(title);
        topContainer.setAlignment(Pos.CENTER_RIGHT);
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
     * @author Daan Rosbergen
     * @param name          Name of the button.
     * @param eventhandler  EventHandler for button action.
     */
    private void createButton(String name, EventHandler eventhandler) {
        Button button = new Button(name);
        button.getStyleClass().addAll("btn", "btn-primary");
        button.setOnAction(eventhandler);
        addToForm(button);
    }

    /**
     * Adds 2 nodes to the view in the same row.
     *
     * @author Daan Rosbergen
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
     * @author Daan Rosbergen
     * @param node
     */
    private void addToForm(Node node) {
        add(node, 1, currentRow);
        currentRow++;
    }

    /**
     * Viewable method shows this view.
     *
     * @author Daan Rosbergen
     */
    @Override public void show() {
        this.stage.setScene(new Scene(this));
        this.stage.show();
    }
}
