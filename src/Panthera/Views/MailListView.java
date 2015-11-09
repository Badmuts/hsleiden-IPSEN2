package Panthera.Views;

import Panthera.Panthera;
import Panthera.Controllers.MailController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Main view to send emails. The user can select one of the two options to send a email:
 *
 *  - Danwoord email
 *  - Uitnodiging email
 *
 * @author Daan Rosbergen
 * Created by Daan on 30-Sep-15.
 */
public class MailListView extends BorderPane implements Viewable {
    private final Stage stage = Panthera.getInstance().getStage();
    private final MailController mailController;
    private VBox topContainer = new VBox(10);
    private HBox body = new HBox(30);
    private HBox footer = new HBox(30);

    /**
     * Creates a new MailListView.
     *
     * @param mailController
     */
    public MailListView(MailController mailController) {
        this.mailController = mailController;

        setPadding(new Insets(22));
        createHeader();
        createBody();
    }

    /**
     * Create header of the view.
     */
    private void createHeader() {
        createTitle();
        createDankwoordButton();
        createUitnodigingButton();
        createHerinneringButton();
        setTop(topContainer);
    }

    /**
     * Create body of the view.
     */
    private void createBody() {
    	setCenter(body);
    	setBottom(footer);
    	createExplainText();
    }

    /**
     * Display explain text.
     */
    private void createExplainText() {
    	Text explain = new Text("Verstuur een email naar alle gasten.");
    	body.getChildren().add(explain);
    }

    /**
     * Create title of the view.
     */
    private void createTitle() {
        Text title = new Text("Email");
        title.setFont(Font.font(22));
        title.getStyleClass().add("h1");
        topContainer.getChildren().add(title);
        topContainer.setAlignment(Pos.CENTER_RIGHT);
    }

    /**
     * Create button for uitnodingen view.
     */
    private void createUitnodigingButton() {
        Button button = new Button("Verstuur uitnodiging");
        button.setOnAction(event -> this.mailController.cmdShowUitnodigingView());
        button.getStyleClass().addAll("btn", "btn-primary");
        footer.getChildren().add(button);
    }

    /**
     * Create button for dankwoord view.
     */
    private void createDankwoordButton() {
        Button button = new Button("Verstuur dankwoord");
        button.setOnAction(event -> this.mailController.cmdShowDankwoordView());
        button.getStyleClass().addAll("btn", "btn-primary");
        footer.getChildren().add(button);
    }
    
    private void createHerinneringButton() {
    	Button button = new Button("Verstuur Herinnering");
    	button.setOnAction(event -> this.mailController.cmdShowHerinneringView());
    	button.getStyleClass().addAll("btn", "btn-primary");
        footer.getChildren().add(button);
    }

    /**
     * Show the view.
     */
    @Override
    public void show() {
        this.stage.setScene(new Scene(this));
        this.stage.show();
    }
}
