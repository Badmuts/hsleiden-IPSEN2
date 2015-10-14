package Panthera.Views;

import java.util.List;

import com.sun.javafx.geom.BaseBounds;

import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;

import Panthera.Panthera;
import Panthera.Controllers.MailController;
import Panthera.DAO.MailTemplatesDAO;
import Panthera.Models.Email;
import Panthera.Models.MailTemplate;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class MailHerinneringView extends GridPane implements Viewable {

	private final MailController mailController;
	private int currentRow = 0;
	private TextArea bericht;
	private Email email;
	private TextField onderwerp;

	public MailHerinneringView(MailController mailController) {
		this.mailController = mailController;
		this.email = new Email();
		createForm();
		setPadding();
	}

	public void createForm() {
		try {
			createField("Onderwerp");
			createChoiceBox("Template", new MailTemplatesDAO().all(),
					(observable, oldValue, newValue) -> updateBericht(observable, oldValue, newValue));
			createTextAreaField("Bericht");
			createButton("Ontvangers selecteren",
					event -> mailController.cmdShowSelectRecipients(onderwerp.getText(), bericht.getText()));
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

	private void setPadding() {
		setHgap(5);
		setVgap(5);
		setPadding(new Insets(10));
	}

	private void createChoiceBox(String name, List<MailTemplate> mails, ChangeListener<MailTemplate> changeListener) {
		Label label = new Label(name);
		ChoiceBox<MailTemplate> box = new ChoiceBox<>(FXCollections.observableArrayList(mails));

		box.getSelectionModel().selectedItemProperty().addListener(changeListener);
		addToForm(label, box);
	}

	private void updateBericht(ObservableValue<? extends MailTemplate> observable, MailTemplate oldValue,
			MailTemplate newValue) {
		this.bericht.setText(newValue.getContent());
		this.email.setText(this.bericht.getText());
	}

	private void createTextAreaField(String name) {
		Label label = new Label(name);
		this.bericht = new TextArea();
		addToForm(label, this.bericht);
	}
	private void createButton(String name, EventHandler eventhandler) {
        Button button = new Button(name);
        button.setOnAction(eventhandler);
        addToForm(button);
    }
	private void addToForm(Node label, Node textArea) {
        add(label, 0, currentRow);
        add(textArea, 1, currentRow);
        currentRow++;
    }
	private void addToForm(Node node) {
        add(node, 1, currentRow);
        currentRow++;
    }

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

}
