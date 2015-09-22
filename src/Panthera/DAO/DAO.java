package Panthera.DAO;

import Panthera.Services.DatabaseService;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Brandon on 22-Sep-15.
 */
public class DAO {

    protected Connection conn;

    public DAO() throws IllegalAccessException, InstantiationException, SQLException {
            this.conn = DatabaseService.getInstance().getConnection();

    }
}
