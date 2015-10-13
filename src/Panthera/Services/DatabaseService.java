package Panthera.Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Brandon on 22-Sep-15.
 */

public class DatabaseService {

    private volatile static DatabaseService connectionInstance;

    private DatabaseService()  {

        try {
            Class.forName("org.postgresql.Driver").newInstance();
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: unable to load driver class!");
            System.exit(1);
        }
            catch(IllegalAccessException ex) {
                System.out.println("Error: access problem while loading!");
                System.exit(2);
            }
                catch(InstantiationException ex){
                    System.out.println("Error: unable to instantiate driver!");
                    System.exit(3);
                }
            }

    public static DatabaseService getInstance() throws InstantiationException, IllegalAccessException {
        if(connectionInstance == null) {
            synchronized (DatabaseService.class) {
                if(connectionInstance == null) {
                    connectionInstance = new DatabaseService();
                }
            }
        }
        return connectionInstance;
    }

    public Connection getConnection() throws SQLException {
        String URL = "jdbc:postgresql://localhost/Lions";
        Properties info = new Properties( );

        info.put( "user", "postgres" );
        info.put( "password", "" );

        Connection conn = DriverManager.getConnection(URL, info);

        return conn;
    }
}
