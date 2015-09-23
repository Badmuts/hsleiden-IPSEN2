package Panthera.Views;
import Panthera.Controllers.FacturenController;
import Panthera.DAO.FactuurDAO;
import Panthera.Models.Factuur;
import Panthera.Panthera;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.sql.SQLException;


/**
 * Created by Brandon on 23-Sep-15.
 */
public class FacturenListView extends BorderPane implements Viewable {

    private FacturenController facturenController;
    private Stage stage = Panthera.getInstance().getStage();

    public FacturenListView(FacturenController facturenController)  {

        this.facturenController = facturenController;
        Text title = new Text("Facturen");

        title.setFont(Font.font(22));
        setTop(title);


        FactuurDAO factuurDAO = null;
        try {
            factuurDAO = new FactuurDAO();
            Factuur factuur = factuurDAO.getFactuur(1);
            Text factuurr = new Text(factuur.toString());
            setCenter(factuurr);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void show() {
        this.stage.setScene(new Scene(this));
        this.stage.show();
    }
}
