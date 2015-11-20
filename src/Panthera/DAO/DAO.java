package Panthera.DAO;

import Panthera.Services.DatabaseService;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Brandon on 22-Sep-15.
 * Dit is de superklasse van alle onderliggende DAO klassen
 * Alle DAO klassen extenden van DAO om zo toegang te krijgen tot de database connectie
 */
public class DAO {

    protected Connection conn;

    /**
     * @author Brandon van Wijk
     * Deze constructor haalt de singleton instantie op uit de databaseservice klasse
     * En vult daarmee het connectie attribuut dat gebruikt wordt
     * In de subklassen van DAO
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws SQLException
     */
    public DAO() throws IllegalAccessException, InstantiationException, SQLException {
            this.conn = DatabaseService.getInstance().getConnection();
    }
}
