package Panthera.DAO;

import Panthera.Services.DatabaseService;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Brandon on 22-Sep-15.
 */
public class DAO {

    /** deze connectie wordt gebruikt in alle DAO klassen
     * die extenden van dao
     */
    protected Connection conn;

    /**
     * de connectie wordt gevuld met de echte connectie die opgehaald wordt uit de singleton extensie
     */
    public DAO() throws IllegalAccessException, InstantiationException, SQLException {
            this.conn = DatabaseService.getInstance().getConnection();
    }
}
