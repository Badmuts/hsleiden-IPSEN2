package Panthera.DAO;

import Panthera.Models.Land;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Class used to retrieve, manipulate and remove Land models from the DB.
 *
 * @author Daan Rosbergen
 * Created by Daan on 27-Sep-15.
 */
public class LandDAO extends DAO {

    public LandDAO() throws IllegalAccessException, InstantiationException, SQLException {
    }

    /**
     * Returns a list with Land models.
     *
     * @return ArrayList<Product> List with product models.
     * @throws Exception
     */
    public ArrayList<Land> all() throws Exception {
        ArrayList<Land> landen = new ArrayList<>();
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM land");
        while (result.next()) {
            landen.add(new Land(
                    result.getInt("id"),
                    result.getString("land")
            ));
        }
        return landen;
    }

    /**
     * Get a Land model by id with values from the DB.
     *
     * @param id id of record in database.
     * @return Land Land model.
     * @throws Exception
     */
    public Land get(int id) throws Exception {
        Land land = new Land();
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM land WHERE id =" + id);
        while (result.next()) {
            land.setId(result.getInt("id"));
            land.setNaam(result.getString("land"));
        }
        return land;
    }
}
